import { Component, OnInit } from '@angular/core';
import { AccountApiService } from "../../api/account/account-api.service";
import { IAccountResponse } from "../../api/account/account.interface";

@Component({
  selector: 'app-first-setup',
  templateUrl: './first-setup.component.html',
  styleUrls: ['./first-setup.component.scss']
})
export class FirstSetupComponent implements OnInit {

  constructor(private accountService: AccountApiService) {
  }

  ngOnInit(): void {
    this.accountService.getMyAccount()
      .subscribe((account: IAccountResponse | null) => {
        console.log(account)
      });
  }
}
