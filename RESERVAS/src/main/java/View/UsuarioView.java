package View;

import Controller.UsuarioController;
import Dao.DBConnection;
import Model.*;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UsuarioView {

    public static void programaUsuario() throws SQLException {
        UsuarioController controller = new UsuarioController();
        boolean continuar = false;
        if (DBConnection.getConnection() != null) {
            menu();

            while (!continuar) {
                System.out.println("Elige una opcion: (11 para el menú) ");
                Scanner sc = new Scanner(System.in);
                try {
                    int opcion = sc.nextInt();
                    sc.nextLine();
                    switch (opcion) {
                        case 11: {
                            menu();
                            break;
                        }
                        case 1: {
                            boolean todobien  = false;
                            while (!todobien) {
                                try {
                                    System.out.println("Introduce el nombre del usuario: ");
                                    String nombre = sc.nextLine();
                                    System.out.println("Introduce el email del usuario: ");
                                    String email = sc.nextLine();
                                    System.out.println("Introduce la contraseña del usuario: ");
                                    String contrasenya = sc.nextLine();
                                    System.out.println("Introduce la fecha nacimiento del usuario: ");
                                    String fecha = sc.nextLine();
                                    System.out.println("Introduce el tipo de usuario (ADMIN/NORMAL): ");
                                    String tipo = sc.nextLine();

                                    String telefono = "";
                                    String direccion = "";
                                    String movil = "";
                                    String foto = "";

                                    //Primero añade el usuario normal para coger su id y meter en las otras tablas a los usuarios con el mismo id de la tabla general


                                    if (tipo.equalsIgnoreCase("ADMIN")) {
                                        int id = controller.altaUsuario(nombre, email, contrasenya, fecha, tipoUsu.ADMINISTRADOR);
                                        System.out.println("Introduce el teléfono de guardia: ");
                                        telefono = sc.nextLine();
                                        controller.altaUsuarioAdmin(id, nombre, email, contrasenya, fecha, tipoUsu.ADMINISTRADOR, telefono);
                                    }

                                    if (tipo.equalsIgnoreCase("NORMAL")) {
                                        int id = controller.altaUsuario(nombre, email, contrasenya, fecha, tipoUsu.USUARIO_NORMAL);
                                        System.out.println("Introduce la dirección del usuario: ");
                                        direccion = sc.nextLine();
                                        System.out.println("Introduce el teléfono móvil del usuario: ");
                                        movil = sc.nextLine();
                                        //Para el ejemplo lo he hecho que con poner CONFOTO o SINFOTO sirva
                                        System.out.println("Introduce una fotografía del usuario: (CONFOTO/SINFOTO)");
                                        foto = sc.nextLine();
                                        controller.altaUsuarioNormal(id, nombre, email, contrasenya, fecha, tipoUsu.USUARIO_NORMAL, direccion,  movil, foto);
                                    }
                                    System.out.println("Se ha añadido correctamente");
                                    todobien = true;
                                } catch (InputMismatchException e) {
                                    todobien = false;
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                    System.out.println("No pueden haber datos que ya tenga otro usuario");
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
                                    sc.nextLine();
                                    System.out.println("El usuario es normal o administrador?");
                                    String tipo = sc.nextLine();
                                    tipo = tipo.toLowerCase();
                                    switch (tipo) {
                                        case "normal" -> {
                                            if (controller.bajaUsuarioNormal(id)) {
                                                System.out.println("Se ha borrado correctamente");
                                            };
                                        }
                                        case "administrador" -> {
                                            if (controller.bajaUsuarioAdmin(id)) {
                                                System.out.println("Se ha borrado correctamente");
                                            };
                                        }
                                    }


                                    todobien = true;
                                } catch (InputMismatchException e) {
                                    todobien = false;
                                    sc.nextLine();
                                } catch (SQLException e) {
                                    System.out.println("Comprueba que el usuario que quieres borrar no tenga reservas asociadas");
                                }
                            }
                            break;

                        }
                        case 3: {
                            boolean todobien = false;
                            while (!todobien) {
                                try {
                                    System.out.println("Escribe el id del usuario que quieres modificar: ");
                                    int id = sc.nextInt();
                                    sc.nextLine();

                                    Usuario usuario = controller.buscarID(id);
                                    System.out.println(usuario.toString());

                                    System.out.println("Escribe el nuevo nombre del usuario: ");
                                    String nombre = sc.nextLine();
                                    System.out.println("Escribe el nuevo email del usuario: ");
                                    String email = sc.nextLine();
                                    System.out.println("Escribe la nueva contraseña del usuario: ");
                                    String contrasenya = sc.nextLine();
                                    System.out.println("Escribe la nueva fecha de nacimiento del usuario: ");
                                    String fecha = sc.nextLine();
                                    System.out.println("¿Es administrador o normal?: ");
                                    String tipo  = sc.nextLine();
                                    if (tipo.equalsIgnoreCase("NORMAL")) {
                                        System.out.println("Escribe la nueva dirección del usuario: ");
                                        String direccion = sc.nextLine();
                                        System.out.println("Escribe el nuevo móvil del usuario: ");
                                        String telefono = sc.nextLine();
                                        System.out.println("Escribe la nueva URL de la foto: ");
                                        String foto = sc.nextLine();

                                        Usuario_normal usuario_normal = new Usuario_normal(email, contrasenya, nombre, fecha, tipoUsu.USUARIO_NORMAL, direccion, telefono, foto);
                                        usuario_normal.setId(id);
                                        controller.modificarUsuarioNormal(usuario_normal);
                                    }

                                    if (tipo.equalsIgnoreCase("ADMINISTRADOR")) {
                                        System.out.println("Escribe el nuevo teléfono de guardia del usuario: ");
                                        String telefono = sc.nextLine();

                                        Usuario_administrador usuario_administrador = new Usuario_administrador(email, contrasenya, nombre, fecha, tipoUsu.ADMINISTRADOR, telefono);
                                        usuario_administrador.setId(id);
                                        controller.modificarUsuarioAdmin(usuario_administrador);
                                    }



                                    todobien = true;

                                    break;
                                } catch (InputMismatchException e) {
                                    todobien = false;
                                    sc.nextLine();
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            break;
                        }
                        case 4: {
                            System.out.println("Qué tipo de usuarios quieres listar: todos, administrador o normal?");
                            String opc = sc.nextLine();
                            controller.listarUsuarios(opc);
                            break;
                        }
                        case 5: {
                            System.out.println("Escribe el nombre del usuario que quieres buscar: ");
                            String nombre = sc.nextLine();
                            Usuario usuario = controller.buscarNombre(nombre);
                            System.out.println(usuario.toString());
                            break;
                        }
                        case 6: {
                            System.out.println("Escribe el correo del usuario que quieres buscar: ");
                            String correo = sc.nextLine();
                            Usuario usuario = controller.buscarCorreo(correo);
                            System.out.println(usuario.toString());
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
                    sc.nextLine();
                }
            }
        }

    }

    public static void menu() {
        System.out.println("===========================");
        System.out.println("    GESTIÓN DE USUARIOS    ");
        System.out.println("===========================\n");
        System.out.println("0.  Salir");
        System.out.println("1.  Dar de alta un usuario.");
        System.out.println("2.  Dar de baja un usuario.");
        System.out.println("3.  Modificar un usuario.");
        System.out.println("4.  Listar los usuarios.");
        System.out.println("5.  Buscar por nombre un usuario.");
        System.out.println("6.  Buscar por correo un usuario.");
        System.out.println("11. Para abrir otra vez el menú.");
    }
}
