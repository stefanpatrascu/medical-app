import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { HttpEventType } from "@angular/common/http";
import { SafeUrl } from "@angular/platform-browser";
import { AccountApiService } from "../../../../api/account/account-api.service";
import { CustomToastService } from "../../../../shared/modules/toast/toast.service";

@Component({
  selector: 'app-my-avatar',
  templateUrl: './my-avatar.component.html',
  styleUrls: ['./my-avatar.component.scss']
})
export class MyAvatarComponent implements OnInit {
  @ViewChild('fileInput') fileInput: ElementRef | undefined;
  uploadInProgress: boolean = false;
  avatarUrl!: SafeUrl;

  constructor(private readonly accountService: AccountApiService,
              private readonly toastService: CustomToastService) {
  }

  public triggerFileInput(): void {
    this.fileInput?.nativeElement.click();
  }

  public ngOnInit(): void {
    this.populateAvatar();
  }

  public onFileSelected(event: Event): void {
    const target = event.target as HTMLInputElement;
    const file: File = (target.files as FileList)[0];
    this.accountService.uploadAvatar(file).subscribe((event) => {
      if (event.type === HttpEventType.UploadProgress) {
        this.uploadInProgress = true;
      } else if (event.type === HttpEventType.Response) {
        this.toastService.success("Success", "Avatar uploaded successfully");
        this.populateAvatar();
        this.uploadInProgress = false;
      }
    });
  }


  private populateAvatar(): void {
    this.accountService.getMyAvatar()
      .subscribe((safeUrl: SafeUrl) => {
        this.avatarUrl = safeUrl;
      });
  }
}
