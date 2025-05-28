package ProyectoCajeroATM;

public class Retiro extends Transaccion {

    public Retiro(int identificador, String fecha, char tipo, double monto, Cuenta cuentaOrigen) {
        super(identificador, fecha, tipo, monto, cuentaOrigen, null);
    }

    @Override
    public boolean realizarOperacion() {
        if (getCuentaOrigen().getSaldo() >= getMonto()) {
            getCuentaOrigen().setSaldo(getCuentaOrigen().getSaldo() - getMonto());
            System.out.println("Retiro exitoso de " + getMonto() + " de la cuenta " + getCuentaOrigen().getNumero_de_cuenta());
            return true;
        } else {
            System.out.println("Fondos insuficientes para retiro.");
            return false;
        }
    }
}
