import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyAvatarComponent } from './my-avatar.component';
import { EditAccountModule } from "../../edit-account.module";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { CustomToastService } from "../../../../shared/modules/toast/toast.service";
import { MessageService } from "primeng/api";

describe('MyAvatarComponent', () => {
  let component: MyAvatarComponent;
  let fixture: ComponentFixture<MyAvatarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditAccountModule, HttpClientTestingModule],
      providers: [CustomToastService, MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MyAvatarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
