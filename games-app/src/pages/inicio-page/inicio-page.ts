import { Component } from '@angular/core';
import { Header } from '../../components/header/header';

@Component({
  selector: 'app-inicio-page',
  imports: [Header],
  templateUrl: './inicio-page.html',
  styleUrl: './inicio-page.css',
})
export class InicioPage {
  getRol():string | null{
    return localStorage.getItem('rol');
}

}


