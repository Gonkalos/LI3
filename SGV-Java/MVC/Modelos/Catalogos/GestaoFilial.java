package MVC.Modelos.Catalogos;

import MVC.Modelos.Constantes;
import MVC.Modelos.ModelosBase.*;
import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;
import java.io.Serializable;
import MVC.Modelos.Meses;

/**
  * Classe que implementa um Gestao Filial.
  * 
  * @author (Grupo 26) 
  * @version (8/6/2019)
  */
public class GestaoFilial implements Serializable, IGestaoFilial{
    // Variáveis de Instância
    /** Gestao filial */
    private Map <String, Map <String, Matriz>> gestaoFilial;
    /** Constante que representa os meses */
    final static int numMeses = Constantes.numMeses;
    /** Constante que representa o número de filiai */
    final static int numFiliais = Constantes.numFiliais;
    
    
    //Construtores
    
    /**
     * Construtores da classe GestaoFilial
     * Declaração dos construtores por omissao (vazio), parametrizado e de cópia
     */
    
    /**
     * Construtor por omissão de GestaoFilial
     */
    public GestaoFilial(){
        this.gestaoFilial = new HashMap<>();
    }
    
    /**
     * Construtor de cópia de GestaoFilial
     * Aceita como parâmetro outro Cliente e utiliza os métodos
     * de acesso ao valor da variável de instancia
     * @param GestaoFilial original
     */
    public GestaoFilial (GestaoFilial gf){
        this.gestaoFilial = gf.getGestaoFilial();
    }
    
    /**
     * Construtor parametrizado de GestaoFilial
     * Aceita como parâmetro um gestor
     * @param gestor
     */
    public GestaoFilial (Map<String,Map<String,Matriz>> gestor){
        this.gestaoFilial = new HashMap<>();
        for (String codCliente : gestor.keySet()){
            Map <String, Matriz> produtos = gestor.get(codCliente); 
            Map <String, Matriz> produtosAux = new HashMap<>();     
            for (String codProduto : produtos.keySet()){
                Matriz matriz = produtos.get(codProduto);
                Matriz matrizAux = new Matriz();
                for (int i = 0; i < numMeses; i++){
                    for (int j = 0; j < numFiliais; j++){
                        Fatura fatura = matriz.getFatura(i,j);
                        matrizAux.setFatura(fatura, i, j);
                    }
                }
            }
            this.gestaoFilial.put(codCliente, produtosAux);
        }    
    }

    
    
    //métodos de instância
    
    //Gets
    
    /**
      * Devolve o gestaoFilial
      * @return gestor
      */
    public Map <String, Map <String, Matriz>> getGestaoFilial(){
        Map <String, Map <String, Matriz>> aux = new HashMap<>();
        for (String codCliente : this.gestaoFilial.keySet()){
            Map <String, Matriz> produtos = this.gestaoFilial.get(codCliente);
            Map <String, Matriz> produtosAux = new HashMap<>(); 
            for (String codProduto : produtos.keySet()){
                Matriz matriz = produtos.get(codProduto);
                Matriz matrizAux = new Matriz();
                for (int i = 0; i < numFiliais; i++){
                    for (int j = 0; j < numMeses; j++){
                        Fatura fatura = matriz.getFatura(i,j);
                        matrizAux.setFatura(fatura, i, j);
                    }
                }
            }
            aux.put(codCliente, produtosAux);
        }  
        return aux;
    }
    
    // Clone

    /**
     * Cria uma cópia de GestaoFilial
     * @return GestaoFilial
     */
    public GestaoFilial clone(){
        return new GestaoFilial(this);
    }
    
    //Equals
    
    /**
     * Verifica a igualdade de dois objectos
     * @param objecto
     * @return boolean
     */
    public boolean equals (Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        GestaoFilial gf = (GestaoFilial)o;
        return this.gestaoFilial.equals(gf.getGestaoFilial());
    }
    
    // toString
    
