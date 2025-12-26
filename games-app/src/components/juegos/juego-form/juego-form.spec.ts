import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JuegoForm } from './juego-form';

describe('JuegoForm', () => {
  let component: JuegoForm;
  let fixture: ComponentFixture<JuegoForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JuegoForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JuegoForm);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
