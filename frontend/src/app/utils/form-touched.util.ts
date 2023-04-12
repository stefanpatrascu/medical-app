import { FormGroup } from "@angular/forms";

export const markAsTouched = (form: FormGroup) => {
  for (let i in form.controls) {
    form.controls[i].markAsTouched();
    form.controls[i].markAsDirty();
  }
}

