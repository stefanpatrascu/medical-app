import { TestBed } from '@angular/core/testing';

import { SessionsApiService } from './session.service';

describe('SessionService', () => {
  let service: SessionsApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SessionsApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
