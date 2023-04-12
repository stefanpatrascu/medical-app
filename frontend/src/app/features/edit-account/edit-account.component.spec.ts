import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAccountComponent } from './edit-account.component';
import { EditAccountModule } from "./edit-account.module";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { CustomToastService } from "../../shared/modules/toast/toast.service";
import { MessageService } from "primeng/api";

describe('EditAccountComponent', () => {
  let component: EditAccountComponent;
  let fixture: ComponentFixture<EditAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditAccountModule, HttpClientTestingModule],
      providers: [CustomToastService, MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
