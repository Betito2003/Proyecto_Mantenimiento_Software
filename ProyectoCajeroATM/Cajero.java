package ProyectoCajeroATM;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Cajero {

    ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
    private String localizacion;
    private String banco;
    private static int id = 0;

    public Cajero(String localizacion, String banco) {
        this.localizacion = localizacion;
        this.banco = banco;
    }
    
    public void realizarRetiro(Cuenta cuenta, double monto) {
        id++;
        String fecha = obtenerFechaActual();
        Retiro retiro = new Retiro(id, fecha, 'R', monto, cuenta);
        if(retiro.realizarOperacion()){
            listaTransacciones.add(retiro);
            imprimirTicket(cuenta.getCliente().getNombre(), 'R', monto);
        }
    }

    public void realizarTransferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto) {
        id++;
        String fecha = obtenerFechaActual();
        Transferencia transferencia = new Transferencia(id, fecha, 'T', monto, cuentaOrigen, cuentaDestino);
        if(transferencia.realizarOperacion()){
            imprimirTicket(cuentaOrigen.getCliente().getNombre(), 'T', monto);
            listaTransacciones.add(transferencia);
        }
    }

    private String obtenerFechaActual() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ahora.format(formato);
    }
    public void imprimirTicket(String cliente, char tipoTransaccion, double monto) {
        System.out.println("======= " + banco + " =======");
        System.out.println("Cliente: " + cliente);
        System.out.println("Tipo: " + (tipoTransaccion == 'R' ? "Retiro" : "Transferencia"));
        System.out.println("Monto: " + monto);
        System.out.println("=========================");
    }

    // Getters y setters
    public String getLocalizacion() {
        return this.localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getBanco() {
        return this.banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
}
