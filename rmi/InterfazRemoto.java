import java.rmi.Remote;

public interface InterfazRemoto extends Remote
{
    public int calcularSegundos(int anyos) throws java.rmi.RemoteException;
    public int calcularMinutos(int anyos) throws java.rmi.RemoteException;
}