import { TestBed } from '@angular/core/testing';

import { CreditsService } from './credits.service';

describe('CreditsService', () => {
  let service: CreditsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreditsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
