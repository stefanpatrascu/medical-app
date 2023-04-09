import { TestBed } from '@angular/core/testing';

import { LoginApiService } from './login-api.service';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('LoginService', () => {
  let service: LoginApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(LoginApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
