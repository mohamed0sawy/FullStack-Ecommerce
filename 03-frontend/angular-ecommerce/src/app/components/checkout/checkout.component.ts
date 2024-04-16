import { JsonPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { State } from 'src/app/common/state';
import { Country } from 'src/app/common/country';
import { FormService } from 'src/app/services/form.service';
import { CustomValidators } from 'src/app/validators/custom-validators';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css'],
})
export class CheckoutComponent implements OnInit {
  checkoutFormGroup!: FormGroup;
  months: number[] = [];
  years: number[] = [];
  countries: Country[] = [];
  shippingStates: State[] = [];
  billingStates: State[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private formService: FormService
  ) {}

  ngOnInit(): void {
    this.checkoutFormGroup = this.formBuilder.group({
      customer: this.formBuilder.group({
        firstName: new FormControl('', [
          Validators.required,
          Validators.minLength(2),
          CustomValidators.notOnlyWhiteSpace,
        ]),
        lastName: new FormControl('', [
          Validators.required,
          Validators.minLength(2),
          CustomValidators.notOnlyWhiteSpace,
        ]),
        email: new FormControl('', [
          Validators.required,
          Validators.pattern('^[a-z0-9._+%-]+@[a-z0-9._]+\\.[a-z]{2-4}$'),
        ]),
      }),
      shippingAddress: this.formBuilder.group({
        country: [''],
        street: new FormControl('', [
          Validators.required,
          Validators.minLength(2),
          CustomValidators.notOnlyWhiteSpace,
        ]),
        city: new FormControl('', [
          Validators.required,
          Validators.minLength(2),
          CustomValidators.notOnlyWhiteSpace,
        ]),
        state: [''],
        zipCode: new FormControl('', [
          Validators.required,
          Validators.minLength(2),
          CustomValidators.notOnlyWhiteSpace,
        ]),
      }),
      billingAddress: this.formBuilder.group({
        country: [''],
        street: new FormControl('', [
          Validators.required,
          Validators.minLength(2),
          CustomValidators.notOnlyWhiteSpace,
        ]),
        city: new FormControl('', [
          Validators.required,
          Validators.minLength(2),
          CustomValidators.notOnlyWhiteSpace,
        ]),
        state: [''],
        zipCode: new FormControl('', [
          Validators.required,
          Validators.minLength(2),
          CustomValidators.notOnlyWhiteSpace,
        ]),
      }),
      creditCard: this.formBuilder.group({
        cardType: [''],
        NameOnCard: new FormControl('', [
          Validators.required,
          Validators.minLength(2),
          CustomValidators.notOnlyWhiteSpace,
        ]),
        cardNumber: new FormControl('', [
          Validators.required,
          Validators.pattern('[0-9]{16}'),
        ]),
        securityCode: new FormControl('', [
          Validators.required,
          Validators.pattern('[0-9]{3}'),
        ]),
        expirationMonth: [''],
        expirationYear: [''],
      }),
    });
    this.formService
      .getCreditCardMonths()
      .subscribe((data) => (this.months = data));
    this.formService
      .getCreditCardYears()
      .subscribe((data) => (this.years = data));

    this.getCountries();
  }

  onSubmit() {
    if (this.checkoutFormGroup.invalid) {
      this.checkoutFormGroup.markAllAsTouched();
    }
    console.log(this.checkoutFormGroup.valid);
    // console.log(this.checkoutFormGroup.get('customer')?.value);
    // console.log(this.checkoutFormGroup.get('shippingAddress')?.value);
    // console.log(this.checkoutFormGroup.get('billingAddress')?.value);
    // console.log(this.checkoutFormGroup.get('creditCard')?.value);
  }

  getCountries() {
    this.formService
      .getCountries()
      .subscribe((data) => (this.countries = data));
  }

  getStateOfCountry(formGroup: string) {
    const countryCode =
      this.checkoutFormGroup.get(formGroup)?.value.country.code;
    this.formService.getStates(countryCode).subscribe((data) => {
      if (formGroup == 'shippingAddress') {
        this.shippingStates = data;
      } else {
        this.billingStates = data;
      }
    });
  }

  get firstName() {
    return this.checkoutFormGroup.get('customer.firstName');
  }

  get lastName() {
    return this.checkoutFormGroup.get('customer.lastName');
  }

  get email() {
    return this.checkoutFormGroup.get('customer.email');
  }

  get streetShipping() {
    return this.checkoutFormGroup.get('shippingAddress.street');
  }

  get cityShipping() {
    return this.checkoutFormGroup.get('shippingAddress.city');
  }

  get zipCodeShipping() {
    return this.checkoutFormGroup.get('shippingAddress.zipCode');
  }

  get streetBilling() {
    return this.checkoutFormGroup.get('billingAddress.street');
  }

  get cityBilling() {
    return this.checkoutFormGroup.get('billingAddress.city');
  }

  get zipCodeBilling() {
    return this.checkoutFormGroup.get('billingAddress.zipCode');
  }

  get NameOnCard() {
    return this.checkoutFormGroup.get('creditCard.NameOnCard');
  }

  get cardNumber() {
    return this.checkoutFormGroup.get('creditCard.cardNumber');
  }

  get securityCode() {
    return this.checkoutFormGroup.get('creditCard.securityCode');
  }
}
