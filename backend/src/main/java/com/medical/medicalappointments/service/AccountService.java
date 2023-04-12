package com.medical.medicalappointments.service;

import com.medical.medicalappointments.model.dto.ResponseEntityDTO;
import com.medical.medicalappointments.model.dto.UpdateUserRequestDTO;
import com.medical.medicalappointments.model.entity.User;
import com.medical.medicalappointments.model.entity.UserInfo;
import com.medical.medicalappointments.model.repository.UserRepository;
import com.medical.medicalappointments.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class AccountService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserRepository userRepository;


    public void logout(HttpServletResponse response) {
        final Cookie logoutCookie = new Cookie("JWT-TOKEN", null);
        logoutCookie.setHttpOnly(true);
        logoutCookie.setSecure(true);
        logoutCookie.setPath("/");
        logoutCookie.setMaxAge(0); // Set the cookie to expire immediately

        response.addCookie(logoutCookie);

        SecurityContextHolder.getContext().setAuthentication(null);
    }

    public User getCurrentUser(String email) {
        return customUserDetailsService.getUserDetails(email);
    }


    public ResponseEntity<ResponseEntityDTO> updateAccount(Authentication authentication, UpdateUserRequestDTO updatedUser) {
        final User currentUser = getCurrentUser(authentication.getName());
        currentUser.setFirstName(updatedUser.getFirstName());
        currentUser.setLastName(updatedUser.getLastName());
        if (updatedUser.getEmail() != null) {
            currentUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null) {
            final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            currentUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        UserInfo updatedUserInfo = currentUser.getUserInfo();
        if (updatedUserInfo == null) {
            updatedUserInfo = new UserInfo();
        }
        updatedUserInfo.setCnp(updatedUser.getCnp());
        updatedUserInfo.setBirthDate(updatedUser.getBirthDate());
        currentUser.setUserInfo(updatedUserInfo);

        userRepository.save(currentUser);

        if (updatedUser.getPassword() != null && updatedUser.getEmail() != null) {
            // Re-authenticate user with the new email and password
            UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(updatedUser.getEmail(), updatedUser.getPassword(), authentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        } else if (updatedUser.getEmail() != null && updatedUser.getPassword() == null) {
            // Re-authenticate user with the new email
            UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(updatedUser.getEmail(), currentUser.getPassword(), authentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        } else if (updatedUser.getEmail() == null && updatedUser.getPassword() != null) {
            // Re-authenticate user with the new password
            UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(currentUser.getEmail(), updatedUser.getPassword(), authentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        }

        return ResponseUtil.success("Account successfully updated", null);
    }


    public ResponseEntity<ResponseEntityDTO> uploadAvatar(Authentication authentication, MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseUtil.error(HttpStatus.BAD_REQUEST, "Please select a file to upload.");
        }

        try {
            // Get the current user and the path to the old image
            User currentUser = getCurrentUser(authentication.getName());
            Path oldImagePath = Paths.get(uploadPath + File.separator + currentUser.getAvatarFileName());

            // Generate a random UUID as the new file name
            String newFileName = UUID.randomUUID().toString() + "." + file.getOriginalFilename().split("\\.")[1];

            // Read the image from the input stream
            BufferedImage originalImage = ImageIO.read(file.getInputStream());

            // Calculate the resize dimensions while maintaining the aspect ratio
            int newWidth;
            int newHeight;
            double aspectRatio = (double) originalImage.getWidth() / (double) originalImage.getHeight();

            if (originalImage.getWidth() > originalImage.getHeight()) {
                newWidth = (int) (150 * aspectRatio);
                newHeight = 150;
            } else {
                newHeight = (int) (150 / aspectRatio);
                newWidth = 150;
            }

            // Resize the image
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            BufferedImage resizedBufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = resizedBufferedImage.createGraphics();
            g2d.drawImage(resizedImage, 0, 0, null);
            g2d.dispose();

            // Calculate the crop dimensions
            int cropSize = 150;
            int x = (resizedBufferedImage.getWidth() - cropSize) / 2;
            int y = (resizedBufferedImage.getHeight() - cropSize) / 2;

            // Crop the image
            BufferedImage croppedImage = resizedBufferedImage.getSubimage(x, y, cropSize, cropSize);

            // Store the cropped image
            Path path = Paths.get(uploadPath + File.separator + newFileName);
            ImageIO.write(croppedImage, file.getOriginalFilename().split("\\.")[1], path.toFile());

            // Delete the old image if it exists
            Files.deleteIfExists(oldImagePath);

            // Update the user's avatar file path
            currentUser.setAvatarFileName(newFileName);
            userRepository.save(currentUser);

            return ResponseUtil.success("Avatar uploaded successfully: " + newFileName, null);
        } catch (IOException e) {
            return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload avatar: " + e.getMessage());
        }
    }

    public byte[] getAvatar(Authentication authentication) {
        final User currentUser = getCurrentUser(authentication.getName());
        Path path = Paths.get(uploadPath + File.separator + currentUser.getAvatarFileName());

        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            try {
                path = Paths.get(uploadPath + File.separator + "default_no_image.png");
                return Files.readAllBytes(path);
            } catch (IOException ed) {
                throw new RuntimeException("Error load image", ed);
            }
        }
    }

}
