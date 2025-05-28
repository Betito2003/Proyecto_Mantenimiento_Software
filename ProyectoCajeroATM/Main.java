package ProyectoCajeroATM;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cajero cajero = new Cajero("Av. Principal 123", "Banco Ejemplo");

        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        ArrayList<Tarjeta> tarjetas = new ArrayList<>();

        System.out.println("=== Bienvenido al Cajero Automático ===");

        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Alta de Cliente");
            System.out.println("2. Consultar Clientes");
            System.out.println("3. Realizar Operación");
            System.out.println("4. Consultar Cuentas");
            System.out.println("5. Depositar dinero a una cuenta");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    // Alta de Cliente
                    System.out.print("Ingrese nombre del cliente: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese dirección: ");
                    String direccion = scanner.nextLine();

                    int idCliente = clientes.size() + 1;
                    Cliente nuevoCliente = new Cliente(idCliente, nombre, direccion);
                    clientes.add(nuevoCliente);

                    Cuenta cuentaAhorro = new CuentaAhorro(cuentas.size() + 1, nuevoCliente, 1000, 1000);
                    Cuenta cuentaCheque = new CuentaCheque(cuentas.size() + 2, nuevoCliente, 2000, 2000);
                    cuentas.add(cuentaAhorro);
                    cuentas.add(cuentaCheque);

                    Tarjeta nuevaTarjetaCheque = new Tarjeta(1234000 + idCliente, cuentaCheque);
                    Tarjeta nuevaTarjetaAhorro = new Tarjeta(4321000 + idCliente, cuentaAhorro);
                    tarjetas.add(nuevaTarjetaCheque);
                    tarjetas.add(nuevaTarjetaAhorro);

                    System.out.println("\nCliente creado exitosamente:");
                    System.out.println("ID Cliente: " + nuevoCliente.getIdentificador());
                    System.out.println("Nombre: " + nuevoCliente.getNombre());
                    System.out.println("Cuenta Ahorro: " + cuentaAhorro.getNumero_de_cuenta());
                    System.out.println("Cuenta Cheque: " + cuentaCheque.getNumero_de_cuenta());
                    System.out.println("Tarjeta de cheque: " + nuevaTarjetaCheque.getNumero());
                    System.out.println("Tarjeta de ahorro: " + nuevaTarjetaAhorro.getNumero());
                    break;

                case "2":
                    if (clientes.isEmpty()) {
                        System.out.println("\nNo hay clientes registrados aún.");
                    } else {
                        System.out.println("\n=== Lista de Clientes Registrados ===");
                        for (Cliente c : clientes) {
                            System.out.println("ID: " + c.getIdentificador() +
                                    " | Nombre: " + c.getNombre() +
                                    " | Dirección: " + c.getDireccion());
                        }
                    }
                    break;

                case "3":
                    if (clientes.isEmpty()) {
                        System.out.println("\nNo hay clientes registrados aún.");
                    } else {
                        realizarOperacion(scanner, clientes, cuentas, tarjetas, cajero);
                    }
                    break;

                case "4":
                    if (cuentas.isEmpty()) {
                        System.out.println("\nNo hay cuentas registradas aún.");
                    } else {
                        System.out.println("\n=== Lista de Cuentas Registradas ===");
                        for (Cuenta c : cuentas) {
                            // Buscar tarjeta asociada a esta cuenta
                            String numeroTarjeta = "No tiene tarjeta asociada";
                            for (Tarjeta t : tarjetas) {
                                if (t.getCuenta().getNumero_de_cuenta() == c.getNumero_de_cuenta()) {
                                    numeroTarjeta = String.valueOf(t.getNumero());
                                    break;
                                }
                            }

                            System.out.println("Número: " + c.getNumero_de_cuenta() +
                                " | Tipo: " + (c.getTipo() == 'A' ? "Ahorro" : "Cheques") +
                                " | Cliente: " + c.getCliente().getNombre() +
                                " | Saldo: " + String.format("%.2f", c.getSaldo()) +
                                " | Tarjeta: " + numeroTarjeta);
                        }
                    }
                    break;

                    case "5":
                        if (tarjetas.isEmpty()) {
                            System.out.println("\nNo hay tarjetas registradas aún.");
                        } else {
                            System.out.print("\nIngrese el número de tarjeta: ");
                            String numeroTarjetaDeposito = scanner.nextLine();

                            Tarjeta tarjetaDeposito = null;
                            for (Tarjeta t : tarjetas) {
                                if (String.valueOf(t.getNumero()).equals(numeroTarjetaDeposito)) {
                                    tarjetaDeposito = t;
                                    break;
                                }
                            }

                            if (tarjetaDeposito == null) {
                                System.out.println("Tarjeta no encontrada o no válida.");
                            } else {
                                System.out.print("Ingrese el monto a depositar: ");
                                double montoDeposito = Double.parseDouble(scanner.nextLine());

                                // Sumar el saldo directamente
                                Cuenta cuentaDeposito = tarjetaDeposito.getCuenta();
                                cuentaDeposito.setSaldo(cuentaDeposito.getSaldo() + montoDeposito);

                                System.out.println("Depósito realizado exitosamente. Nuevo saldo: " +
                                        String.format("%.2f", cuentaDeposito.getSaldo()));
                            }
                        }
                        break;


                case "6":
                    continuar = false;
                    ArrayList<Transaccion> lista = cajero.getListaTransacciones();
                    for (Transaccion t : lista) {
                        System.out.println("Identificador: " + t.getIdentificador()
                            + " | Fecha: " + t.getFecha() 
                            + " | Tipo: " + t.getTipo()
                            + " | Cuenta: " + t.getCuentaOrigen().getNumero_de_cuenta()
                            + " | Cuenta Destino: " + (t.getCuentaDestino() != null ? t.getCuentaDestino().getNumero_de_cuenta() : "N/A")
                            + " | Monto: " + t.getMonto());
                    }

                    System.out.println("Gracias por usar el cajero automático. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    private static void realizarOperacion(Scanner scanner, ArrayList<Cliente> clientes, ArrayList<Cuenta> cuentas,
                                           ArrayList<Tarjeta> tarjetas, Cajero cajero) {
        System.out.print("\nIngrese el número de su tarjeta: ");
        String idTarjeta = scanner.nextLine();

        Tarjeta tarjetaUsuario = null;
        for (Tarjeta t : tarjetas) {
            if (String.valueOf(t.getNumero()).equals(idTarjeta)) {
                tarjetaUsuario = t;
                break;
            }
        }

        if (tarjetaUsuario == null) {
            System.out.println("Tarjeta no encontrada o no válida.");
            return;
        }

        Cliente cliente = tarjetaUsuario.getCuenta().getCliente();
        System.out.println("Bienvenido, " + cliente.getNombre());

        // Se toma la cuenta directamente de la tarjeta
        Cuenta cuentaSeleccionada = tarjetaUsuario.getCuenta();

        System.out.println("\nSeleccione la operación:");
        System.out.println("1. Retiro");
        System.out.println("2. Transferencia");
        System.out.print("Opción: ");
        String opcion = scanner.nextLine();

        System.out.print("Ingrese monto: ");
        double monto = Double.parseDouble(scanner.nextLine());

        switch (opcion) {
            case "1":
                cajero.realizarRetiro(cuentaSeleccionada, monto);
                break;
            case "2":
                System.out.print("Ingrese número de tarjeta destino: ");
                String numTarjetaDestino = scanner.nextLine();

                Tarjeta tarjetaDestino = null;
                for (Tarjeta t : tarjetas) {
                    if (String.valueOf(t.getNumero()).equals(numTarjetaDestino)) {
                        tarjetaDestino = t;
                        break;
                    }
                }

                if (tarjetaDestino == null) {
                    System.out.println("Tarjeta destino no encontrada.");
                } else {
                    Cuenta cuentaDestino = tarjetaDestino.getCuenta();
                    cajero.realizarTransferencia(cuentaSeleccionada, cuentaDestino, monto);
                }
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
}
