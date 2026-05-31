package View;

import Controller.HorarioController;
import Controller.UsuarioController;
import Dao.DBConnection;
import Model.*;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HorarioView {
    public static void programaHorario() throws SQLException {
        HorarioController controller = new HorarioController();
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
                            boolean continuarCase = true;
                            do {
                                try {
                                    System.out.println("Escribe el día de la semana: ");
                                    String diaString = sc.nextLine();
                                    diaSemana dia = null;
                                    if (diaString.equalsIgnoreCase("Lunes")) {
                                        dia = Model.diaSemana.Lunes;
                                    } else if (diaString.equalsIgnoreCase("Martes")) {
                                        dia = Model.diaSemana.Martes;
                                    } else if (diaString.equalsIgnoreCase("Miércoles")) {
                                        dia = Model.diaSemana.Miércoles;
                                    } else if (diaString.equalsIgnoreCase("Miercoles")) {
                                        dia = Model.diaSemana.Miércoles;
                                    } else if (diaString.equalsIgnoreCase("Jueves")) {
                                        dia = Model.diaSemana.Jueves;
                                    } else if (diaString.equalsIgnoreCase("Viernes")) {
                                        dia = Model.diaSemana.Viernes;
                                    } else if (diaString.equalsIgnoreCase("Sábado")) {
                                        dia = Model.diaSemana.Sábado;
                                    } else if (diaString.equalsIgnoreCase("Sabado")) {
                                        dia = Model.diaSemana.Sábado;
                                    } else if (diaString.equalsIgnoreCase("Domingo")) {
                                        dia = Model.diaSemana.Domingo;
                                    }

                                    System.out.println("Escribe la hora de inicio del horario (hh:mm:ss): ");
                                    LocalTime inicio = LocalTime.parse(sc.nextLine());
                                    System.out.println("Escribe la hora de fin del horario (hh:mm:ss): ");
                                    LocalTime fin = LocalTime.parse(sc.nextLine());
                                    if (!inicio.isBefore(fin)) {
                                        throw new IllegalArgumentException();
                                    }


                                    int id = controller.altaHorario(dia, inicio, fin);
                                    System.out.println(controller.buscarHorarioId(id).toString());
                                    continuarCase = false;
                                } catch (InputMismatchException ex) {
                                    ex.printStackTrace();
                                } catch (IllegalArgumentException e) {
                                    System.out.println("La hora final tiene que ser después de la hora de inicio.");
                                } catch (DateTimeParseException ex) {
                                    System.out.println("Ha habido un error en la hora.");
                                }
                            } while (continuarCase);
                            break;
                        }
                        case 2: {
                            boolean continuarCase = true;
                            do {
                                try {
                                    System.out.println("Escribe el id del horario que quieres eliminar: ");
                                    int id = sc.nextInt();
                                    sc.nextLine();
                                    if (controller.borrarHorario(id)) {
                                        System.out.println("Se ha borrado correctamente.");
                                    }
                                    continuarCase = false;
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                } catch (InputMismatchException ex) {
                                    System.out.println("Solo números");
                                }
                            } while (continuarCase);
                            break;
                        }
                        case 3: {
                            boolean continuarCase = true;
                            do {
                                try {
                                    System.out.println("Escribe el ID del horario que quieres modificar: ");
                                    int id = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Escribe el nuevo día de la semana: ");
                                    String diaString = sc.nextLine();
                                    diaSemana dia = null;
                                    if (diaString.equalsIgnoreCase("Lunes")) {
                                        dia = Model.diaSemana.Lunes;
                                    } else if (diaString.equalsIgnoreCase("Martes")) {
                                        dia = Model.diaSemana.Martes;
                                    } else if (diaString.equalsIgnoreCase("Miércoles")) {
                                        dia = Model.diaSemana.Miércoles;
                                    } else if (diaString.equalsIgnoreCase("Miercoles")) {
                                        dia = Model.diaSemana.Miércoles;
                                    } else if (diaString.equalsIgnoreCase("Jueves")) {
                                        dia = Model.diaSemana.Jueves;
                                    } else if (diaString.equalsIgnoreCase("Viernes")) {
                                        dia = Model.diaSemana.Viernes;
                                    } else if (diaString.equalsIgnoreCase("Sábado")) {
                                        dia = Model.diaSemana.Sábado;
                                    } else if (diaString.equalsIgnoreCase("Sabado")) {
                                        dia = Model.diaSemana.Sábado;
                                    } else if (diaString.equalsIgnoreCase("Domingo")) {
                                        dia = Model.diaSemana.Domingo;
                                    }

                                    System.out.println("Escribe la nueva hora de inicio del horario (hh:mm:ss): ");
                                    LocalTime inicio = LocalTime.parse(sc.nextLine());
                                    System.out.println("Escribe la nueva hora de fin del horario (hh:mm:ss): ");
                                    LocalTime fin = LocalTime.parse(sc.nextLine());

                                    if (!inicio.isBefore(fin)) {
                                        throw new IllegalArgumentException();
                                    }


                                    Horario horario = controller.modificarHorario(id, dia, inicio, fin);
                                    horario.setId(id);
                                    System.out.println(horario.toString());

                                    continuarCase = false;
                                } catch (InputMismatchException ex) {
                                    ex.printStackTrace();
                                } catch (IllegalArgumentException e) {
                                    System.out.println("La hora final tiene que ser después de la hora de inicio.");
                                } catch (DateTimeParseException ex) {
                                    System.out.println("Ha habido un error en la hora.");
                                }
                            } while (continuarCase);
                            break;
                        }
                        case 4: {
                            System.out.println("Estos son todos los horarios que hay hasta ahora: ");
                            List<Horario> lista = controller.listarHorarios();
                            lista.forEach(System.out::println);
                            break;
                        }
                        case 5: {
                            System.out.println("Escribe el día de la semana que quieres consultar: ");
                            String diaString = sc.nextLine();
                            diaSemana dia = null;
                            if (diaString.equalsIgnoreCase("Lunes")) {
                                dia = Model.diaSemana.Lunes;
                            } else if (diaString.equalsIgnoreCase("Martes")) {
                                dia = Model.diaSemana.Martes;
                            } else if (diaString.equalsIgnoreCase("Miércoles")) {
                                dia = Model.diaSemana.Miércoles;
                            } else if (diaString.equalsIgnoreCase("Miercoles")) {
                                dia = Model.diaSemana.Miércoles;
                            } else if (diaString.equalsIgnoreCase("Jueves")) {
                                dia = Model.diaSemana.Jueves;
                            } else if (diaString.equalsIgnoreCase("Viernes")) {
                                dia = Model.diaSemana.Viernes;
                            } else if (diaString.equalsIgnoreCase("Sábado")) {
                                dia = Model.diaSemana.Sábado;
                            } else if (diaString.equalsIgnoreCase("Sabado")) {
                                dia = Model.diaSemana.Sábado;
                            } else if (diaString.equalsIgnoreCase("Domingo")) {
                                dia = Model.diaSemana.Domingo;
                            }

                            List<Horario> lista = controller.listarHorariosPorDia(dia);
                            lista.forEach(System.out::println);
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
        System.out.println("    GESTIÓN DE HORARIOS    ");
        System.out.println("===========================\n");
        System.out.println("0.  Salir");
        System.out.println("1.  Registrar un nuevo horario.");
        System.out.println("2.  Borrar un horario de la lista.");
        System.out.println("3.  Modificar un horario.");
        System.out.println("4.  Listar los horarios.");
        System.out.println("5.  Listar los horarios por día.");
        System.out.println("11. Para abrir otra vez el menú.");
    }
}