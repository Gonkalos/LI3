package MVC.Modelos.Catalogos;

import MVC.Modelos.ModelosBase.Cliente;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Classe que implementa um CatalogoClientes
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public class CatalogoClientes implements ICatalogoClientes, Serializable{
    
    // Variáveis de Instância
    
    /**
     * Coleção de clientes 
     */
    private Collection<Cliente> clientes;
    
    /**
     * Número de clientes lidos do ficheiro Clientes.txt
     */
    private int numeroClientesLidos;
    
    /**
     * Comparador de clientes por ordem alfabética dos seus códigos
     * @param Cliente, Cliente
     * @return Integer
     */
    Comparator<Cliente> compClienteCodigo = ((cliente1,cliente2) -> cliente1.getCodigoCliente().compareTo(cliente2.getCodigoCliente()));
    
    /**
     * Construtores da classe CatalogoClientes
     * Declaração dos construtores por omissao (vazio), parametrizado e de cópia
     */
    
    /**
     * Construtor por omissao de CatalogoClientes
     */
    public CatalogoClientes(){
        this.clientes = new TreeSet<>(compClienteCodigo);
        //this.clientes = new HashSet<>();
        //this.clientes = new ArrayList<>();
        this.numeroClientesLidos = 0;
    }
    
    /**
     * Construtor parametrizado de CatalogoClientes
     * @param coleção de clientes, número de clientes lidos
     */
    public CatalogoClientes(Collection<Cliente> clientes, int numeroClientesLidos){
        this.clientes = new TreeSet<>(compClienteCodigo);
        //this.clientes = new HashSet<>();
        //this.clientes = new ArrayList();
        for(Cliente c : clientes){
            this.clientes.add(c.clone());
        }
        this.numeroClientesLidos = numeroClientesLidos;
    }
    
    /**
     * Construtor de cópia de CatalogoClientes
     * @param CatalogoClientes original
     */
    public CatalogoClientes(CatalogoClientes catClientes){
        this.clientes = catClientes.getClientes();
        this.numeroClientesLidos = catClientes.getNumeroClientesLidos();
    }
    
    /**
      * Devolve o TreeSet do CatalogoClientes 
      * @return TreeSet de Clientes
      */
    public Collection<Cliente> getClientes(){
        Collection<Cliente> aux = new TreeSet<>(compClienteCodigo);
        //Collection<Cliente> aux = new HashSet<>();
        //Collection<Cliente> aux = new ArrayList<>();
        for(Cliente c : this.clientes){
            aux.add(c.clone());
        }
        return aux;
    }
    
    /**
     * Devolve o número de clientes lidos
     * @return int
     */
    public int getNumeroClientesLidos(){
        return this.numeroClientesLidos;
    }
    
    /**
     * Adiciona um Cliente ao CatalogoClientes
     * @param Cliente
     */
    public void addCliente(Cliente cliente){
        this.clientes.add(cliente.clone());
    }   

    /**
     * Liberta o CatalogoClientes da memória
     */
    public void libertaCatalogoClientes(){
        this.clientes.clear();
    }
    
    /**
     * Valida as string de clientes e, caso sejam válidas, cria para cada string um Cliente e adiciona ao CatalogoClientes
     * @param coleção de strings
     * @return CatalogoClientes
     */
    public CatalogoClientes validaClientes(Collection<String> clientes){
        Collection<Cliente> aux = new TreeSet<>(compClienteCodigo);
        //Collection<Cliente> aux = new HashSet<>();
        //ArrayList<Cliente> aux = new ArrayList<>();
        for(String s : clientes){
            if(Cliente.validaCodigoCliente(s)){
                Cliente c = new Cliente(s);
                aux.add(c.clone());
            }
        }
        return new CatalogoClientes(aux,clientes.size());
    }
    
    /**
     * Verifica a igualdade de dois objectos
     * @param objecto
     * @return boolean
     */
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        
        CatalogoClientes catClientes = (CatalogoClientes)o;
        
        return this.clientes.equals(catClientes.getClientes()) && (this.numeroClientesLidos == catClientes.getNumeroClientesLidos());
    }
    
    /**
     * Metodo que devolve a representação em String do CatalogoClientes
     * @return string
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("----------Catalogo de Clientes----------\n");
        for(Cliente c : this.clientes){
            sb.append(c.toString());
        }
        sb.append("O numero de clientes lidos foi de : " + this.numeroClientesLidos + ".\n");
        sb.append("O numero de clientes validos é de : " + this.clientes.size() + ".\n");
        return sb.toString();
    }
    
    /**
     * Cria uma cópia de CatalogoClientes
     * @return CatalogoClientes
     */
    public CatalogoClientes clone(){
        return new CatalogoClientes(this);
    }
    
    /**
     * Determina se um cliente existe no CatalogoClientes
     * @param código do cliente
     * @return boolean
     */
    public boolean clienteExiste (String codCliente){
        return this.clientes.contains(new Cliente(codCliente));
    }
    
}
