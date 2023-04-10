import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { AccountApiService } from "../../api/account/account-api.service";
import { IAccountResponse, IAccountUpdateRequest } from "../../api/account/account.interface";
import { Message } from "primeng/api";
import { DatePipe } from "@angular/common";
import { CustomToastService } from "../../shared/modules/toast/toast.service";

@Component({
  selector: 'app-edit-account',
  templateUrl: './edit-account.component.html',
  styleUrls: ['./edit-account.component.scss']
})
export class EditAccountComponent implements OnInit {
  messages: Message[] = [];


  public form!: FormGroup;

  constructor(private readonly accountService: AccountApiService,
              private readonly formBuilder: FormBuilder,
              private readonly datePipe: DatePipe,
              private readonly toastService: CustomToastService) {
  }

  ngOnInit(): void {
    this.generateReactiveForm();
    // this.messages = [{ severity: 'info', summary: 'Info', detail: 'Please enter a new email and password in the fields below and click the Update button to complete the process. Blank fields will not be saved.' }];
  }

  private populateForm(): void {
    this.accountService.getMyAccount(true)
      .subscribe((account: IAccountResponse | null) => {
        this.form.get("firstName")?.setValue(account?.firstName);
        this.form.get("lastName")?.setValue(account?.lastName);
        this.form.get("currentEmail")?.setValue(account?.email);
        this.form.get("cnp")?.setValue(account?.userInfo?.cnp);
        this.form.get("birthDate")?.setValue(account?.userInfo?.birthDate ? new Date(account?.userInfo?.birthDate) : null)
      });
  }

  private generateReactiveForm(): void {
    this.form = this.formBuilder.group({
      firstName: new FormControl<string | null>(null, [Validators.required]),
      lastName: new FormControl<string | null>(null, [Validators.required]),
      cnp: new FormControl<string | null>(null, [Validators.required]),
      currentEmail: new FormControl<string | null>(null, [Validators.required, Validators.email]),
      email: new FormControl<string | null>(null, [Validators.required, Validators.email]),
      password: new FormControl<string | null>(null, [Validators.required]),
      birthDate: new FormControl<Date | null>(null, [Validators.required]),
    });
    this.populateForm();
  }

  public onSubmit(): void {
    const accountUpdateRequest: IAccountUpdateRequest = {
      ...this.form.value,
      birthDate: this.datePipe.transform(this.form.value.birthDate, 'yyyy-MM-dd')
    }
    if (this.form.valid) {
      this.accountService.updateAccount(accountUpdateRequest)
        .subscribe(() => {
          this.populateForm();
          this.toastService.success("Success",
            "Account updated successfully!");
        });
    }
  }
}
