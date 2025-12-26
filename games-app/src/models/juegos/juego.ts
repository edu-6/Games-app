export interface Juego {
    id: number;
    nombre: string;
    clasificacion: string;
    descripcion: string;
    requerimientos: string;
    precio: number;
    fechaLanzamiento: Date;
    activo: boolean;
    permiteComentarios: boolean;
    codigoEmpresa: number;
}