import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FortuneFivehundredComponent } from './fortune-fivehundred.component';

describe('FortuneFivehundredComponent', () => {
  let component: FortuneFivehundredComponent;
  let fixture: ComponentFixture<FortuneFivehundredComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FortuneFivehundredComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FortuneFivehundredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
