/**
 * Implemente un sistema de envío de mensajes a móviles. Existen dos tipos de mensajes que se pueden enviar entre móviles, 
 * mensajes de texto (SMS) y mensajes que contienen imágenes (MMS). Por un lado, los mensajes de texto contienen un mensaje 
 * en caracteres que se desea enviar de un móvil a otro. Por otro lado, los mensajes que contienen imágenes almacenan 
 * información sobre la imagen a enviar, la cual se representará por el nombre del fichero que la contiene. Independientemente 
 * del tipo de mensaje, cada mensaje tendrá asociado un remitente de dicho mensaje y un destinatario. Ambos estarán definidos 
 * obligatoriamente por un número de móvil, y opcionalmente se podrá guardar información sobre su nombre. Además, los métodos 
 * enviarMensaje y visualizarMensaje deben estar definidos.
 * @author Luis
 */
public class Problema3_MensajesMoviles {
    public static void main(String[] args) {
        SMS sms = new SMS(911223344, 922334455, "Hola bro ¿Puedes salir mañana?");
        sms.enviarMensaje();
        sms.verMensaje();
        System.out.println();
        MMS mms = new MMS(933112200, 944556677, "foto_juegos.jpg");
        mms.enviarMensaje();
        mms.verMensaje();
    }
}
class Mensaje {
    public int remitente;
    public int destinatario;
    public Mensaje(int remitente, int destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
    }
    public void enviarMensaje() {
    }
    public void verMensaje() {
    }
}
class SMS extends Mensaje {
    public String mensaje;
    public SMS(int remitente, int destinatario, String mensaje) {
        super(remitente, destinatario);
        this.mensaje = mensaje;
    }
    public void enviarMensaje() {
        System.out.println("Se ha enviado el mensaje a: " + destinatario);
    }
    public void verMensaje() {
        System.out.println("Ha recibido un mensaje de: " + remitente + " : " + mensaje);
    }
}
class MMS extends Mensaje {

    public String imagen;

    public MMS(int remitente, int destinatario, String imagen) {
        super(remitente, destinatario);
        this.imagen = imagen;
    }
    public void enviarMensaje() {
        System.out.println("Se ha enviado la imagen a: " + destinatario);
    }
    public void verMensaje() {
        System.out.println("Ha recibido una imagen de: " + remitente + " : " + imagen);
    }
}