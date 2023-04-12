import { TestBed } from '@angular/core/testing';

import { SessionsApiService } from './session.service';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('SessionService', () => {
  let service: SessionsApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(SessionsApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
