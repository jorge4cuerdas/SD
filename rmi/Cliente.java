import java.io.*;
import java.rmi.*;

public class Cliente
{
    public Cliente()
    {
        
    }
    
    public static void main(String[] args)
    {
        String host;
        String port;
        Cliente cl = new Cliente();
        int i = 0;
        
        if(args.length < 2){
            try{
                System.out.println("Parametros incorrectos");
            }
            catch(Exception ex){
                System.out.println("Error en los parametros");
            }
            
            return;
        }
        
        host = args[0];
        port = args[1];
        
        while(i==0){
            cl.menu(host, port);
        }
    }
    
    private void menu(String host, String port)
    {
        int opc = 0;
        InputStreamReader ent = new InputStreamReader(System.in);
        BufferedReader buf = new BufferedReader(ent);
        
        while(1!=0){
            System.out.println("1 Calcular los segundos vividos a partir de una edad");
            System.out.println("2 Calcular los minutos vividos a partir de una edad");
            System.out.println("3 Salir");
            
            try{
                opc = new Integer(buf.readLine()).intValue();
            }
            catch(Exception ex){
                System.out.println("Error al leer la operacion");
            }
            
            if(opc == 1){
                operar(opc, host, port);
            }
            else if(opc == 2){
                operar(opc, host, port);
            }
            else{
                System.out.println("Adios!");
                System.exit(0);
            }
        }
    }
    
    private void operar(int opc, String host, String port){
        InterfazRemoto objetoRemoto = null;
        InputStreamReader ent = new InputStreamReader(System.in);
        BufferedReader buf = new BufferedReader(ent);
        int edad = 0; 
        
        String servidor = "rmi://"+host+":"+port+"/ObjetoRemoto";
        
        try{
            System.setSecurityManager(new SecurityManager());
            objetoRemoto = (InterfazRemoto) Naming.lookup(servidor);
        }
        catch(Exception ex){
            System.out.println("Error en el naming.lookup");
            System.exit(0);
        }
        
            try{
                System.out.println("Introduce tu edad ");
                edad = new Integer(buf.readLine()).intValue();
            }
            catch(Exception ex){
                System.out.println("Error al leer la edad");
            }
            
            try{
                if(opc == 1){
                    System.out.println("Los segundos vividos son " + objetoRemoto.calcularSegundos(edad));
                }
                else{
                    System.out.println("Los minutos vividos son " + objetoRemoto.calcularMinutos(edad));
                }
            }
            catch(Exception ex)
            {
                System.out.println("Error al realizar la operacion");
            }
    }
}