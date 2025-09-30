package warehouse;

import warehouse.data.Coordinate;
import warehouse.data.Grid;
import warehouse.data.Robot;

import java.util.Scanner;

public class WarehouseSimulator {

    public static String simulate(String input) {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(input);

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

        // robots
        while (scanner.hasNext()) {
            int startX = scanner.nextInt();
            int startY = scanner.nextInt();
            String instrucciones = scanner.next();

            Robot robot = new Robot(new Coordinate(startX, startY), grid);

            for (char instr : instrucciones.toCharArray()) {
                robot.move(instr);
                if (robot.isLost()) break;
            }

            result.append(robot.getReport()).append("\n");
        }
        scanner.close();
        return result.toString().trim();
    }
}
