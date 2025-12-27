export class BusquedaJuego {
    nombre: string | null;
    precioMaximo: number;
    precioMinimo: number;
    categoria: string | null;
    correoAdmin: string | null;

    constructor(nombre: string| null, precioMax: number, precioMin:number, categoria:string | null, correo: string | null) {
        this.nombre = nombre;
        this.precioMaximo = precioMax;
        this.precioMinimo = precioMin;
        this.categoria = categoria;
        this.correoAdmin = correo;
    }
}