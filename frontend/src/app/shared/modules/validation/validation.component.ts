import { Component, Input } from '@angular/core';
import { AbstractControl, FormControl } from "@angular/forms";

@Component({
  selector: 'app-validation',
  templateUrl: './validation.component.html',
  styleUrls: ['./validation.component.scss']
})
export class ValidationComponent {
  @Input() controlInput!: FormControl | AbstractControl | undefined;
  @Input() matchingMessage: string = 'Matching error';
  @Input() patternMessage: string = 'Invalid pattern';

}
