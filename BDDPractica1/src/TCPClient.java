// TCPClient.java

import java.net.*;
import java.io.*;
import sun.net.*;
// Implementación del cliente de datagramas UDP. Devuelve la salida de los servidores
class clienteUDP {
public static void main( String args[] ) {
int longitud = 100;
DatagramSocket s = (DatagramSocket)null;
DatagramPacket enviap,recibep;
byte ibuffer[] = new byte[100];
InetAddress IP = (InetAddress)null;
int puertoEnvio = 4322;
int puertoRecep = 4321;
// Abre una conexión y establece el cliente para recibir
// una petición en el socket 4321
try {
s = new DatagramSocket( puertoRecep );
} catch( SocketException e ) {
System.out.println( "Error - "+e.toString() );
}
// Crea una petición para enviar bytes. Intenta conseguir
// la dirección IP del host
try {
IP = InetAddress.getLocalHost();
} catch( UnknownHostException e ) {
System.out.println( "No encuentro el host" );
System.exit( -1 );
}
// Envía una petición para que responda el servidor
try {
enviap = new DatagramPacket( ibuffer,ibuffer.length,
IP,4322 );
s.send( enviap );
} catch( IOException e ) {
System.out.println( "Error - "+e.toString() );
}
// Consigue un controlador de fichero de entrada del socket y lee
// dicha entrada. Creamos un paquete descriptor para recibir el
// paquete UDP
recibep = new DatagramPacket( ibuffer,longitud );
// Espera a recibir un paquete
try {
s.receive( recibep );
} catch( IOException e ) {
System.out.println( "Error - "+e.toString() );
System.exit( -1 );
}
// Imprimimos los resultados de lo que conseguimos
System.out.println( "Recibido: "+recibep.getLength()+" bytes" );
String datos = new String( recibep.getData(),0 );
System.out.println( "Datos: "+datos );
System.out.println( "Recibido por puerto: "+recibep.getPort() );
// Cerramos la conexión y abandonamos
s.close();
}
}