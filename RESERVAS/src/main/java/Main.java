import Model.Recurso;
import View.HorarioView;
import View.RecursoView;
import View.UsuarioView;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = false;
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
            System.out.println("6. Salir");

            int opcion = 0;
            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("ERROR, solo números enteros");
                continuar = false;
                sc.nextLine();
            }

            switch (opcion) {

                case 1 -> {
                    try {
                        UsuarioView.programaUsuario();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 2 -> {
                    try {
                        RecursoView.programaRecurso();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3 -> {

                    break;
                }
                case 4 -> {


                    break;
                }
                case 5 -> {
                    try {
                        HorarioView.programaHorario();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 6 -> {
                    System.out.println("HASTA LUEGO!!");
                    continuar = true;
                    break;
                }


            }
        } while (!continuar);
    }
}
