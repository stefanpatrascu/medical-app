import { TestBed } from '@angular/core/testing';

import { CustomToastService } from './toast.service';
import { CustomToastModule } from "./toast.module";

describe('ToastService', () => {
  let service: CustomToastService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [CustomToastModule]
    });
    service = TestBed.inject(CustomToastService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
