import java.io.*;
import java.util.*;

/**
 * La clase representa a una tienda on-line en la
 * que se publican los juegos que se van lanzando al mercado
 * 
 * Un objeto de esta clase guarda en un array los juegos 
 *
 * @author -Gorka Rodriguez
 */
public class RevistaOnLineJuegos 
{
    private String nombre;
    private Juego[] juegos;
    private int total;

    /**
     * Constructor  
     * Crea el array de juegos al tama�o m�ximo indicado por la constante
     * e inicializa el resto de atributos
     */
    public RevistaOnLineJuegos(String nombre, int n) {
        juegos = new Juego[n];
        this.nombre = nombre;
        total = 0;
    }

    /**
     * Devuelve true si el array est� completo, false en otro caso
     */
     public boolean estaCompleta() {
        return total == juegos.length;
    }

    /**
     *    A�ade un nuevo juego solo si el array no est� completo y no existe otro juego
     *    ya con el mismo nombre.  Si no se puede a�adir se muestra los mensajes adecuados 
     *    (diferentes en cada caso)
     *    
     *    El juego se a�ade de tal forma que queda insertado en orden alfab�tico de t�tulo
     *    (de menor a mayor)
     *     !!OJO!! No hay que ordenar ni utilizar ning�n algoritmo de ordenaci�n
     *    Hay que insertar en orden 
     *    
     */
    public void add(Juego juego) {
        if(estaCompleta()){
            System.out.print("\nNo se ha podido a�adir el juego " + juego.getTitulo()
                                + "  pues la lista ya est� completa");
        }else if(existeJuego(juego.getTitulo()) >= 0){
            System.out.print("\nYa est� publicado el juego " + juego.getTitulo()
                        + " en la revista on-line");
        }else{
            
            int posicion = calcularPosicionTitulo(juego.getTitulo());
            
            System.arraycopy(juegos, posicion, juegos, posicion + 1, total - posicion);
            juegos[posicion] = juego;
            total++;
        }
    }

    /**
     * Devuelve la posici�n en la que deber�a ir un t�tulo dentro del array juegos
     */
    private int calcularPosicionTitulo(String titulo) {
        for(int i = 0; i < total; i++){
            if(juegos[i].getTitulo().compareTo(titulo) > 0){
                return i;
            }
        }
        return total;
    }

    /**
     * Efect�a una b�squeda en el array del juego cuyo titulo se
     * recibe como par�metro. Es indiferente may�sculas y min�sculas
     * Si existe el juego devuelve su posici�n, si no existe devuelve -1
     */
    public int existeJuego(String titulo) {
        for(int i = 0; i < total; i++){
            if(titulo.equalsIgnoreCase(juegos[i].getTitulo())){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Representaci�n textual de la revista
     * Utiliza StringBuilder como clase de apoyo.
     * Se incluye el nombre de la  revista on-line.
     * (Ver resultados de ejecuci�n)
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nLos mejores juegos en nuestra revista " + nombre
                    + " (" + total + " juegos)\n");
        for(int i = 0; i < total; i++){
            sb.append("\n" + juegos[i].toString() + "\n--------------------");
        }
        return sb.toString();
    }

        
    /**
     * Devuelve un array con los nombres de los juegos 
     * con una valoraci�n media mayor a la indicada  
     * 
     * El array se devuelve todo en may�sculas y ordenado ascendentemente
     */
    public String[] valoracionMayorQue(double valoracion) {
        String[] arrayValoracion = new String[total];
        int contador = 0;
        for(int i = 0; i < total; i++){
            if(juegos[i].getValoracionMedia() > valoracion){
                arrayValoracion[contador] = juegos[i].getTitulo();
                contador++;
            }
        }
        return Arrays.copyOf(arrayValoracion, contador);
    }

    /**
     * Borrar los juegos del g�nero indicado devolviendo
     * el n� de juegos borradas
     */
    public int borrarDeGenero(Genero genero) {
        int contador = 0;
        int i = 0;
        while(i < total){
            if(genero == juegos[i].getGenero()){
                borrarJuego(i);
                contador++;
            }else{
                 i++;
            }
        }
        return contador;
    }

    /**
     * Borra un juego de una posicion, y ajusta el array
     */
    private void borrarJuego(int posicion) {
        System.arraycopy(juegos, posicion + 1, juegos, posicion, total - posicion - 1);
        total--;
    }

    
}