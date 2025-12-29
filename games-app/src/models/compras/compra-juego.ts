export class CompraJuego {
    fechaCompra: Date; 
    nombreJuego: string;
    correoUsuario: string;

    constructor(fechaCompra: Date, nombreJuego: string, correoUsuario: string) {
        this.fechaCompra = fechaCompra;
        this.nombreJuego = nombreJuego;
        this.correoUsuario = correoUsuario;
    }
}