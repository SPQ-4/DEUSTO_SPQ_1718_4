package PresentacionCobertura.Cobertura;

import java.util.Scanner;

public class Calculo {
	String usuario="Paco";
	String contrasenya="password";
	public Calculo() {
		/*System.out.println("Hellow");
		Scanner leer=new Scanner(System.in);
		System.out.println("Introduce usuario");
		String entradaUsuario=leer.next();
		System.out.println("Introduce contraseña");
		String entradaUsuario2=leer.next();*/
		
	}
	public String comprobar(String entradaUsuario,String entradaUsuario2) {
		String resultado;
		if(usuario.equals(entradaUsuario) && contrasenya.equals(entradaUsuario2)) {
			resultado="permitido";
			System.out.println("Acceso permitido");
		}else {
			resultado="denegado";
			System.out.println("Acceso denegado");
		}
		return resultado;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculo calculo=new Calculo();
	}
}	
