import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente 
{
    public void enviarMensaje(Socket sk, String mensaje) throws IOException
    {
        OutputStream aux = sk.getOutputStream();
        DataOutputStream flujo = new DataOutputStream(aux);
        flujo.writeUTF(mensaje);
    }
    
    public String recibirMensaje(Socket sk) throws IOException {
        InputStream aux = sk.getInputStream();
        DataInputStream flujo = new DataInputStream(aux);
        return(flujo.readUTF());
    }
    
    public String menu() throws IOException{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        System.out.println("Selecciona una acción para continuar:");
        System.out.println("1 Calcula tus segundos de vida a partir de una edad(aprox)");
        System.out.println("2 Calcula tus minutos de vida a partir de una edad(aprox)");
        System.out.println("3 Salir");
        
        String sel = br.readLine();
        
        if(Integer.parseInt(sel) != 3 && Integer.parseInt(sel) > 0 && Integer.parseInt(sel) < 4){
            System.out.println("Escribe tu edad");
            sel += br.readLine();
            return sel;
        }
        else{
            return "3";
        }   
    }
    
    public static void main(String[] arg)
    {
        Cliente cl = new Cliente();
        String cadena = "";
        
        try {
            Socket skCliente = new Socket(arg[0], Integer.parseInt(arg[1]));
            System.out.println("===================================\nBienvenido a la calculadora de vida!\n===================================");

            while(0!=1){
                cadena = cl.menu();
                cl.enviarMensaje(skCliente, cadena);
                System.out.println("Mensaje del servidor ==> " + cl.recibirMensaje(skCliente));
                if(Integer.parseInt("" + cadena.charAt(0)) == 3)
                    break;
            }
            
            skCliente.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}