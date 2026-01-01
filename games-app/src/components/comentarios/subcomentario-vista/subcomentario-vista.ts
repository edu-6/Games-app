import { Component, Input, OnInit } from '@angular/core';
import { SubcomentarioResponse } from '../../../models/comentarios/subcomentario-response';

@Component({
  selector: 'app-subcomentario-vista',
  standalone: true,
  templateUrl: './subcomentario-vista.html'
})
export class SubcomentarioVista implements OnInit{
  @Input({ required: true }) 
  subcomentario!: SubcomentarioResponse;

  ngOnInit(): void {
    console.log(this.subcomentario);
  }
}