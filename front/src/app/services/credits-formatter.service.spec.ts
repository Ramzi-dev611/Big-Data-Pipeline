import { TestBed } from '@angular/core/testing';

import { CreditsFormatterService } from './credits-formatter.service';

describe('CreditsFormatterService', () => {
  let service: CreditsFormatterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreditsFormatterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
