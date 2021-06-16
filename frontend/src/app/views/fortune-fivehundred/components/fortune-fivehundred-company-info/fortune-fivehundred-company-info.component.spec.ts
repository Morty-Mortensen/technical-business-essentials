import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FortuneFivehundredCompanyInfoComponent } from './fortune-fivehundred-company-info.component';

describe('FortuneFivehundredCompanyInfoComponent', () => {
  let component: FortuneFivehundredCompanyInfoComponent;
  let fixture: ComponentFixture<FortuneFivehundredCompanyInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FortuneFivehundredCompanyInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FortuneFivehundredCompanyInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
