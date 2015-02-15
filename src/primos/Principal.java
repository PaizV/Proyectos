package primos;

import Interface.InterfaceUsuario;
import java.util.Collections;

/**
 *
 * @author Viviana
 */
public class Principal {

    /**
     * Comienzo de ejecución.
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        
        //Arreglo para guardar los hilos a lanzar
        Thread hilos[];
        
        //Objeto utilizado para compartir información entre los hilos.
        ListasCompartidas listasCompartidas = new ListasCompartidas();
        
        //Inicializo el arreglo que guarda los primos calculados.
        //Se insertan los números 2,3 y 5 por que el método que calcula primos no
        //contempla los números que contenga dichos dígitos.
        listasCompartidas.agregarPrimo(2);
        listasCompartidas.agregarPrimo(3);
        listasCompartidas.agregarPrimo(5);
        
        //Obtengo la cantidad de procesadores asignados a la máquina virtual.
        int cantProcesadores = Runtime.getRuntime().availableProcessors();
        
        //Inicializo el arreglo que contendrá los hilos lanzados.
        hilos = new Thread[cantProcesadores];
        
        //Creo tantos hilos como procesadores tenga.
        for(int i = 0; i < cantProcesadores; i++){
            CalculadorPrimosCirculares calcularPrimo = new CalculadorPrimosCirculares(listasCompartidas);
            Thread hilo = new Thread(calcularPrimo);
            hilos[i] = hilo;
            hilo.setName("Hilo: " + i);
            hilo.start();
        }
        
       //Espero que terminen de ejecutar todos los hilos.
        for(int i = 0; i < hilos.length; i++) {
            hilos[i].join();
        }
        
       //Ordeno la lista de primos circulares.
        Collections.sort(listasCompartidas.getPrimosCirculares());
       
        //Despliego la interface que muestra la lista.
        InterfaceUsuario ventana = new InterfaceUsuario(listasCompartidas.getPrimosCirculares());
        ventana.setVisible(true);
    }
}
