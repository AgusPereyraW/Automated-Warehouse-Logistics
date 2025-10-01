package warehouse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Warehouse {

    public static void main(String[] args) {
        try {
            File file = new File("resources/input.txt");
            Scanner scanner = new Scanner(file);

            // leer todo el archivo como string
            StringBuilder inputBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                inputBuilder.append(scanner.nextLine()).append("\n");
            }
            scanner.close();

            // ejecutar la simulación
            String output = WarehouseSimulator.simulate(inputBuilder.toString());

            // imprimir resultado
            System.out.println(output);

        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo de entrada.");
            e.printStackTrace();
        }
    }
}

