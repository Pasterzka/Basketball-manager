import { TestBed } from '@angular/core/testing';

import { Basketball } from './basketball';

describe('Basketball', () => {
  let service: Basketball;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Basketball);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
