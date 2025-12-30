/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.servicios;

import com.mycompany.rest.api.games.db.ComprasDB;
import com.mycompany.rest.api.games.dtos.compras.CompraRequest;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.MenorDeEdadException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.exceptions.SaldoInsuficienteException;
import com.mycompany.rest.api.games.modelos.compras.CompraExistencia;
import com.mycompany.rest.api.games.modelos.compras.CompraJuego;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class ComprasCrudService {

    public void registrarCompra(CompraRequest compraRequest) throws SQLException, SaldoInsuficienteException, NoEncontradoException, IdentidadRepetidaException, MenorDeEdadException {
        ComprasDB db = new ComprasDB();
        int id_juego = db.obtenerIdJuegoPorNombre(compraRequest.getNombreJuego());
        double precio_juego = db.obtenerPrecioJuego(id_juego);
        double saldo_gamer = db.obtenerSaldoGamer(compraRequest.getCorreoUsuario());
        
        if(id_juego < 0){
            throw new NoEncontradoException();
        }
        
        
        boolean gamerEsMayor = db.gamerEsMayorDeEdad(compraRequest.getCorreoUsuario());
        boolean juegoEsParaAdultos = db.juegoEsParaAdultos(id_juego);
        
        if(!gamerEsMayor && juegoEsParaAdultos){
            throw new MenorDeEdadException();
        }
        
        if(saldo_gamer < precio_juego){
            throw new SaldoInsuficienteException(); // saldo insuficiente
        }
        
        if(existeCompra(new CompraExistencia(compraRequest.getCorreoUsuario(), compraRequest.getNombreJuego()))){
            throw new IdentidadRepetidaException(" ya compró el juego !");
        }
        
        double saldoNuevo = saldo_gamer - precio_juego;
        db.actualizarSaldoGamer(saldoNuevo, compraRequest.getCorreoUsuario()); // actualizar saldo
        
        int porcentaje_comision = db.obtenerPorcentajeComision();
        double comision = (precio_juego*porcentaje_comision)/100;
        
        
        
        
        CompraJuego compra = new CompraJuego(id_juego, compraRequest.getCorreoUsuario(),
                false, compraRequest.getFechaCompra(), precio_juego, comision, porcentaje_comision);
       db.registrarCompra(compra);
        
    }
    
    
    public boolean existeCompra(CompraExistencia compra) throws SQLException{
        ComprasDB db = new ComprasDB();
        int id_juego = db.obtenerIdJuegoPorNombre(compra.getNombreJuego());
        return db.existeCompra(id_juego, compra.getCorreoUsuario());
    }
}
