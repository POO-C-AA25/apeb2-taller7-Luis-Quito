/**
 * Se desea desarrollar un sistema de nómina para los trabajadores de una empresa. Los datos personales de los trabajadores son nombre 
 * y apellidos, dirección y DNI. Además, existen diferentes tipos de trabajadores:
 * 
 * Fijos Mensuales: que cobran una cantidad fija al mes.
 * Comisionistas: cobran un porcentaje fijo por las ventas que han realizado
 * Por Horas: cobran un precio por cada una de las horas que han realizado durante el mes. El precio es fijo para las primeras 40 horas 
 * y es otro para las horas realizadas a partir de la 40 hora mensual.
 * Jefe: cobra un sueldo fijo (no hay que calcularlo). Cada empleado tiene obligatoriamente un jefe (exceptuando los jefes que no 
 * tienen ninguno). El programa debe permitir dar de alta a trabajadores, así como fijar horas o ventas realizadas e imprimir la nómina 
 * correspondiente al final de mes.
 * @author Luis
 */
import java.util.ArrayList;
public class Problema4_Nomina {
     public static void main(String[] args) {
        Jefe jefeGeneral = new Jefe("Ana", "Martínez", "Av. Central 123", "01234567A", 2500);
        jefeGeneral.jefe = jefeGeneral;
        Empresa empresa = new Empresa(jefeGeneral);
        FijosMensuales empleado1 = new FijosMensuales("Daniela", "Ríos", "Calle Bolívar 112", "12345678Z", jefeGeneral, 1350);
        PorHoras empleado2 = new PorHoras("Esteban", "Luna", "Av. América 908", "87654321Y", jefeGeneral, 9.25, 46);
        Comisionistas empleado3 = new Comisionistas("Camila", "Vera", "Calle Sucre 507", "55667788W", jefeGeneral, 8500, 0.05);
        empresa.darDeAltaTrabajador(empleado1);
        empresa.darDeAltaTrabajador(empleado2);
        empresa.darDeAltaTrabajador(empleado3);
        empresa.listarNomina();
    }
    String nombres;
    String apellidos;
    String direccion;
    String DNI;
    Jefe jefe;
    public Problema4_Nomina(String nombres, String apellidos, String direccion, String DNI, Jefe jefe) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.DNI = DNI;
        this.jefe = jefe;
    }
    public double calcularSueldo() {
        return 0;
    }
    public String toString() {
        return "Trabajador: " + nombres + " " + apellidos + " (" + DNI + ")\n"
                + "Dirección: " + direccion + "\n"
                + "Jefe: " + jefe.nombres + " " + jefe.apellidos + "\n"
                + "Sueldo: " + calcularSueldo() + "\n"
                + "----------------------------------";
    }
}
class Empresa {
    Jefe jefeGeneral;
    ArrayList<Problema4_Nomina> trabajadores;
    public Empresa(Jefe jefeGeneral) {
        this.jefeGeneral = jefeGeneral;
        trabajadores = new ArrayList<>();
        trabajadores.add(jefeGeneral);
    }
    public void darDeAltaTrabajador(Problema4_Nomina t) {
        trabajadores.add(t);
    }
    public void listarNomina() {
        System.out.println("NOMINA DE LA EMPRESA");
        for (Problema4_Nomina t : trabajadores) {
            System.out.println(t);
        }
    }
}
class Jefe extends Problema4_Nomina {
    double sueldo;
    public Jefe(String nombres, String apellidos, String direccion, String DNI, double sueldo) {
        super(nombres, apellidos, direccion, DNI, null);
        this.sueldo = sueldo;
    }
    public double calcularSueldo() {
        return sueldo;
    }
}
class FijosMensuales extends Problema4_Nomina {
    double sueldo;
    public FijosMensuales(String nombres, String apellidos, String direccion, String DNI, Jefe jefe, double sueldo) {
        super(nombres, apellidos, direccion, DNI, jefe);
        this.sueldo = sueldo;
    }
    public double calcularSueldo() {
        return sueldo;
    }
}
class PorHoras extends Problema4_Nomina {
    double valorHora;
    int horasTrabajadas;
    public PorHoras(String nombres, String apellidos, String direccion, String DNI, Jefe jefe, double valorHora, int horasTrabajadas) {
        super(nombres, apellidos, direccion, DNI, jefe);
        this.valorHora = valorHora;
        this.horasTrabajadas = horasTrabajadas;
    }
    public double calcularSueldo() {
        if (horasTrabajadas <= 40) {
            return horasTrabajadas * valorHora;
        } else {
            return 40 * valorHora + (horasTrabajadas - 40) * (valorHora * 1.5);
        }
    }
}
class Comisionistas extends Problema4_Nomina {
    int ventasMes;
    double comision;

    public Comisionistas(String nombres, String apellidos, String direccion, String DNI, Jefe jefe, int ventasMes, double comision) {
        super(nombres, apellidos, direccion, DNI, jefe);
        this.ventasMes = ventasMes;
        this.comision = comision;
    }
    public double calcularSueldo() {
        return ventasMes * comision;
    }
}