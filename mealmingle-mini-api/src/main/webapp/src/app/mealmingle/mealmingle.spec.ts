import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Mealmingle } from './mealmingle';

describe('Mealmingle', () => {
  let component: Mealmingle;
  let fixture: ComponentFixture<Mealmingle>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Mealmingle]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Mealmingle);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
