import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { LoginServicio } from '../../services/login-service';
import { UsuarioLogin } from '../../models/login/UsuarioLogin';
import { UsuarioSesion } from '../../models/login/UsuarioSesion';

@Component({
  selector: 'app-login-form',
  imports: [RouterLink, FormsModule, ReactiveFormsModule],
  templateUrl: './login-form.html',
  styleUrl: './login-form.css',
})
export class LoginForm implements OnInit {

  formulario !: FormGroup;
  usuarioLogin !: UsuarioLogin;

  loginFallido: boolean = false;

  constructor(private formBuilder: FormBuilder, private loginServicio: LoginServicio, private router: Router) {

  }


  ngOnInit(): void {
    this.formulario = this.formBuilder.group({
      correo: ['', [Validators.required, Validators.maxLength(100)]],
      contraseña: ['', [Validators.required, Validators.maxLength(100)]]

    });
  }

  resetearFormulario(): void {
    this.formulario.reset({
      usuario: '',
      contraseña: ''
    });
  }


  enviarFormulario(): void {
    if (this.formulario.valid) {
      this.usuarioLogin = this.formulario.value as UsuarioLogin;
      this.loginServicio.loguearUsuario(this.usuarioLogin).subscribe({
        next: (usuario: UsuarioSesion) => {
          // hacer set a los datos para la sesión
          localStorage.setItem('usuario_correo', usuario.correo);
          localStorage.setItem('usuario_rol', usuario.rol);

          // redirigir a la pagina principal
          this.router.navigate(["/inicio"]);
          
        },
        error: (error: any) => {
          if (error.error) {
            console.log('Eror encontrado:', error.error);
          }
          this.resetearFormulario();
          this.loginFallido = true;
        }
      });
    }

  }
}