    /**
     * Metodo que devolve a representação em String do GestaoFilial
     * @return String gestor
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (String codCliente : this.gestaoFilial.keySet()){
            sb.append("Cliente: " + codCliente + "\n");
            Map <String, Matriz> produtos = this.gestaoFilial.get(codCliente);
            for (String codProduto : produtos.keySet()){
                sb.append("\tProduto: " + codProduto + "\n");;
                Matriz matriz = produtos.get(codProduto);
                for (int i = 0; i < numMeses; i++){
                    sb.append("\t Mês " + Meses.getMes(i) + ":\n");
                    for (int j = 0; j < numFiliais; j++){
                        Fatura fatura = matriz.getFatura(i,j);
                        sb.append("\t  Filial " + (j+1) + ":\n");
                        sb.append(fatura.toString()+"\n\n\n");
                    }
                }
            }
        }
        return sb.toString();
    }
    
    /**
     * Adiciona uma venda e no caso de ela existir atualiza os valores da matriz.
     * @param Venda
     */
    public void adicionaVenda (Venda venda){
        Map <String, Matriz> produtos;
        Matriz matriz;
        if (this.gestaoFilial.containsKey(venda.getCodCli())){
            produtos = this.gestaoFilial.get(venda.getCodCli());
            if (produtos.containsKey(venda.getCodProd())){
                matriz = produtos.get(venda.getCodProd());
                Fatura fatura = matriz.getFatura(venda.getMes() - 1, venda.getFilial() - 1);
                fatura.atualizaFatura(venda.getQuantidade(), venda.getPreco(), venda.getTipo());
            }
            else {
                matriz = new Matriz();
                Fatura fatura = matriz.getFatura(venda.getMes() - 1, venda.getFilial() - 1);
                fatura.atualizaFatura(venda.getQuantidade(), venda.getPreco(), venda.getTipo());
                produtos.put(venda.getCodProd(), matriz);
            }
        }
        else {
            produtos = new HashMap<>();
            matriz = new Matriz();
            Fatura fatura = matriz.getFatura(venda.getMes() - 1, venda.getFilial() - 1);
            fatura.atualizaFatura(venda.getQuantidade(), venda.getPreco(), venda.getTipo());
            produtos.put(venda.getCodProd(), matriz);
            this.gestaoFilial.put(venda.getCodCli(), produtos);
        }
    }
            
    /**
     * Insere todas as vendas de uma Collection<Venda>
     * @param Collection<Venda>
     */
    public void adicionaVendas (Collection<Venda> vendas){
        for (Venda v : vendas){
            adicionaVenda(v);
        }
    }
    
    /**
     * Liberta o catálogo GestaoFilial da memória
     */
    public void clear(){
        for (String codCliente : this.gestaoFilial.keySet()){
            Map <String,Matriz> produtos = this.gestaoFilial.get(codCliente);
            for (String codProduto : produtos.keySet()){
                Matriz meses = produtos.get(codProduto);
                meses.clear();
            }
            produtos.clear();
        }
        this.gestaoFilial.clear();
    }
    
    /**
     * Retorna os clientes que existem na GestaoFilial
     * @return Set<String>
     */
    public Set<String> getClientes(){
        return this.gestaoFilial.keySet();
    }

    /**
     * Retorna os produtos que existem na GestaoFilial
     * @return Set<String>
     */
    public Set<String> getProdutos(String codCliente){
        return this.gestaoFilial.get(codCliente).keySet();
    }

    /**
     * Retorna uma Fatura de um produto que um cliente comprou num dado mês e numa dada filial
     * @param Strig, String, int, int
     * @return Fatura
     */
    public Fatura getFatura (String codCliente, String codProduto, int mes, int filial){
        return this.gestaoFilial.get(codCliente).get(codProduto).getFatura(mes, filial);
    }

    /**
     * Determina se um cliente existe na GestaoFilial
     * @param String
     * @return boolean
     */
    public boolean clienteExiste (String codCliente){
        return this.gestaoFilial.containsKey(codCliente);
    }
    
    /**
     * Determina se um produto foi comprado por um clientes
     * @param String, String
     * @return boolean
     */
    public boolean produtoExiste (String codCliente, String codProduto){
        return this.gestaoFilial.get(codCliente).containsKey(codProduto);
    }
    
    /**
     * Determina se um produto existe na GestaoFilial
     * @param String
     * @return boolean
     */
    public boolean produtoExiste(String codProduto){
        for(String codCli : this.gestaoFilial.keySet()){
            Map <String, Matriz> produtos = this.gestaoFilial.get(codCli);
            for(String codProd : produtos.keySet()){
                if(codProd.equals(codProduto)){
                    return true;
                }
            }
        }
        return false;
    }
}