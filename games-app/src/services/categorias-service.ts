import { HttpClient } from "@angular/common/http";
import { ConstantesRest } from "../shared/restapi/constantes-rest";
import { Observable } from "rxjs/internal/Observable";
import { Categoria } from "../models/categoria";
import { Injectable } from "@angular/core";


@Injectable({
  providedIn: 'root'
})
export class CategoriasService{
    constantesRest =  new ConstantesRest();

    constructor(private httpCliente: HttpClient){}

  
    public crearCategoria(categoria: Categoria):  Observable<void>{
      return this.httpCliente.post<void>(this.constantesRest.getApiURL() + 'categorias',categoria);
    }

}