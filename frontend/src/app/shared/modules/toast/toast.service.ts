import { Injectable} from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable()
export class CustomToastService {
  constructor(
    private messageService: MessageService,
  ) {
  }

  public success(summary: string, detail: string): void {
    this.messageService.add({
      severity: 'success',
      summary: summary,
      detail: detail,
    });
  }

  public error(summary: string, detail: string): void {
    this.messageService.add({
      severity: 'error',
      summary: summary,
      detail: detail,
    });
  }

  public warning(summary: string, detail: string): void {
    this.messageService.add({
      severity: 'warn',
      summary: summary,
      detail: detail,
    });
  }
}
