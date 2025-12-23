import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EdicionEmpresa } from './edicion-empresa';

describe('EdicionEmpresa', () => {
  let component: EdicionEmpresa;
  let fixture: ComponentFixture<EdicionEmpresa>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EdicionEmpresa]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EdicionEmpresa);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
