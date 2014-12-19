package cogedatos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

public class PruebaContenedor {

    public static void main(String[] args) {
        ContenedorDeEnteros a = new ContenedorDeEnteros();
        int[] vec = new int[100000];
        int count = 0;
        double timeS;
        double timeF;
        FileWriter fichesc;
        // Formato de tres decimales.
        DecimalFormat dform = new DecimalFormat("#.###");

        try {
            fichesc = new FileWriter("salida1.txt");
            RandomAccessFile fichero = new RandomAccessFile("datos.dat", "r");

            // Rellenamos el vector con los datos de datos.dat.
            while (fichero.getFilePointer() < fichero.length()) {
                vec[count] = fichero.readInt();
                count++;
            }
            fichero.close();

            //PARTE INSERCION
            // Escribimos en el fichero los tiempos de inserción
            fichesc.write("·->Insertar en el contenedor 10000 elementos\r\n");
            System.out.println("Insertar en el contenedor 10000 elementos");
            for (int j = 0; j < 10; j++) {
                timeS = System.currentTimeMillis();
                for (int i = 0; i < 10000; i++) {
                    a.insertar(vec[i]);
                }
                timeF = System.currentTimeMillis();
                fichesc.write("Tiempo de inserción " + j + ": " + dform.format(timeF - timeS) + " ms\r\n");
                System.out.println("Tiempo de inserción " + j + ": " + dform.format(timeF - timeS) + " ms");
                a.vaciar();
            }

            //PARTE EXTRACCION
            // Escribimos en el fichero los tiempos de extracción
            fichesc.write("\r\n\r\n·->Extraer en el contenedor 10000 elementos\r\n");
            System.out.println("Extraer en el contenedor 10000 elementos");
            for (int i = 0; i < 100000; i++) {
                a.insertar(vec[i]);

            }
            for (int j = 0; j < 10; j++) {
                timeS = System.currentTimeMillis();
                for (int i = 0; i < 100000; i++) {
                    a.extraer(vec[i]);
                }
                timeF = System.currentTimeMillis();
                fichesc.write("Tiempo de extracción " + j + ": " + dform.format(timeF - timeS) + " ms\r\n");
                System.out.println("Tiempo de extracción " + j + ": " + dform.format(timeF - timeS) + " ms");
            }

            fichesc.close();
        } catch (IOException e) {
            System.out.println("Error al leer o escribir el fichero: " + e.getMessage());
        }
    }
}