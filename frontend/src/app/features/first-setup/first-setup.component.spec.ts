import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FirstSetupComponent } from './first-setup.component';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('FirstSetupComponent', () => {
  let component: FirstSetupComponent;
  let fixture: ComponentFixture<FirstSetupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ FirstSetupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FirstSetupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
