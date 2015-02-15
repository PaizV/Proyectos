package primos;

import java.util.ArrayList;

/**
 * Esta clase se usa para proveer a los hilos con los candidatos que deben evaluar 
 * y les provee dos listas para guardar los resultados.
 * Una lista para guardar los números primos que sean candidatos a circulares (Que no contengan los números 0,2,4,5,6,8)
 * Y otra lista para guardar los números primos Circulares. 
 * 
 * @author Viviana
 */
public class ListasCompartidas {
    
    //Este contador se usa para proveer a los hilos con los candidatos que deben
    //ser evaluados para ver si son números primos.
    private int contador = 3;
    
    //Este contador se utiliza para recorrer la lista de primos. Este recorrido 
    //se realiza para ver si son o no circulares. 
    private int contadorprimo = 0;
    
    //Lista que guarda los números primos candidatos a circulares.
    private final ArrayList<Integer> primos = new ArrayList<>();
    
    //Lista que guarda los números primos circulares
    private final ArrayList<Integer> primosCirculares = new ArrayList<>();

    /**
     * Provee un número impar a cada hilo que lo invoque, sin repetir números.
     * @return 
     */
    synchronized public int getProximoImpar() {

        //calculo el proximo impar
        int impar = 2 * contador + 1;

        contador++;

        return impar;
    }

    /**
     * Devulve la lista con números primos
     * @return 
     */
    public ArrayList<Integer> getPrimos() {
        return primos;
    }

    /**
     * Devuelve en orden ascendente los números primos calculados y sin devolver 
     * el mismo número a dos hilos distintos.
     * Cuando ya termino de recorrer la lista, devuelve cero.
     * @return
     */
    synchronized public int getProximoPrimo() {
        if (contadorprimo < primos.size()) {
            int proxprimo = primos.get(contadorprimo);
            contadorprimo++;
            return proxprimo;
        }
        return 0;
    }

    /**
     * Inserta en el arreglo de primos el número pasado por parámetro.
     * @param primo 
     */
    synchronized public void agregarPrimo(int primo) {
        this.primos.add(primo);
    }

    /**
     * Devuelve la lista con números primos circulares.
     * @return 
     */
    public ArrayList<Integer> getPrimosCirculares() {
        return primosCirculares;
    }

    /**
     * Inserta en el arreglo de primos circulares el número pasado por parámetro.
     * @param primo 
     */
    synchronized public void agregarPrimoCircular(int primo) {
        this.primosCirculares.add(primo);
    }
}
