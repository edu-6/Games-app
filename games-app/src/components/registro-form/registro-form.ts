import { Component } from '@angular/core';
@Component({
  selector: 'app-registro-form',
  templateUrl: './registro-form.html',
  styleUrl: './registro-form.css',
})

export class RegistroForm {
  fotoSeleccionada: File | null = null;
  hayArchivoCargado: boolean = false;
  urlTemporal: String = "url";


  AlCargarArchivo(event: any): void {
    const archivos = event.target.files;
    if (archivos && archivos.length > 0) {
      //Seguridad para solo permitir imagenes
      if (!archivos[0].type.startsWith('image/')) {
        alert('Solo se permiten imágenes');
        event.target.value = ''; // limpiar el contenedor
        return;
      }

      this.fotoSeleccionada = archivos[0];
      this.hayArchivoCargado = true;
      this.urlTemporal = URL.createObjectURL(archivos[0]);

      
    }
  }

}
