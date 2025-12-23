import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpresasEditarPage } from './empresas-editar-page';

describe('EmpresasEditarPage', () => {
  let component: EmpresasEditarPage;
  let fixture: ComponentFixture<EmpresasEditarPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmpresasEditarPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmpresasEditarPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
