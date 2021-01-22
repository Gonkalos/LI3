package MVC.Modelos.Catalogos;

import java.util.Collection;
import MVC.Modelos.ModelosBase.Cliente;
import MVC.Modelos.Catalogos.CatalogoClientes;

/**
 * Interface ICatalogoClientes da classe CatalogoClientes.
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public interface ICatalogoClientes{
    
    /**
      * Devolve o TreeSet do CatalogoClientes 
      * @return TreeSet de Clientes
      */
    public Collection<Cliente> getClientes();
    
    /**
     * Devolve o número de clientes lidos
     * @return int
     */
    public int getNumeroClientesLidos();
    
    /**
     * Adiciona um Cliente ao CatalogoClientes
     * @param Cliente
     */
    public void addCliente(Cliente cliente);
    
    /**
     * Liberta o CatalogoClientes da memória
     */
    public void libertaCatalogoClientes();
    
    /**
     * Valida as string de clientes e, caso sejam válidas, cria para cada string um Cliente e adiciona ao CatalogoClientes
     * @param coleção de strings
     * @return CatalogoClientes
     */
    public CatalogoClientes validaClientes(Collection<String> clientes);
    
    /**
     * Determina se um cliente existe no CatalogoClientes
     * @param código do cliente
     * @return boolean
     */
    public boolean clienteExiste (String codCliente);
    
    /**
     * Verifica a igualdade de dois objectos
     * @param objecto
     * @return boolean
     */
    public boolean equals(Object o);
    
    /**
     * Metodo que devolve a representação em String do CatalogoClientes
     * @return string
     */
    public String toString();
    
    /**
     * Cria uma cópia de CatalogoClientes
     * @return CatalogoClientes
     */
    public CatalogoClientes clone();
    
}
