import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { LoginService } from "../../api/login.service";
import { GenericApiResponse } from "../../api/interfaces/generic.interface";
import { CustomToastService } from "../../shared/toast/toast.service";
import { HttpErrorResponse } from "@angular/common/http";
import { AccountService } from "../../api/account.service";
import { lastValueFrom } from "rxjs";
import { IAccountResponse } from "../../api/interfaces/account.interface";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form!: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private loginService: LoginService,
              private accountService: AccountService,
              private toastService: CustomToastService) {
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
        next: async () => {
          const account: IAccountResponse = await lastValueFrom(this.accountService.getMyAccount());
          if (account) {
            this.toastService.success("Success", "Welcome back, " + account.lastName + " " + account.firstName + "!");
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
