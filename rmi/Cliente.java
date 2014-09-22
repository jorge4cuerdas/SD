import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente
{
    String servidor = "";
    InterfazRemoto objetoRemoto;
    
    public int menu() throws IOException
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        System.out.println("====================================");
        System.out.println("Bienvenido a la calculadora de vida!");
        System.out.println("====================================");
        System.out.println("1 Calcula los segundos vividos a partir de una edad");
        System.out.println("2 Calcula los minutos vividos a partir de una edad");
        System.out.println("3 Salir");
        System.out.println("Selecciona una acción para continuar.");
        
        return (Integer.parseInt(br.readLine()));
    }
    
    public static void main(String[] args)
    {
        Cliente cl = new Cliente();
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        cl.servidor = "rmi://" + args[0] + "/" + args[1] + "/ObjetoRemoto";
        System.setSecurityManager(new RMISecurityManager());
        try {
            cl.objetoRemoto = (InterfazRemoto) Naming.lookup(cl.servidor);
        } catch (NotBoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        int seleccion = 1;
        int edad = 0;
        
        while(seleccion != 3){
            try {
                seleccion = cl.menu();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(seleccion == 1){
                System.out.print("Introduce tu edad: ");
                try {
                    edad = Integer.parseInt(br.readLine());
                    System.out.println("Has vivido: " + cl.objetoRemoto.calcularSegundos(edad) + " segundos.");
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else if(seleccion == 2){
                System.out.print("Introduce tu edad: ");
                try {
                    edad = Integer.parseInt(br.readLine());
                    System.out.println("Has vivido: " + cl.objetoRemoto.calcularMinutos(edad) + " minutos.");
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else{
                System.out.println("Hasta luego!");
                break;
            }
        }
    }
}