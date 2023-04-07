import { TestBed } from '@angular/core/testing';

import { CustomToastService } from './toast.service';

describe('ToastService', () => {
  let service: CustomToastService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomToastService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
