/**
 * Dadas las siguientes clases, que expresan una relación de herencia entre las entidades:
 * 
 * Se desea gestionar la venta de entradas para un espectáculo en un teatro. El patio de butacas del teatro se 
 * divide en varias zonas, cada una identificada por su nombre. Los datos de las zonas son los mostrados en la siguiente tabla:
 * NOMBRE ZONA	NÚMERO DE LOCALIDADES	PRECIO NORMA	PRECIO ABONADO
 * Principal	200	25$	17.5$
 * PalcoB	40	70$	40$
 * Central	400	20$	14$
 * Lateral	100	15.5$	10$
 * Para realizar la compra de una entrada, un espectador debe indicar la zona que desea y presentar al vendedor el documento que 
 * justifique que tiene algún tipo de descuento (estudiante, abonado o pensionista). El vendedor sacará la entrada del tipo apropiado 
 * y de la zona indicada, en el momento de la compra se asignará a la entrada un identificador (un número entero) que permitirá la 
 * identificación de la entrada en todas las operaciones que posteriormente se desee realizar con ella.
 * Una entrada tiene como datos asociados su identificador, la zona a la que pertenece y el nombre del comprador.
 * Los precios de las entradas dependen de la zona y del tipo de entrada según lo explicado a continuación:
 * Entradas normales: su precio es el precio normal de la zona elegida sin ningún tipo de descuento.
 * Entradas reducidas (para estudiantes o pensionistas): su precio tiene una rebaja del 15% sobre el precio normal de la zona elegida.
 * Entradas abonado: su precio es el precio para abonados de la zona elegida.
 * La interacción entre el vendedor y la aplicación es la descrita en los siguientes casos de usos.
 * @author Luis
 */
import java.util.*;
public class Problema5_VentaEntradas {
    public static void main(String[] args) {
        Zona Principal = new Zona("Principal", 150, 60.0, 45.0, 0);
        Zona PalcoB = new Zona("PalcoB", 80, 50.0, 35.0, 0);
        Zona central = new Zona("Central", 500, 25.0, 18.0, 0);
        Zona lateral = new Zona("Lateral", 120, 12.0, 8.0, 0);

        ArrayList<Ticket> ticketsVendidos = new ArrayList<>();

        Cliente cli1 = new Cliente("Principal", "normal", null);
        Cliente cli2 = new Cliente("PalcoB", "abonado", null);
        Cliente cli3 = new Cliente("Central", "reducida", null);
        Cliente cli4 = new Cliente("Lateral", "normal", null);

        cli1.realizarCompra(cli1, Principal, ticketsVendidos, 101, "Diana");
        cli2.realizarCompra(cli2, PalcoB, ticketsVendidos, 102, "Mario");
        cli3.realizarCompra(cli3, central, ticketsVendidos, 103, "Andrea");
        cli4.realizarCompra(cli4, lateral, ticketsVendidos, 104, "Pedro");

        cli1.verTicket(101, ticketsVendidos);
        cli2.verTicket(102, ticketsVendidos);
        cli3.verTicket(103, ticketsVendidos);
        cli4.verTicket(104, ticketsVendidos);
    }
}
class Zona {
    public String nombreZona;
    public int totalAsientos;
    public double precioBase;
    public double precioDescuento;
    public int asientosOcupados;

    public Zona() {}

    public Zona(String nombreZona, int totalAsientos, double precioBase, double precioDescuento, int asientosOcupados) {
        this.nombreZona = nombreZona;
        this.totalAsientos = totalAsientos;
        this.precioBase = precioBase;
        this.precioDescuento = precioDescuento;
        this.asientosOcupados = asientosOcupados;
    }
    public boolean hayDisponibilidad() {
        return totalAsientos > asientosOcupados;
    }
}
class Ticket {
    public int codigo;
    public Zona zonaAsignada;
    public String comprador;

    public Ticket() {}

    public Ticket(int codigo, Zona zonaAsignada, String comprador) {
        this.codigo = codigo;
        this.zonaAsignada = zonaAsignada;
        this.comprador = comprador;
    }
    public double obtenerPrecio() {
        return zonaAsignada.precioBase;
    }
}
class TicketAbonado extends Ticket {
    public TicketAbonado(int codigo, Zona zona, String comprador) {
        super(codigo, zona, comprador);
    }
    @Override
    public double obtenerPrecio() {
        return zonaAsignada.precioDescuento;
    }
}
class TicketReducido extends Ticket {
    public TicketReducido(int codigo, Zona zona, String comprador) {
        super(codigo, zona, comprador);
    }
    @Override
    public double obtenerPrecio() {
        return zonaAsignada.precioBase * 0.85;
    }
}
class Cliente {
    public String zonaDeseada;
    public String tipoEntrada;
    public Ticket ticketComprado;

    public Cliente() {}

    public Cliente(String zonaDeseada, String tipoEntrada, Ticket ticketComprado) {
        this.zonaDeseada = zonaDeseada;
        this.tipoEntrada = tipoEntrada;
        this.ticketComprado = ticketComprado;
    }
    public void realizarCompra(Cliente cliente, Zona zonaSeleccionada, ArrayList<Ticket> listaTickets, int codigo, String comprador) {
        if (zonaSeleccionada.hayDisponibilidad()) {
            Ticket ticket;
            switch (cliente.tipoEntrada.toLowerCase()) {
                case "abonado":
                    ticket = new TicketAbonado(codigo, zonaSeleccionada, comprador);
                    break;
                case "reducida":
                    ticket = new TicketReducido(codigo, zonaSeleccionada, comprador);
                    break;
                default:
                    ticket = new Ticket(codigo, zonaSeleccionada, comprador);
                    break;
            }
            zonaSeleccionada.asientosOcupados++;
            cliente.ticketComprado = ticket;
            listaTickets.add(ticket);
            System.out.println("Ticket vendido: Código " + codigo + ", Precio: $" + ticket.obtenerPrecio());
        } else {
            System.out.println("Zona sin disponibilidad. Compra no realizada.");
        }
    }
    public void verTicket(int codigo, ArrayList<Ticket> listaTickets) {
        for (Ticket t : listaTickets) {
            if (t.codigo == codigo) {
                System.out.println("Ticket encontrado:");
                System.out.println("Código: " + t.codigo);
                System.out.println("Comprador: " + t.comprador);
                System.out.println("Zona: " + t.zonaAsignada.nombreZona);
                System.out.println("Precio: $" + t.obtenerPrecio());
                return;
            }
        }
        System.out.println("Ticket no encontrado.");
    }
}
