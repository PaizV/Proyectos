package primos;

/**
 * Hilo que calcula primos circulares.
 * Para calcularlos primero crea una lista con candidatos a primos circulares,
 * Un resultado matemático indica que los números primos circulares  no pueden contener los dígitos 0,2,4,5,6 y 8,
 * por lo tanto dichos números no se evalúan.
 * Una vez que se obtiene la lista con candidatos, se realiza el cálculo para ver si son circulares.
 * 
 * @author Viviana
 */
public class CalculadorPrimosCirculares implements Runnable {

    //Listas que comparte con los demas hilos
    ListasCompartidas listasCompartida;
    
    /**
     *  Constructor. Setea la lista compartida.
     * @param primos 
     */
    public CalculadorPrimosCirculares(ListasCompartidas primos) {
        this.listasCompartida = primos;
    }
    
    /**
     * Comienzo de ejecución del hilo.
     */
    @Override
    public void run() {
        
        Integer impar;
        
        //Representación del impar como un String
        String impar_string;
        
        do {     
            //Obtengo un num impar para verificar si es primo.
            impar = listasCompartida.getProximoImpar();          

            //Transformo el impar a string para comprobar que no contenga los digitos 0,2,4,6,8 ni 5
            //ya que solo me interesan los primos circulares.
            impar_string = impar.toString();
            if (!(impar_string.contains("0") || impar_string.contains("2") || impar_string.contains("4") || impar_string.contains("6") || impar_string.contains("8") || impar_string.contains("5"))) {

                //Verifico si es primo.
                boolean esprimo = es_primo(impar);
                
                //Si es primo lo inserto en la lista.
                if ((esprimo)) {
                    listasCompartida.agregarPrimo(impar);
                }
            }
        } while (impar < 1000000);//Hasta calcular los primos debajo de 1 millón.

      
        //Recorro el arreglo de primos y me quedo con los que son circulares.
        for (Integer candidato = listasCompartida.getProximoPrimo(); candidato != 0; candidato = listasCompartida.getProximoPrimo()) {

            //Obtendo la representación en String del candidato para poder rotarlo.
            String candidato_string = candidato.toString();
            
            int j;
            int num;
            
            //Transformo el num primo a string y lo voy rotando mientras verifico que sea primo
            for (j = 1,num = candidato; j < candidato_string.length() && (listasCompartida.getPrimos().contains(num)); j++) {
                
                //Hago rotar el string.
                candidato_string = candidato_string.substring(1, candidato_string.length()) + candidato_string.charAt(0);      
                
                //Transformo el número rotado a entero para poder comprobar si es primo.
                num = Integer.parseInt(candidato_string);

            }
            
            //Si todas las rotaciones son primas lo inserta en la lista de primos circulares.
            if ((j == candidato_string.length()) && (listasCompartida.getPrimos().contains(num))) {
                listasCompartida.agregarPrimoCircular(candidato);
            }
        }
    }

    /**
     * Determina si un número es o no primo.
     * @param num
     * @return true si es primo, false en otro caso.
     */
    public boolean es_primo(int num) {

        //Si el número tiene algun divisor este se encuentra debajo de su raíz cuadrada.
        double limite = Math.sqrt(num);
        
        //Busco los divisores del numero candidato a primo. 
        for (int i = 2; i <= limite; i++) {
            
            //Si existe algun divisor es por que no es primo.
            if ((num % i) == 0) {
                return false;
            }
        }
        
        //Si no encontré ningún divisor es por que el número es primo.
        return true;
    }
}
