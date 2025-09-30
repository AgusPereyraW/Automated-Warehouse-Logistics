package warehouse;

import java.util.Scanner;
import warehouse.data.Coordinate;
import warehouse.data.Grid;
import warehouse.data.Robot;
import java.io.File;
import java.io.FileNotFoundException;

public class Warehouse {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        try {
            // archivo de entrada
            File file = new File("resources/input.txt");
            Scanner scanner = new Scanner(file);

            // tamaño de la grilla
            int maxX = scanner.nextInt();
            int maxY = scanner.nextInt();

            // cantidad de obstáculos
            int numObstacles = scanner.nextInt();
            Grid grid = new Grid(maxX, maxY, numObstacles);

            // coordenadas de obstáculos
            for (int i = 0; i < numObstacles; i++) {
                int ox = scanner.nextInt();
                int oy = scanner.nextInt();
                grid.addObstacle(new Coordinate(ox, oy));
            }

            // robots (posición inicial e instrucciones)
            while (scanner.hasNext()) {
                int startX = scanner.nextInt();
                int startY = scanner.nextInt();
                String instrucciones = scanner.next();

                Robot robot = new Robot(new Coordinate(startX, startY), grid);

                // ejecutar cada instruccion
                for (char instr : instrucciones.toCharArray()) {
                    robot.move(instr);
                    if (robot.isLost()) {
                        break; // si se perdió, no sigue
                    }
                }

                // reporte final del robot
                System.out.println(robot.getReport());
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo de entrada.");
            e.printStackTrace();
        }
    }
}
