import { HttpClient } from "@angular/common/http";
import { ConstantesRest } from "../shared/restapi/constantes-rest";
import { Observable } from "rxjs/internal/Observable";
import { Categoria } from "../models/categorias/categoria";
import { Injectable } from "@angular/core";
import { core } from "@angular/compiler";
import { EdicionCategoria } from "../models/categorias/edicion-categoria";


@Injectable({
  providedIn: 'root'
})
export class CategoriasService {
  constantesRest = new ConstantesRest();

  constructor(private httpCliente: HttpClient) { }


  public crearCategoria(categoria: Categoria): Observable<void> {
    return this.httpCliente.post<void>(this.constantesRest.getApiURL() + 'categorias', categoria);
  }
  public obtenerCategorias(): Observable<Categoria[]> {
    return this.httpCliente.get<Categoria[]>(this.constantesRest.getApiURL() + 'categorias');
  }

  public buscarCategoria(nombre: string): Observable<Categoria>{
    return this.httpCliente.get<Categoria>(`${this.constantesRest.getApiURL()}categorias/${nombre}`);
  }

  public editarCategoria(nueva: EdicionCategoria): Observable<void>{
    return this.httpCliente.put<void>(this.constantesRest.getApiURL() + 'categorias', nueva);
  }

}