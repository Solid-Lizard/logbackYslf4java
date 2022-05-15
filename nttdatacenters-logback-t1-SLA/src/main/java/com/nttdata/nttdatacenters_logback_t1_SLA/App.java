package com.nttdata.nttdatacenters_logback_t1_SLA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/* DESCRIPCIÓN DEL EJERCICIO */

/*Creamos un programa que, dados un nombre, dirección y una serie de parámetros, calcula una factura, 
  y imprime los datos en un archivo llamado "factura.txt", ubicado en "./Factura"
 */


public class App {	
	private final static Logger log = LoggerFactory.getLogger(App.class);
	
	private static boolean endsWithLetter (String str) {
		
		log.info("INICIO DEL MÉTODO, COMPROBANDO LA CADENA: {}", str);
		
		boolean resultado = false;		
		char[] abecedario = new char[26];
		
		for (int i = 0; i<26; i++) {			 
			 int ascii = 65 + i;
			 
			 abecedario[i] = (char) ascii;
			 			 			
		 }
		
		log.info("TABLA ABECEDARIO RELLENADA CORRECTAMENTE, VALORES: {}", abecedario);
		
		for (char letra : abecedario) {
			if (str.endsWith(Character.toString(letra))) {
				resultado = true;
				break;
			}
			
		}
		
		log.info("MÉTODO FINALIZADO, VALOR DEVUELTO: {}", resultado);
		
		return resultado;			
		
	}
	
    public static void main( String[] args ) {
    	
    	log.info("INICIO DE TRAZA");
    	    	
    	// Objetos //
    	Scanner sc = new Scanner (System.in);   
    	FileWriter factura;    	    	
    	    	
    	
    	// Variables //
    	double cantidad, luz, agua, gas, total;
    	String nombre, calle, NIF;    	    
    	    	
    	//Luz, agua, gas, teléfono
    	
    	//Tablas
    	double [] costoPorUnidad = {1.91, 1.16, 0.14};    	
    	
		log.info("OBJETOS Y VARIABLES DECLARADOS E INICIALIZADOS CORRECTAMENTE");
    	
    	// Instrucciones //										 				 		 						 		
		try {			
			System.out.println("Introduzca su nombre");
			nombre = sc.nextLine();
			log.info("VALOR DE NOMBRE: {}", nombre);
			
			System.out.println("Introduzca su calle");
			calle = sc.nextLine();
			log.info("VALOR DE CALLE: {}", calle);			
			
			System.out.println("Introduzca su NIF");
			NIF = sc.nextLine();			
			
			while (NIF.length() !=9 || endsWithLetter(NIF.toUpperCase()) == false) {
				System.out.println("Error, el NIF se compone de 8 caracteres numéticos seguido de un carácter alfabético");
				System.out.println("Vuelva a introducir el NIF porfavor");
				NIF = sc.nextLine();				
			}
			
			log.info("VALOR DE NIF: {}", NIF);
			
			factura = new FileWriter("factura.txt", false);
			factura.write("----------RESUMEN DE GASTOS MENSUALES---------- \n");
			factura.write("              -DATOS DEL CLIENTE- \n");
			factura.write("--Nombre del cliente: " + nombre + "\n");
			factura.write("--Residencia: " + calle + "\n");
			factura.write("--NIF: " + NIF + "\n");
			factura.write("----------------------------------------------- \n");
			
			log.info("Datos del cliente implementados correctamente en factura.txt");
			
			System.out.println("Introduzca, en orden, la cantidad de kWH que consume de luz, los m3 que consume de agua, y los kW que consume de gas en un día");
			cantidad = sc.nextDouble();			
			luz = (cantidad*30) * costoPorUnidad[0];
			
			factura.write("             -DATOS DE LA FACTURA- \n");
			factura.write("---Consumo de luz--- \n");
			factura.write("-KWh consumidos de media al día: " + cantidad + "\n");
			factura.write("-KWh comsumidos al mes " + cantidad*30 + "\n");
			factura.write("-Precio a pagar: " + luz + "\n");
			
			factura.write("\n");
			
			log.info("Datos de consumo de luz introducidos, consumo: {} -precio: {}", cantidad, luz);
			
			cantidad = sc.nextDouble();			
			agua = cantidad* costoPorUnidad[1];
						
			factura.write("---Consumo de agua--- \n");
			factura.write("-m3 consumidos de media al día: " + cantidad + "\n");
			factura.write("-m3 comsumidos al mes " + cantidad*30 + "\n");
			factura.write("-Precio a pagar: " + agua + "\n");
			
			factura.write("\n");
			
			log.info("Datos de consumo de agua introducidos, consumo: {} -precio: {}", cantidad, agua);
			
			cantidad = sc.nextDouble();			
			gas = cantidad* costoPorUnidad[2];
					
			factura.write("---Consumo de gas--- \n");
			factura.write("-KWh consumidos de media al día: " + cantidad + "\n");
			factura.write("-KWh comsumidos al mes " + cantidad*30 + "\n");
			factura.write("-Precio a pagar: " + gas + "\n");					
			
			log.info("Datos de consumo de gas introducidos, consumo: {} -precio: {}", cantidad, gas);
			
			total = luz + agua + gas;
			
			factura.write("----------------------------------------------- \n");
			factura.write("--Total a pagar: " + total +"€" + "\n");
			factura.write("----------------------------------------------- \n");
			
			log.info("Datos totales de consumo introducido, valor de total: " + total);
			
			factura.close();
			
			log.info("Flujo de entrada cerrado correctamente, comprobar el archivo: factura.txt");
    	
		} catch (InputMismatchException ex) {
			log.error("Se ha introducido un dato que no es del tipo requerido, informe de error: {}", ex.toString());
			
		} catch (IOException ex) {
			log.error("Error, se ha producido un error a la hora de utilizar el flujo de salida, informe de error: {}", ex.toString());
		}
		
		log.info("FIN DE TRAZA");
		
    }
}
