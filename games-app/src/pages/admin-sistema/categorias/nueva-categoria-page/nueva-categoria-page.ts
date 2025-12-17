import { Component } from '@angular/core';
import { NuevaCategoriaFrom } from "../../../../components/categorias/nueva-categoria-from/nueva-categoria-from";
import { Header } from '../../../../components/header/header';

@Component({
  selector: 'app-nueva-categoria-page',
  imports: [Header, NuevaCategoriaFrom],
  templateUrl: './nueva-categoria-page.html',
  styleUrl: './nueva-categoria-page.css',
})
export class NuevaCategoriaPage {

}
