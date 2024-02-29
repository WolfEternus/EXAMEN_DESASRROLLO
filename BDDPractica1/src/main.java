import java.net.*;
import java.io.*;
import sun.net.*;
// Implementación del servidor de datagramas UDP. Envía una cadena tras petición
class servidorUDP {
public static void main( String args[] ) {
DatagramSocket s = (DatagramSocket)null;
DatagramPacket enviap,recibep;
byte ibuffer[] = new byte[100];
String cadena = "Hola Tutorial de Java!\n";
InetAddress IP = (InetAddress)null;
int longitud = cadena.length();
int puertoEnvio = 4321;
int puertoRecep = 4322;
int puertoRemoto;
// Intentamos conseguir la dirección IP del host
try {
IP = InetAddress.getLocalHost();
} catch( UnknownHostException e ) {
System.out.println( "No encuentro al host breogan" );
System.exit( -1 );
}
// Establecemos el servidor para escuchar en el socket 4322
try {
s = new DatagramSocket( puertoRecep );
} catch( SocketException e ) {
System.out.println( "Error - "+e.toString() ); }
// Creamos un paquete de solicitud en el cliente y nos quedamos esperando a sus peticiones
recibep = new DatagramPacket( ibuffer,longitud );
try {
s.receive( recibep );
} catch( IOException e ) {
System.out.println( "Error - "+e.toString() );
}
// Creamos un paquete para enviar al cliente y lo enviamos
cadena.getBytes( 0,longitud,ibuffer,0 );
enviap = new DatagramPacket( ibuffer,longitud,IP,puertoEnvio );
try {
s.send( enviap );
} catch( IOException e ) {
System.out.println( "Error - "+e.toString() );
System.exit( -1 );
}
// Cerramos el socket
s.close();
}
}