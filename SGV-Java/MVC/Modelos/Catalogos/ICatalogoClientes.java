package MVC.Modelos.Catalogos;

import java.util.Collection;
import MVC.Modelos.ModelosBase.Cliente;
import MVC.Modelos.Catalogos.CatalogoClientes;

public interface ICatalogoClientes{

    public Collection<Cliente> getClientes();

    public int getNumeroClientesLidos();

    public void addCliente(Cliente cliente);

    public void libertaCatalogoClientes();

    public CatalogoClientes validaClientes(Collection<String> clientes);

    public boolean clienteExiste(String codCliente);

    public boolean equals(Object o);

    public String toString();

    public CatalogoClientes clone();
}
