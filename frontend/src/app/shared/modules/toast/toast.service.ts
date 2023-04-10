import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable()
export class CustomToastService {
  private lastMessageSend!: string;

  constructor(
    private messageService: MessageService,
  ) {
  }

  public success(summary: string, detail: string): void {
    if (this.lastMessageSend === detail) return;
    this.messageService.add({
      severity: 'success',
      summary: summary,
      detail: detail,
    });
    this.lastMessageSend = detail;
  }

  public error(summary: string, detail: string): void {
    if (this.lastMessageSend === detail) return;
    this.messageService.add({
      severity: 'error',
      summary: summary,
      detail: detail,
    });
    this.lastMessageSend = detail;
  }

  public warning(summary: string, detail: string): void {
    if (this.lastMessageSend === detail) return;
    this.messageService.add({
      severity: 'warn',
      summary: summary,
      detail: detail,
    });
    this.lastMessageSend = detail;
  }
}
