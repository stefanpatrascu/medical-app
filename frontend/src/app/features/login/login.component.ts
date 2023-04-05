import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { LoginService } from "../../api/login.service";
import { GenericApiResponse } from "../../api/interfaces/generic.interface";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form!: FormGroup;

  constructor(private formBuilder: FormBuilder, private loginService: LoginService) {
  }

  ngOnInit(): void {
    this.generateReactiveForm();
  }

  public onSubmit(): void {
    if (this.form.invalid) {
      this.form.controls['email'].markAsDirty();
      this.form.controls['password'].markAsDirty();
      return;
    }

    this.loginService.authentificate(this.form.value).subscribe((response: GenericApiResponse) => {
      console.log(response);
    });

  }

  private generateReactiveForm(): void {
    this.form = this.formBuilder.group({
      email: new FormControl<string | null>(null, Validators.required),
      password: new FormControl<string | null>(null, Validators.required)
    });
  }

}
