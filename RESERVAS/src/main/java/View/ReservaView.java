package View;

import Controller.DisponibleenController;
import Controller.HorarioController;
import Controller.ReservaController;
import Dao.*;
import Model.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ReservaView {
    public static void programaHorario() throws SQLException {
        ReservaController reserva = new ReservaController();
        UsuarioDao usuario = new UsuarioDao();
        RecursoDao recurso = new RecursoDao();
        DisponibleenDao dao = new DisponibleenDao();
        HorarioDao horario = new HorarioDao();

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

                                    System.out.println("Escribe el Id de uno de estos usuario que hace la reserva: ");
                                    List<Usuario> lista = usuario.listarUsuarios();
                                    for (Usuario user : lista) {
                                        if (user.getTipo().equals(tipoUsu.USUARIO_NORMAL)) {
                                            System.out.println(user.toString());
                                        }
                                    }
                                    int id_usuario = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Escribe el Id del recurso que quieres reservar: ");
                                    List<Recurso> listaR = recurso.listarRecursos();
                                    listaR.forEach(System.out::println);
                                    int id_recurso = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Escribe la fecha de la reserva (yyyy-mm-dd): ");
                                    LocalDate fecha = LocalDate.parse(sc.nextLine());
                                    boolean continuarHoras = true;
                                    LocalTime inicio;
                                    LocalTime fin;
                                    int id_horario = dao.buscarHorarioporRecurso(id_recurso);
                                    do {
                                        System.out.println("Este recurso está disponible en estos horarios: ");
                                        System.out.println(horario.buscarPorIdHorario(id_horario));
                                        System.out.println("Escribe la hora de inicio de la reserva (hh:mm:ss): ");
                                        inicio = LocalTime.parse(sc.nextLine());
                                        System.out.println("Escribe la hora de fin de la reserva (hh:mm:ss): ");
                                        fin = LocalTime.parse(sc.nextLine());

                                        if (inicio.isAfter(fin)) {
                                            continuarHoras = false;
                                            System.out.println("Las horas no están bien.");
                                        } else {
                                            continuarHoras = true;

                                            if (!horario.comprobarHoraFinal(fin, id_horario) || !horario.comprobarHoraInicio(inicio, id_horario)) {
                                                continuarHoras = false;
                                                System.out.println("Las horas no están bien.");
                                            }
                                        }

                                    } while (!continuarHoras);
                                    System.out.println("Escribe el coste de la reserva: ");
                                    double coste = sc.nextDouble();
                                    sc.nextLine();
                                    System.out.println("Escribe el número de plazas de la reserva: ");
                                    int plazas = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Escribe el motivo de la reserva: ");
                                    String motivo = sc.nextLine();
                                    System.out.println("Escribe alguna observación de la reserva: ");
                                    String observaciones = sc.nextLine();

                                    Reserva res = new Reserva(id_recurso, id_usuario, fecha, inicio, fin, coste, plazas, motivo, observaciones);
                                    int[] id = reserva.altaReserva(res);
                                    System.out.println(reserva.buscarReservaId(id[0], id[1], id[2]));

                                    continuarCase = false;
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                } catch (InputMismatchException ex) {
                                    sc.nextLine();
                                    System.out.println("Ese formato no es válido");
                                }
                            } while (continuarCase);
                            break;
                        }
                        case 2: {
                            boolean continuarCase = true;
                            do {
                                try {
                                    System.out.println("Escribe los siguientes Ids de la reserva: ");
                                    System.out.println("Id de recurso: ");
                                    int id_recurso = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Id de usuario: ");
                                    int id_usuario = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Id de la reserva local: ");
                                    int id_reserva_local = sc.nextInt();
                                    sc.nextLine();
                                    reserva.borrarReserva(id_recurso, id_usuario, id_reserva_local);
                                    continuarCase = false;
                                } catch (InputMismatchException ex) {
                                    System.out.println("Error de formato.");
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            } while (continuarCase);
                            break;
                        }
                        case 3: {
                            boolean continuarCase = true;
                            do {
                                try {
                                    System.out.println("Escribe los siguientes Ids de la reserva: ");
                                    System.out.println("Id de recurso: ");
                                    int id_recurso = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Id de usuario: ");
                                    int id_usuario = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Id de la reserva local: ");
                                    int id_reserva_local = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Escribe la nueva fecha de la reserva (yyyy-mm-dd): ");
                                    LocalDate fecha = LocalDate.parse(sc.nextLine());
                                    boolean continuarHoras = true;
                                    LocalTime inicio;
                                    LocalTime fin;
                                    int id_horario = dao.buscarHorarioporRecurso(id_recurso);
                                    do {
                                        System.out.println("Este recurso está disponible en estos horarios: ");
                                        System.out.println(horario.buscarPorIdHorario(id_horario));
                                        System.out.println("Escribe la nueva hora de inicio de la reserva (hh:mm:ss): ");
                                        inicio = LocalTime.parse(sc.nextLine());
                                        System.out.println("Escribe la nueva hora de fin de la reserva (hh:mm:ss): ");
                                        fin = LocalTime.parse(sc.nextLine());

                                        if (inicio.isAfter(fin)) {
                                            continuarHoras = false;
                                            System.out.println("Las horas no están bien.");
                                        } else {
                                            continuarHoras = true;

                                            if (!horario.comprobarHoraFinal(fin, id_horario) || !horario.comprobarHoraInicio(inicio, id_horario)) {
                                                continuarHoras = false;
                                                System.out.println("Las horas no están bien.");
                                            }
                                        }

                                    } while (!continuarHoras);
                                    System.out.println("Escribe el nuevo coste de la reserva: ");
                                    double coste = sc.nextDouble();
                                    sc.nextLine();
                                    System.out.println("Escribe el nuevo número de plazas de la reserva: ");
                                    int plazas = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Escribe el motivo de la reserva: ");
                                    String motivo = sc.nextLine();
                                    System.out.println("Escribe alguna observación de la reserva: ");
                                    String observaciones = sc.nextLine();

                                    Reserva res = new Reserva(id_recurso, id_usuario, fecha, inicio, fin, coste, plazas, motivo, observaciones);
                                    res.setCantidadDeReservas(reserva.cantidadReservas(res));
                                    int[] id = reserva.modificarReserva(res);
                                    System.out.println(reserva.buscarReservaId(id[0], id[1], id[2]));

                                    continuarCase = false;
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                } catch (InputMismatchException ex) {
                                    sc.nextLine();
                                    System.out.println("Ese formato no es válido");
                                }
                            } while (continuarCase);
                            break;
                        }
                        case 4: {
                            System.out.println("Estos son todas las reservas que hay hasta ahora: ");
                            List<Reserva> lista = reserva.listarReservas();
                            lista.forEach(System.out::println);
                            break;
                        }
                        case 5: {
                            boolean continuarCase = true;
                            do {
                                try {
                                    System.out.println("Escribe el Id del usuario que quieres consultar sus reservas: ");
                                    List<Usuario> lista = usuario.listarUsuarios();
                                    for (Usuario user : lista) {
                                        if (user.getTipo().equals(tipoUsu.USUARIO_NORMAL)) {
                                            System.out.println(user.toString());
                                        }
                                    }
                                    System.out.println("Id de usuario: ");
                                    int id_usuario = sc.nextInt();
                                    sc.nextLine();

                                    List<Reserva> res = reserva.buscarReservaUsuario(id_usuario);
                                    res.forEach(System.out::println);
                                    continuarCase = false;
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                } catch (InputMismatchException ex) {
                                    System.out.println("Error en el formato.");
                                }
                            } while (continuarCase);
                            break;
                        }

                        case 6: {
                            boolean continuarCase = true;
                            do {
                                try {
                                    List<Disponibleen> lista = dao.listarDisponibilidad();
                                    lista.forEach(System.out::println);
                                    continuarCase = false;
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            } while (continuarCase);
                            break;
                        }

                        case 7: {
                            boolean continuarCase = true;
                            do {
                                try {
                                    System.out.println("Escribe los siguientes Ids: ");
                                    System.out.println("Id de recurso: ");
                                    int id_recurso = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Id de horario: ");
                                    int  id_horario = sc.nextInt();
                                    sc.nextLine();
                                    Disponibleen d = new Disponibleen(id_recurso, id_horario);
                                    if (dao.anyadirDisponibleen(d)) {
                                        System.out.println("Se ha añadido bien.");
                                    }
                                    continuarCase = false;
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            } while (continuarCase);
                            break;
                        }

                        case 8: {
                            boolean continuarCase = true;
                            do {
                                try {
                                    System.out.println("Escribe los siguientes Ids: ");
                                    System.out.println("Id de recurso: ");
                                    int id_recurso = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Id de horario: ");
                                    int  id_horario = sc.nextInt();
                                    sc.nextLine();
                                    Disponibleen d = new Disponibleen(id_recurso, id_horario);
                                    if (dao.borrarDisponibilidad(d)) {
                                        System.out.println("Se ha añadido bien.");
                                    }
                                    continuarCase = false;
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            } while (continuarCase);
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
                } catch (SQLException ex) {
                    System.out.println("Ha habido algún problema.");
                }
            }
        }

    }

    public static void menu() {
        System.out.println("===========================");
        System.out.println("    GESTIÓN DE RESERVAS    ");
        System.out.println("===========================\n");
        System.out.println("0.  Salir");
        System.out.println("1.  Hacer una reserva.");
        System.out.println("2.  Borrar una reserva.");
        System.out.println("3.  Modificar una reserva.");
        System.out.println("4.  Listar las reservas.");
        System.out.println("5.  Listar las reservas por usuario.");

        System.out.println("6.  Ver disponibilidad.");
        System.out.println("7.  Añadir disponibilidad.");
        System.out.println("8.  Eliminar disponibilidad.");
        System.out.println("11. Para abrir otra vez el menú.");
    }


}
