import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { LoginApiService } from "../../api/login/login-api.service";
import { GenericApiResponse } from "../../interfaces/generic.interface";
import { CustomToastService } from "../../shared/modules/toast/toast.service";
import { HttpErrorResponse } from "@angular/common/http";
import { AccountApiService } from "../../api/account/account-api.service";
import { filter, lastValueFrom } from "rxjs";
import { IAccountResponse } from "../../api/account/account.interface";
import { Router } from "@angular/router";
import { RouteEnum } from "../../enums/route.enum";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class LoginComponent implements OnInit {

  form!: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private loginService: LoginApiService,
              private accountService: AccountApiService,
              private toastService: CustomToastService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.generateReactiveForm();
  }

  public onSubmit(): void {
    if (this.form.invalid) {
      this.markAllAsDirty();
      this.toastService.warning("Warning", "Please fill all the fields");
      return;
    }

    this.loginService.authentificate(this.form.value)
      .subscribe({
        next: (response: GenericApiResponse<IAccountResponse>) => {
          if (response.data) {
            this.toastService.success("Success",
              "Welcome back, " + response.data?.lastName + " " + response.data?.firstName + "!");
            this.router.navigate([RouteEnum.EDIT_ACCOUNT]);
          } else {
            this.toastService.error("Error", "An error occurred");
          }
        },
        error: (error: HttpErrorResponse) => {
          if (error.status === 401) {
            this.toastService.error("Error", "Your email or password is incorrect");
          } else {
            this.toastService.error("Error", "An error occurred");
          }
        }
      });
  }

  private markAllAsDirty(): void {
    this.form.controls['email'].markAsDirty();
    this.form.controls['password'].markAsDirty();
  }

  private generateReactiveForm(): void {
    this.form = this.formBuilder.group({
      email: new FormControl<string | null>(null, Validators.required),
      password: new FormControl<string | null>(null, Validators.required)
    });
  }

}
