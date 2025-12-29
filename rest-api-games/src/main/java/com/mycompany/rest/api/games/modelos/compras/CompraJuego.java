/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.compras;

import java.time.LocalDate;

/**
 *
 * @author edu
 */
public class CompraJuego {
    private int codigoJuego;
    private String correoUsuario;
    private boolean instalado;
    private LocalDate fechaCompra;
    private double monto;
    private double  comision;
    private int porcentajeComision;

    public CompraJuego(int codigoJuego, String correoUsuario, boolean instalado, LocalDate fechaCompra, double monto, double comision, int porcentajeComision) {
        this.codigoJuego = codigoJuego;
        this.correoUsuario = correoUsuario;
        this.instalado = instalado;
        this.fechaCompra = fechaCompra;
        this.monto = monto;
        this.comision = comision;
        this.porcentajeComision = porcentajeComision;
    }
 
    
    
    public void setCodigoJuego(int codigoJuego) {
        this.codigoJuego = codigoJuego;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public void setInstalado(boolean instalado) {
        this.instalado = instalado;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public void setPorcentajeComision(int porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }

    public int getCodigoJuego() {
        return codigoJuego;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public boolean isInstalado() {
        return instalado;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public double getMonto() {
        return monto;
    }

    public double getComision() {
        return comision;
    }

    public int getPorcentajeComision() {
        return porcentajeComision;
    }
    
    
    
    
}
