import java.io.*;
import java.net.*;

public class HiloServidor extends Thread{
    private Socket skCliente;
    
    public HiloServidor(Socket p_cliente){
        this.skCliente = p_cliente;
    }
    
    public void enviarMensaje(Socket sk, String mensaje) throws IOException {
        OutputStream aux = sk.getOutputStream();
        DataOutputStream flujo = new DataOutputStream(aux);
        flujo.writeUTF(mensaje);
    }

    public String recibirMensaje(Socket sk) throws IOException {
        InputStream aux = sk.getInputStream();
        DataInputStream flujo = new DataInputStream(aux);
        return(flujo.readUTF());
    }

    public int segundos(int edad){
        return edad*365*24*60*60;
    }
    
    public int minutos(int edad){
        return edad*365*24*60;
    }
    
    public void run() {
        String orden = "";
        int sel = 0;
        try{
            while (1 != 0) {
                orden = recibirMensaje(skCliente);
                sel = Integer.parseInt(orden.substring(0, 1));
                
                if(sel == 3){
                    System.out.println("El cliente " + skCliente.getRemoteSocketAddress() + " ha finalizado la sesión!");
                    enviarMensaje(skCliente, "Se cierra la conexión!");
                    skCliente.close();
                    break;
                }
                else if(sel == 1){
                    System.out.println("El cliente " + skCliente.getRemoteSocketAddress() + " ha vivido " + segundos(Integer.parseInt(orden.substring(1, orden.length()))) + " segundos.");
                    enviarMensaje(skCliente, "Has vivido " + segundos(Integer.parseInt(orden.substring(1, orden.length()))) + " segundos.");
                }
                else if(sel == 2){
                    System.out.println("El cliente " + skCliente.getRemoteSocketAddress() + " ha vivido " + minutos(Integer.parseInt(orden.substring(1, orden.length()))) + " minutos.");
                    enviarMensaje(skCliente, "Has vivido " + segundos(Integer.parseInt(orden.substring(1, orden.length()))) + " minutos.");
                }
            }
            
        }catch(IOException e){
            System.out.println("Error de conexión con el cliente");
        }
    }   
}
