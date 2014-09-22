import java.io.Serializable;
import java.rmi.*;
import java.rmi.server.*;

public class ObjetoRemoto extends UnicastRemoteObject implements InterfazRemoto, Serializable
{
    public ObjetoRemoto() throws RemoteException
    {
        super();
    }
    
    public int calcularSegundos(int anyos)
    {
        return anyos * 365 * 24 * 60 * 60;
    }
    
    public int calcularMinutos(int anyos)
    {
        return anyos * 365 * 24 * 60;
    }
}