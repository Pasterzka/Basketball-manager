import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeagueEdit } from './league-edit';

describe('LeagueEdit', () => {
  let component: LeagueEdit;
  let fixture: ComponentFixture<LeagueEdit>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LeagueEdit],
    }).compileComponents();

    fixture = TestBed.createComponent(LeagueEdit);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
