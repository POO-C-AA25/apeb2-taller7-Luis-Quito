/**
 * El banco UN BANCO mantiene las cuentas de varios clientes. Los datos que describen a cada una de las cuentas consisten 
 * en el número de cuenta, el nombre del cliente y el balance actual. Escriba una clase para implementar dicha cuenta bancaria. 
 * El método constructor debe aceptar como parámetros el número de cuenta y el nombre. Debe proporcionarse métodos para 
 * depositar o retirar una cantidad de dinero y obtener el balance actual.
 * El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de AHORROS. Una cuenta de cheques puede sobregirarse 
 * (el balance puede ser menor que cero), pero una cuenta de ahorros no. Al final de cada mes, se calcula el interés sobre 
 * la cantidad que tenga la cuenta de ahorros. Este interés se suma al balance. Escriba clases para describir cada uno de 
 * estos tipos de cuentas, haciendo un máximo uso de la herencia. La clase de la cuenta de ahorros debe proporcionar un método 
 * que sea invocado para calcular el interés. Además, el banco está pensando en implementar una cuenta PLATINO que viene siendo 
 * similar a los otros dos tipos anteriores de cuentas bancarias, ésta tiene el interés del 10%, sin cargos ni castigos por sobregiro.
 * @author Luis
 */
public class Problema6_Banco {
    public static void main(String[] args) {
        CuentaCheques cuenta1 = new CuentaCheques("C001", "Marco Herrera");
        CuentaAhorros cuenta2 = new CuentaAhorros("C002", "Sofía Mendoza", 0.05);     
        CuentaPlatino cuenta3 = new CuentaPlatino("C003", "Gabriel Salas");

        cuenta1.depositar(800);
        cuenta1.retirar(950); 

        cuenta2.depositar(1500);
        cuenta2.retirar(300);
        cuenta2.calcularInteres(); 

        cuenta3.depositar(2500);
        cuenta3.retirar(700);
        cuenta3.calcularInteres(); 

        System.out.println(cuenta1);
        System.out.println("\n" + cuenta2);
        System.out.println("\n" + cuenta3);
    }
}
class Cuenta {
    public String codigoCuenta;
    public String titular;
    public double saldo;

    public Cuenta() {}

    public Cuenta(String codigoCuenta, String titular) {
        this.codigoCuenta = codigoCuenta;
        this.titular = titular;
        this.saldo = 0.0;
    }
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
    }
    public void retirar(double monto) {
        if (monto > 0) {
            saldo -= monto;
        }
    }
    public double obtenerSaldo() {
        return saldo;
    }
    public String toString() {
        return "Cuenta N°: " + codigoCuenta 
             + "\nTitular: " + titular 
             + "\nSaldo actual: $" + saldo;
    }
}
class CuentaAhorros extends Cuenta {
    public double interesAnual;

    public CuentaAhorros() {}

    public CuentaAhorros(String codigoCuenta, String titular, double interesAnual) {
        super(codigoCuenta, titular);
        this.interesAnual = interesAnual;
    }
    public void calcularInteres() {
        if (saldo > 0) {
            double interes = saldo * interesAnual;
            depositar(interes);
        }
    }
    @Override
    public void retirar(double monto) {
        if (monto <= saldo) {
            saldo -= monto;
        }
    }
}
class CuentaCheques extends Cuenta {
    public CuentaCheques() {}

    public CuentaCheques(String codigoCuenta, String titular) {
        super(codigoCuenta, titular);
    }
    @Override
    public void retirar(double monto) {
        if (monto > 0) {
            saldo -= monto;
        }
    }
}
class CuentaPlatino extends Cuenta {
    public double interesFijo;

    public CuentaPlatino() {}

    public CuentaPlatino(String codigoCuenta, String titular) {
        super(codigoCuenta, titular);
        this.interesFijo = 0.10;
    }
    public void calcularInteres() {
        if (saldo > 0) {
            double interes = saldo * interesFijo;
            depositar(interes);
        }
    }
    @Override
    public void retirar(double monto) {
        if (monto > 0) {
            saldo -= monto;
        }
    }
}