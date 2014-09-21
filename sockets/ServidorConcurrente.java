import java.io.*;
import java.net.*;

public class ServidorConcurrente
{
	public static void main(String[] arg)
	{
            try{
                ServerSocket skServidor = new ServerSocket(Integer.parseInt(arg[0]));
		System.out.println("Escuchando en el puerto " + Integer.parseInt(arg[0]));

		for(;;){
			Socket skCliente = skServidor.accept();
			System.out.println("Sirviendo al cliente: " + skCliente.getRemoteSocketAddress());
                        Thread t = new HiloServidor(skCliente);
                        t.start();
		}
            }catch(IOException e){
                System.out.println("Error");
            }
	}
}