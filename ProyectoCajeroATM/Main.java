package ProyectoCajeroATM;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Datos del cajero (fijo)
        Cajero cajero = new Cajero("Av. Principal 123", "Banco Ejemplo");

        // Listas para almacenar datos
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        ArrayList<Cuenta> cuentas = new ArrayList<>();

        // *** ALTA DE CLIENTES y TARJETAS - Esto sería para poblar la data antes de usar el cajero ***
        Cliente clienteEjemplo = new Cliente(1, "Roberto", "Calle Falsa 123");
        clientes.add(clienteEjemplo);
        Cuenta cuentaAhorro = new CuentaAhorro(1, clienteEjemplo, 'A', 1000); // saldo inicial ejemplo
        Cuenta cuentaCheques = new CuentaCheque(2, clienteEjemplo, 'C', 2000);
        cuentas.add(cuentaAhorro);
        cuentas.add(cuentaCheques);
        Tarjeta tarjeta = new Tarjeta(12344321, cuentaAhorro);
        tarjetas.add(tarjeta);

        System.out.println("=== Bienvenido al Cajero Automático ===");

        while (true) {
            System.out.print("\nPor favor, ingrese el ID de su tarjeta: ");
            String idTarjeta = scanner.nextLine();

            // Buscar la tarjeta en la lista
            Tarjeta tarjetaUsuario = null;
            for (Tarjeta t : tarjetas) {
                if (t.getNumero() == Integer.parseInt(idTarjeta)) {
                    tarjetaUsuario = t;
                    break;
                }
            }

            if (tarjetaUsuario == null) {
                System.out.println("Tarjeta no encontrada o no válida. Intente de nuevo.");
                continue;
            }

            Cliente cliente = tarjetaUsuario.getCuenta().getCliente();
            System.out.println("Bienvenido, " + cliente.getNombre());

            // Selección tipo cuenta
            char tipoCuenta;
            Cuenta cuentaSeleccionada = null;
            do {
                System.out.print("Seleccione tipo de cuenta (A = Ahorro, C = Cheques): ");
                String input = scanner.nextLine().toUpperCase();
                if (input.length() == 1 && (input.charAt(0) == 'A' || input.charAt(0) == 'C')) {
                    tipoCuenta = input.charAt(0);
                    // Buscar cuenta del cliente con ese tipo
                    for (Cuenta c : cuentas) {
                        if (c.getCliente().getIdentificador() == cliente.getIdentificador() && c.getTipo() == tipoCuenta) {
                            cuentaSeleccionada = c;
                            break;
                        }
                    }
                    if (cuentaSeleccionada == null) {
                        System.out.println("No tiene cuenta de ese tipo.");
                        continue;
                    }
                    break;
                } else {
                    System.out.println("Entrada inválida. Ingrese A o C.");
                }
            } while (true);

            // Selección transacción
            System.out.println("Seleccione tipo de transacción:");
            System.out.println("1. Retiro");
            System.out.println("2. Transferencia");
            int opcionTransaccion = 0;
            do {
                System.out.print("Opción: ");
                try {
                    opcionTransaccion = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    opcionTransaccion = 0;
                }
                if (opcionTransaccion != 1 && opcionTransaccion != 2) {
                    System.out.println("Opción inválida, intente de nuevo.");
                }
            } while (opcionTransaccion != 1 && opcionTransaccion != 2);

            System.out.print("Ingrese monto: ");
            double monto = Double.parseDouble(scanner.nextLine());

            if (opcionTransaccion == 1) {
                // Retiro
                cajero.realizarRetiro(cuentaSeleccionada, monto);

            } else {
                // Transferencia
                System.out.print("Ingrese número de cuenta destino: ");
                String numCuentaDestino = scanner.nextLine();

                // Buscar cuenta destino
                Cuenta cuentaDestino = null;
                for (Cuenta c : cuentas) {
                    // Asumo que el número de cuenta es int y se guarda como int, si es String ajustar
                    if (String.valueOf(c.getNumero_de_cuenta()).equals(numCuentaDestino)) {
                        cuentaDestino = c;
                        break;
                    }
                }

                if (cuentaDestino == null) {
                    System.out.println("Cuenta destino no encontrada.");
                } else {
                    cajero.realizarTransferencia(cuentaSeleccionada, cuentaDestino, monto);
                }
            }

            System.out.print("\n¿Desea realizar otra operación? (s/n): ");
            String otraOp = scanner.nextLine();
            if (!otraOp.equalsIgnoreCase("s")) {
                System.out.println("Gracias por usar el cajero automático. ¡Hasta luego!");
                break;
            }
        }

        scanner.close();
    }
}
