import java.util.*;
/**
 * Punto de entrada a la aplicación
 * 
 * @author Gorka
 */
public class AppRevistaJuegosOnLine 
{
    public static void main(String[] args)
    {
        if(args.length != 2){
            System.out.println("Error en argumentos" + 
                "\nSintaxis: java AppRevistaJuegosOnLine <nombre> <n>");
        }else{
           
            String nombre = args[0];
            int total = Integer.parseInt(args[1]);
            RevistaOnLineJuegos revista = new RevistaOnLineJuegos(nombre, total);

            revista.leerDeFichero();

            System.out.println(revista.toString());

            System.out.println("***************************************");
            System.out.println("Puntuando...");
            revista.puntuar("Planet Zoo", 8);
            revista.puntuar("Steep", 7);
            revista.puntuar("Catastronauts", 9);
            revista.puntuar("Wattam", 9);
            System.out.println("Después de puntuar la revista queda\n");

            System.out.println(revista.toString());

            System.out.println("***************************************");
            System.out.println("Juegos con valoración media > 8.2");
            String[] juegosMayorValoracion = revista.valoracionMayorQue(8.2);
            System.out.println(Arrays.toString(juegosMayorValoracion));

            System.out.println("***************************************");
            System.out.println("Borrando juegos de género ROL...");
            System.out.println("Borrados " + revista.borrarDeGenero(Genero.ROL) + " juegos");

            System.out.println(revista.toString());
        }
    }
}