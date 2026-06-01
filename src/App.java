import controllers.SortPersonaMethods;
import models.Persona;
import models.Resultado;

public class App {
    public static Persona[] generarPersonas(int cantidad){
        Persona[] personas = new Persona[cantidad];
        for(int i=0; i<cantidad; i++){
            String nombre = "Persona " + (i+1);
            int edad = (int)(Math.random() * 101);
            personas[i]= new Persona(nombre, edad);
        }    
        return personas;
    }
    public static void main(String[] args) throws Exception {
        SortPersonaMethods sorter = new SortPersonaMethods();
        int[] cantidades = {10000, 50000, 100000};

        for (int c : cantidades) {
            Persona[] base = generarPersonas(c);

            Persona[] paraInsercion = base.clone();
            Persona[] paraQuick = base.clone();

            Resultado resInsercion = BenchMarking.medirTiempo(
                () -> { sorter.insertionSort(paraInsercion); return null; },
                "Inserción", "Desordenado", c
            );

            Resultado resQuick = BenchMarking.medirTiempo(
                () -> { sorter.quickSort(paraQuick, 0, paraQuick.length - 1); return null; },
                "QuickSort", "Desordenado", c
            );

            System.out.println("Desordenado | Inserción | " + resInsercion.getSample() + " | " + resInsercion.getTiempoMilis() + " ms");
            System.out.println("Desordenado | QuickSort | " + resQuick.getSample() + " | " + resQuick.getTiempoMilis() + " ms");
    }
    
}
}