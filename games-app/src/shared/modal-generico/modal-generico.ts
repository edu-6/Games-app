import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-modal-generico',
  imports: [],  
  templateUrl: './modal-generico.html',
})
export class ModalGenerico {

  @Input({required : true}) 
  mensaje!: string; // mensaje generico para usar en todas las eliminaciones

  @Output() 
  confirmationExecuter = new EventEmitter<void>();

  ejecutarConfirmacion(): void {
    this.confirmationExecuter.emit();
  }

}
