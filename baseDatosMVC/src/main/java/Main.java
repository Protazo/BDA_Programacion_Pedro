import Model.Recurso;
import View.RecursoView;
import View.UsuarioView;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        do {
            System.out.println("==========================");
            System.out.println("    SISTEMA DE GESTIÓN    ");
            System.out.println("==========================");
            System.out.println("A qué tabla quieres acceder: ");
            System.out.println("1. Usuarios");
            System.out.println("2. Recurso");
            System.out.println("3. Reserva");
            System.out.println("4. Disponible en");
            System.out.println("5. Horarios");

            int opcion = 0;
            try {
                opcion = sc.nextInt();
                continuar = true;
            } catch (InputMismatchException e) {
                System.out.println("ERROR, solo números enteros");
                continuar = false;
            }

            switch (opcion) {

                case 1 -> {
                    try {
                        UsuarioView.main();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        RecursoView.programaRecurso();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {

                }
                case 4 -> {

                }
                case 5 -> {

                }



            }
        } while (!continuar);
    }
}
