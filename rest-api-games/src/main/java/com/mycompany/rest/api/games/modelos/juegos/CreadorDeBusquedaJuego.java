/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.juegos;

/**
 *
 * @author edu
 */
public class CreadorDeBusquedaJuego {

    private static final String BUSQUEDA_BASE = "select *from juego where 1=1";
    private static final String FILTRO_NOMBRE = " and juego_nombre = ?";
    private static final String FILTRO_PRECIO = " and juego_precio >= ? and juego_precio <= ?";
    private static final String FILTRO_EMPRESA = " and juego_codigo_empresa = ?";

    private int idEmpresa;
    private BusquedaJuego search;

    private boolean filtraNombre = false;
    private boolean filtraPrecio = false;
    private boolean filtraEmpresa = false;

    // final
    private String busquedaFinal;

    public CreadorDeBusquedaJuego(BusquedaJuego busqeuda) {
        this.search = busqeuda;
    }

    public String armarBusqueda() {
        busquedaFinal = BUSQUEDA_BASE; // SI NO HAY FILTROS 

        if (search.getNombre() != null) {
            busquedaFinal += FILTRO_NOMBRE;
            filtraNombre = true;
        }

        if (search.getPrecioMinimo() > 0 && search.getPrecioMaximo() > search.getPrecioMinimo()) {
            busquedaFinal += FILTRO_PRECIO;
            filtraPrecio = true;
        }

        if (idEmpresa > 0) {
            busquedaFinal += FILTRO_EMPRESA;
            filtraEmpresa = true;
        }
        return busquedaFinal;
    }

    public BusquedaJuego getSearch() {
        return search;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public boolean isFiltraNombre() {
        return filtraNombre;
    }

    public boolean isFiltraPrecio() {
        return filtraPrecio;
    }

    public boolean isFiltraEmpresa() {
        return filtraEmpresa;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public String getBusquedaFinal() {
        return busquedaFinal;
    }

}
