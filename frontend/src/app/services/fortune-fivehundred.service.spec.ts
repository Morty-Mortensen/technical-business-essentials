import { TestBed } from '@angular/core/testing';

import { FortuneFivehundredService } from './fortune-fivehundred.service';

describe('FortuneFivehundredService', () => {
  let service: FortuneFivehundredService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FortuneFivehundredService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
