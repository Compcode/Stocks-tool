import { TestBed } from '@angular/core/testing';

import { PortFoiloPredictionService } from './port-foilo-prediction.service';

describe('PortFoiloPredictionService', () => {
  let service: PortFoiloPredictionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PortFoiloPredictionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
