import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    public static void main(String[] args) {
        char[][] matriz = new char[10][10];
        Random aleatorio = new Random();
        Scanner teclado = new Scanner(System.in);
        int vidas = 10;

        for (int i = 0; i < matriz.length; ++i) {
            for (int j = 0; j < matriz.length; ++j) {
                matriz[i][j] = '*';
            }
        }

        char ROBOT = 'R';
        matriz[0][0] = ROBOT;
        char FINAL = 'F';
        matriz[9][9] = FINAL;

        int fila_aleat;
        int columna_aleat;

        do {
            fila_aleat = aleatorio.nextInt(10);
            columna_aleat = aleatorio.nextInt(10);
        } while (matriz[fila_aleat][columna_aleat] != '*');

        matriz[fila_aleat][columna_aleat] = 'B';

        for (int i = 0; i < 5; ++i) {
            do {
                fila_aleat = aleatorio.nextInt(10);
                columna_aleat = aleatorio.nextInt(10);
            } while (matriz[fila_aleat][columna_aleat] != '*');

            matriz[fila_aleat][columna_aleat] = 'X';
        }

        int filaRobot = 0;
        int columnaRobot = 0;

        System.out.println("Tablero inicial:");

        for (int i = 0; i < matriz.length; ++i) {
            for (int j = 0; j < matriz.length; ++j) {
                System.out.print(matriz[i][j] + "  ");
            }
            System.out.println();
        }

        System.out.println("Tu jugador se encuentra en la casilla (0,0). Te puedes desplazar con las teclas W A S D");

        while (true) {
            System.out.println("Vidas: " + vidas);
            System.out.println("Dime una letra: ");

            char desplazamiento = teclado.next().charAt(0);

            int filaAnterior = filaRobot;
            int columnaAnterior = columnaRobot;

            boolean printTablero = true;

            if (desplazamiento == 'D') {
                columnaRobot++;
            } else if (desplazamiento == 'S') {
                filaRobot++;
            } else if (desplazamiento == 'W') {
                filaRobot--;
            } else if (desplazamiento == 'A') {
                columnaRobot--;
            } else {
                System.out.println("Letra no valida");
                printTablero = false;
            }

            if (printTablero) {
                if (filaRobot >= 0 && filaRobot <= 9
                        && columnaRobot >= 0 && columnaRobot <= 9) {

                    if (matriz[filaRobot][columnaRobot] == 'X') {
                        System.out.println("Movimiento bloqueado");
                        filaRobot = filaAnterior;
                        columnaRobot = columnaAnterior;

                    } else {
                        if (matriz[filaRobot][columnaRobot] == 'B') {
                            vidas = vidas + 2;
                            System.out.println("Bateria recogida. +2 vidas");
                        }

                        if (matriz[filaRobot][columnaRobot] == 'F') {
                            matriz[filaAnterior][columnaAnterior] = '*';
                            matriz[filaRobot][columnaRobot] = ROBOT;

                            System.out.println("Rescate completado");
                            break;
                        }

                        matriz[filaAnterior][columnaAnterior] = '*';
                        matriz[filaRobot][columnaRobot] = ROBOT;
                    }

                } else {
                    System.out.println("Fuera de limites");
                    filaRobot = filaAnterior;
                    columnaRobot = columnaAnterior;
                }
            }

            if (vidas <= 0) {
                System.out.println("Te has quedado sin vidas");
                break;
            }

            if (printTablero) {
                for (int i = 0; i < matriz.length; ++i) {
                    for (int j = 0; j < matriz.length; ++j) {
                        System.out.print(matriz[i][j] + "  ");
                    }
                    System.out.println();
                }
            }
        }

        System.out.println("Tablero final:");

        for (int i = 0; i < matriz.length; ++i) {
            for (int j = 0; j < matriz.length; ++j) {
                System.out.print(matriz[i][j] + "  ");
            }
            System.out.println();
        }

        System.out.println("Posicion final: (" + filaRobot + "," + columnaRobot + ")");
        System.out.println("Vidas finales: " + vidas);

        teclado.close();
    }
}
