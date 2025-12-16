
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.servicios;

import com.mycompany.rest.api.games.db.LoginDB;
import com.mycompany.rest.api.games.exceptions.CredencialesIncorrectasException;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.modelos.usuarios.UsuarioLogin;
import com.mycompany.rest.api.games.modelos.usuarios.UsuarioSesion;

/**
 *
 * @author edu
 */
public class LoginService {
    
    
    public UsuarioSesion loguearUsuario(UsuarioLogin usuarioLogin) throws CredencialesIncorrectasException, DatosInvalidosException{
        LoginDB db = new LoginDB();
        
        if(!usuarioLogin.valido()){
            throw new DatosInvalidosException("ingrese correo y contraseña");
        }
        
        if(db.existeAdminSistema(usuarioLogin)){
            return new UsuarioSesion(usuarioLogin.getCorreo(), "ADMIN_SISTEMA");
        }
        if(db.existeAdminEmpresa(usuarioLogin)){
            return new UsuarioSesion(usuarioLogin.getCorreo(), "ADMIN_EMPRESA");
        }
        
        if(db.existeGamer(usuarioLogin)){
            return new UsuarioSesion(usuarioLogin.getCorreo(), "GAMER");
        }
        
        throw new CredencialesIncorrectasException();
    }
    
}
