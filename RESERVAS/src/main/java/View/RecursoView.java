package View;

import Controller.RecursoController;
import Dao.DBConnection;
import Model.Recurso;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class RecursoView {

    public static void programaRecurso() throws SQLException {
        RecursoController controller = new RecursoController();
        boolean continuar = false;
        if (DBConnection.getConnection() != null) {

            while (!continuar) {
                menu();

                System.out.println("Elige una opcion: (11 para el menú) ");
                Scanner sc = new Scanner(System.in);
                try {
                    int opcion = sc.nextInt();
                    sc.nextLine();
                    switch (opcion) {
                        case 1: {
                            boolean todobien  = false;
                            while (!todobien) {
                                try {
                                    System.out.println("Escribe el nombre del recurso que quieres añadir: ");
                                    String nombre = sc.nextLine();
                                    System.out.println("Escribe la descripción del recurso: ");
                                    String desc = sc.nextLine();
                                    System.out.println("Escribe la ubicación donde está el recurso: ");
                                    String ubicacion = sc.nextLine();
                                    System.out.println("Escribe la capacidad que tiene el recurso: ");
                                    int capacidad = sc.nextInt();
                                    controller.altaRecurso(nombre, desc, ubicacion, capacidad);
                                    todobien = true;
                                    break;
                                } catch (InputMismatchException e) {
                                    todobien = false;
                                    break;

                                }
                            }
                            break;

                        }
                        case 2: {
                            boolean todobien = false;
                            while (!todobien) {
                                try {
                                    System.out.println("Escribe el id del recurso que quieres eliminar: ");
                                    int id = sc.nextInt();

                                    controller.bajaRecurso(id);
                                    todobien = true;
                                    break;
                                } catch (InputMismatchException e) {
                                    todobien = false;
                                }
                            }
                            break;

                        }
                        case 3: {
                            boolean todobien = false;
                            while (!todobien) {
                                try {
                                    System.out.println("Escribe el id del recurso que quieres modificar: ");
                                    String numero = sc.nextLine();
                                    int id = Integer.parseInt(numero);

                                    System.out.println("Escribe el nuevo nombre del recurso: ");
                                    String nombre = sc.nextLine();
                                    System.out.println("Escribe la nueva descripción del recurso: ");
                                    String descripcion = sc.nextLine();
                                    System.out.println("Escribe la nueva ubicación del recurso: ");
                                    String ubicacion = sc.nextLine();
                                    System.out.println("Escribe la nueva capacidad del recurso: ");
                                    int capacidad = sc.nextInt();

                                    Recurso r = controller.modificarRecurso(id, nombre, descripcion, ubicacion, capacidad);
                                    System.out.println(r.toString());

                                    todobien = true;

                                    break;
                                } catch (InputMismatchException e) {
                                    todobien = false;
                                }
                            }
                            break;
                        }
                        case 4: {
                            List<Recurso> lista = controller.listarRecursos();
                            lista.forEach(System.out::println);
                            break;
                        }
                        case 5: {
                            System.out.println("Escribe el nombre del recurso que quieres buscar: ");
                            String nombre = sc.nextLine();

                            Recurso r = controller.buscarPorNombreRecurso(nombre);
                            System.out.println(r.toString());
                            break;
                        }
                        case 0: {
                            System.out.println("HASTA LUEGO!!");
                            continuar = true;
                            break;
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Solo números enteros.");
                }
            }
        }
    }

    public static void menu() {
        System.out.println("===============================");
        System.out.println("SISTEMA DE GESTIÓN DE PERSONAS");
        System.out.println("===============================");
        System.out.println("0. Salir");
        System.out.println("1. Dar de alta un recurso.");
        System.out.println("2. Dar de baja un recurso.");
        System.out.println("3. Modificar un recurso.");
        System.out.println("4. Listar los recursos.");
        System.out.println("5. Buscar por nombre un recurso.");
    }

}