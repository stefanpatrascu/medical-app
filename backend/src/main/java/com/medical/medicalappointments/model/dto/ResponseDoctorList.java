package com.medical.medicalappointments.model.dto;

import com.medical.medicalappointments.model.entity.DoctorSpecialization;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ResponseDoctorList {

    private Long id;

    private String doctorName;

    private List<DoctorSpecialization> specializations;

}
