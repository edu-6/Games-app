import { Component, OnInit } from '@angular/core';
import { Header } from '../../../../components/header/header';
import { NuevaCategoriaFrom } from '../../../../components/categorias/nueva-categoria-from/nueva-categoria-from';
import { Categoria } from '../../../../models/categorias/categoria';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoriasService } from '../../../../services/categorias-service';
@Component({
  selector: 'app-editar-categoria-page',
  imports: [Header, NuevaCategoriaFrom],
  templateUrl: './editar-categoria-page.html',
  styleUrl: './editar-categoria-page.css',
})
export class EditarCategoriaPage implements OnInit {
  categoriaAntigua !: Categoria;
  categoriaParametro !: string;

  constructor(private router: ActivatedRoute, private categoriaService: CategoriasService) { }

  ngOnInit(): void {
    this.categoriaParametro = this.router.snapshot.params['categoria'];
    this.categoriaService.buscarCategoria(this.categoriaParametro).subscribe({
      next: (categoria: Categoria) =>{
        this.categoriaAntigua = categoria;
      },
      error : (error: any) =>{
        console.log(error);
      }
    });
  }
}
