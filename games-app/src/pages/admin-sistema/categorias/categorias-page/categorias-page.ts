import { Component } from '@angular/core';
import { Header } from '../../../../components/header/header';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-categorias-page',
  imports: [Header, RouterLink],
  templateUrl: './categorias-page.html',
  styleUrl: './categorias-page.css',
})
export class CategoriasPage {

}
