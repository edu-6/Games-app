/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db;
import com.mycompany.rest.api.games.modelos.compras.CompraJuego;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author edu
 */
public class ComprasDB {

    private static final String ENCONTRAR_CLASIFICACION_JUEGO = "SELECT clasificacion_edad from juego where juego_id = ?";
    private static final String BUSCAR_FECHA_NACIMIENTO_USUARIO = "select gamer_fecha_nacimiento from gamer where gamer_correo = ? ";
    private static final String BUSCAR_COMPRA = "select * from compra_juego where compra_codigo_videojuego = ? and compra_correo_usuario = ?";
    private static final String OBTENER_ID_JUEGO_POR_NOMBRE = "SELECT juego_id from juego where juego_nombre = ?";
    private static final String OBTENER_PRECIO = "SELECT juego_precio from juego where juego_id = ?";
    private static final String ENCONTRAR_COMISION = "SELECT porcentaje_comision FROM tabla_configuracion WHERE id = 1";
    private static final String OBTENER_SALDO_POR_CORREO = "SELECT saldo FROM cartera_gamer WHERE correo_gamer = ?";
    private static final String ACTUALIZAR_SALDO = "UPDATE cartera_gamer SET saldo = ? WHERE correo_gamer = ?";
    private static final String REGISTRAR_COMPRA = "INSERT INTO compra_juego"
            + " (compra_codigo_videojuego, compra_correo_usuario, compra_monto, compra_comision, compra_porcentaje_comision, fecha_compra, instalado)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?)";

    public int obtenerPorcentajeComision() throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(ENCONTRAR_COMISION)) {
            ResultSet result = insert.executeQuery();
            if (result.next()) {
                return result.getInt("porcentaje_comision");
            }
        }
        return -1;
    }

    public double obtenerSaldoGamer(String correo) throws SQLException {
        double saldo = -1;
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(OBTENER_SALDO_POR_CORREO)) {
            insert.setString(1, correo); // Aquí se asigna el correo al "?"

            ResultSet result = insert.executeQuery();
            if (result.next()) {
                saldo = result.getDouble("saldo");
            }
        }
        return saldo;
    }

    public void actualizarSaldoGamer(double monto, String correo) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(ACTUALIZAR_SALDO)) {

            insert.setDouble(1, monto);
            insert.setString(2, correo);
            insert.executeUpdate();
        }
    }

    public void registrarCompra(CompraJuego compra) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(REGISTRAR_COMPRA)) {

            insert.setInt(1, compra.getCodigoJuego());
            insert.setString(2, compra.getCorreoUsuario());
            insert.setDouble(3, compra.getMonto());
            insert.setDouble(4, compra.getComision());
            insert.setInt(5, compra.getPorcentajeComision());
            insert.setDate(6, java.sql.Date.valueOf(compra.getFechaCompra()));
            insert.setBoolean(7, compra.isInstalado());
            insert.executeUpdate();
        }
    }

    public double obtenerPrecioJuego(int codigoJuego) throws SQLException {
        double precio = -1;
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(OBTENER_PRECIO)) {
            insert.setInt(1, codigoJuego);
            ResultSet result = insert.executeQuery();
            if (result.next()) {
                precio = result.getDouble("juego_precio");
            }
        }
        return precio;
    }

    public int obtenerIdJuegoPorNombre(String nombreJuego) throws SQLException {
        int id = -1;
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(OBTENER_ID_JUEGO_POR_NOMBRE)) {
            insert.setString(1, nombreJuego);
            ResultSet result = insert.executeQuery();
            if (result.next()) {
                id = result.getInt("juego_id");
            }
        }
        return id;
    }

    public boolean existeCompra(int id_juego, String correo) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(BUSCAR_COMPRA)) {
            insert.setInt(1, id_juego);
            insert.setString(2, correo);
            ResultSet result = insert.executeQuery();
            return result.next();
        }
    }
    
    public boolean gamerEsMayorDeEdad(String correo) throws SQLException{
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(BUSCAR_FECHA_NACIMIENTO_USUARIO)) {
            insert.setString(1, correo);
            ResultSet result = insert.executeQuery();
            if(result.next()){
                LocalDate fechaNacimiento = result.getDate("gamer_fecha_nacimiento").toLocalDate();
                LocalDate fechaHoy = LocalDate.now();
                Period periodo = Period.between(fechaNacimiento, fechaHoy);
                int edad = periodo.getYears();
                return edad >= 18;
            }
            return false;
        }
    }
    
    
    public boolean juegoEsParaAdultos(int idJuego) throws SQLException{
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(ENCONTRAR_CLASIFICACION_JUEGO)) {
            insert.setInt(1, idJuego);
            ResultSet result = insert.executeQuery();
            if(result.next()){
                String categoria = result.getString("clasificacion_edad");
                if(categoria.equals("M")){
                    return true;
                }
            }
            return false;
        }
    }
    
    
    

}
