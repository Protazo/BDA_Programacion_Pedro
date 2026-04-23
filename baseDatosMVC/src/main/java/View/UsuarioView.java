package View;

import Controller.UsuarioController;
import Dao.DBConnection;
import Model.Recurso;
import Model.tipoUsu;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UsuarioView {

    public static void main() throws SQLException {
        UsuarioController controller = new UsuarioController();
        boolean continuar = true;
        if (DBConnection.getConnection() != null) {
            menu();

            while (!continuar) {
                System.out.println("Elige una opcion: ");
                Scanner sc = new Scanner(System.in);
                try {
                    int opcion = sc.nextInt();
                    sc.nextLine();
                    switch (opcion) {
                        case 1: {
                            boolean todobien  = false;
                            while (!todobien) {
                                try {
                                    System.out.println("Introduce el nombre del usuario: ");
                                    String nombre = sc.nextLine();
                                    System.out.println("Introduce el email del usuario: ");
                                    String email = sc.nextLine();
                                    System.out.println("Introduce la contrasea del usuario: ");
                                    String contrasenya = sc.nextLine();
                                    System.out.println("Introduce la fe nacimiento del usuario: ");
                                    String fecha = sc.nextLine();
                                    System.out.println("Introduce el tipo de usuario (ADMIN/NORMAL): ");
                                    String tipo = sc.nextLine();

                                    int telefono;
                                    String direccion;
                                    int movil;
                                    String foto;

                                    //Primero añade el usuario normal para coger su id y meter en las otras tablas a los usuarios con el mismo id de la tabla general
                                    int id = controller.altaUsuario(nombre, email, contrasenya, fecha, tipoUsu.ADMINISTRADOR);

                                    if (tipo.equals("ADMIN")) {
                                        System.out.println("Introduce el teléfono de guardia: ");
                                        telefono = sc.nextInt();
                                        sc.nextLine();
                                        controller.altaUsuarioAdmin(id, nombre, email, contrasenya, fecha, tipoUsu.ADMINISTRADOR, telefono);
                                    }

                                    if (tipo.equals("NORMAL")) {
                                        System.out.println("Introduce la dirección del usuario: ");
                                        direccion = sc.nextLine();
                                        System.out.println("Introduce el teléfono móvil del usuario: ");
                                        movil = sc.nextInt();
                                        sc.nextLine();
                                        //Para el ejemplo lo he hecho que con poner CONFOTO o SINFOTO sirva
                                        System.out.println("Introduce una fotografía del usuario: (CONFOTO/SINFOTO)");
                                        foto = sc.nextLine();
                                        controller.altaUsuarioNormal(id, nombre, email, contrasenya, fecha, tipoUsu.USUARIO_NORMAL, direccion,  movil, foto);
                                    }

                                    todobien = true;
                                } catch (InputMismatchException e) {
                                    todobien = false;
                                }
                            }
                            break;

                        }
                        case 2: {
                            boolean todobien = false;
                            while (!todobien) {
                                try {
                                    System.out.println("Escribe el id del usuario que quieres eliminar: ");
                                    int id = sc.nextInt();

                                    controller.bajaUsuarioAdmin(id);


                                    todobien = true;
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
                                    int id = sc.nextInt();

                                    System.out.println("Escribe el nuevo nombre del recurso: ");
                                    String nombre = sc.nextLine();
                                    System.out.println("Escribe la nueva descripción del recurso: ");
                                    String descripcion = sc.nextLine();
                                    System.out.println("Escribe la nueva ubicación del recurso: ");
                                    String ubicacion = sc.nextLine();
                                    System.out.println("Escribe la nueva capacidad del recurso: ");
                                    int capacidad = sc.nextInt();


                                    todobien = true;

                                    break;
                                } catch (InputMismatchException e) {
                                    todobien = false;
                                }
                            }
                            break;
                        }
                        case 4: {
                            break;
                        }
                        case 5: {
                            System.out.println("Escribe el nombre del recurso que quieres buscar: ");
                            String nombre = sc.nextLine();

                            break;
                        }
                        case 0: {
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
        System.out.println("===========================");
        System.out.println("    GESTIÓN DE USUARIOS    ");
        System.out.println("===========================\n");
        System.out.println("0. Salir");
        System.out.println("1. Dar de alta un usuario.");
        System.out.println("2. Dar de baja un usuario.");
        System.out.println("3. Modificar un usuario.");
        System.out.println("4. Listar los usuarios.");
        System.out.println("5. Buscar por nombre un usuario.");
        System.out.println("6. Buscar por correo un usuario.");
    }
}
