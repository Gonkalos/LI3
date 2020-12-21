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

public class CatalogoClientes implements ICatalogoClientes, Serializable{
    
    // Variáveis de Instância

    private Collection<Cliente> clientes;

    private int numeroClientesLidos;

    // Comparador de clientes por ordem alfabética dos seus códigos
    Comparator<Cliente> compClienteCodigo = ((cliente1,cliente2) -> cliente1.getCodigoCliente().compareTo(cliente2.getCodigoCliente()));
    
    // Construtores

    public CatalogoClientes(){
        this.clientes = new TreeSet<>(compClienteCodigo);
        //this.clientes = new HashSet<>();
        //this.clientes = new ArrayList<>();
        this.numeroClientesLidos = 0;
    }

    public CatalogoClientes(Collection<Cliente> clientes, int numeroClientesLidos){
        this.clientes = new TreeSet<>(compClienteCodigo);
        //this.clientes = new HashSet<>();
        //this.clientes = new ArrayList();
        for(Cliente c : clientes){
            this.clientes.add(c.clone());
        }
        this.numeroClientesLidos = numeroClientesLidos;
    }

    public CatalogoClientes(CatalogoClientes catClientes){
        this.clientes = catClientes.getClientes();
        this.numeroClientesLidos = catClientes.getNumeroClientesLidos();
    }
    
    // Devolve o TreeSet do CatalogoClientes

    public Collection<Cliente> getClientes(){
        Collection<Cliente> aux = new TreeSet<>(compClienteCodigo);
        //Collection<Cliente> aux = new HashSet<>();
        //Collection<Cliente> aux = new ArrayList<>();
        for(Cliente c : this.clientes){
            aux.add(c.clone());
        }
        return aux;
    }

    // Devolve o número de clientes lidos

    public int getNumeroClientesLidos(){
        return this.numeroClientesLidos;
    }

    // Adiciona um Cliente ao CatalogoClientes

    public void addCliente(Cliente cliente){
        this.clientes.add(cliente.clone());
    }   

    // Liberta o CatalogoClientes da memória

    public void libertaCatalogoClientes(){
        this.clientes.clear();
    }

    // Valida as string de clientes e, caso sejam válidas, cria para cada string um Cliente e adiciona ao CatalogoClientes

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

    // Verifica a igualdade de dois objectos

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

    // Metodo que devolve a representação em String do CatalogoClientes

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
    
    // Cria uma cópia de CatalogoClientes

    public CatalogoClientes clone(){
        return new CatalogoClientes(this);
    }

    // Determina se um cliente existe no CatalogoClientes

    public boolean clienteExiste (String codCliente){
        return this.clientes.contains(new Cliente(codCliente));
    }
    
}
