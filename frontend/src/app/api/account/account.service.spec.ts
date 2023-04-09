import { TestBed } from '@angular/core/testing';

import { AccountApiService } from './account-api.service';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('AccountService', () => {
  let service: AccountApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(AccountApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
