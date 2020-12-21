package MVC.Modelos.Catalogos;

import MVC.Modelos.Constantes;
import MVC.Modelos.ModelosBase.*;
import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;
import java.io.Serializable;
import MVC.Modelos.Meses;

public class GestaoFilial implements Serializable, IGestaoFilial{

    // Variáveis de Instância

    private Map <String, Map <String, Matriz>> gestaoFilial;

    final static int numMeses = Constantes.numMeses;

    final static int numFiliais = Constantes.numFiliais;

    // Construtores

    public GestaoFilial(){
        this.gestaoFilial = new HashMap<>();
    }

    public GestaoFilial (GestaoFilial gf){
        this.gestaoFilial = gf.getGestaoFilial();
    }

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

    // Devolve o gestaoFilial

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

    public GestaoFilial clone(){
        return new GestaoFilial(this);
    }
    
    // Equals

    public boolean equals (Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        GestaoFilial gf = (GestaoFilial)o;
        return this.gestaoFilial.equals(gf.getGestaoFilial());
    }
    
    // toString

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
    
    // Adiciona uma venda e no caso de ela existir atualiza os valores da matriz.

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
            
    // Insere todas as vendas de uma Collection<Venda>

    public void adicionaVendas (Collection<Venda> vendas){
        for (Venda v : vendas){
            adicionaVenda(v);
        }
    }
    
    // Liberta o catálogo GestaoFilial da memória

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
    
    // Retorna os clientes que existem na GestaoFilial

    public Set<String> getClientes(){
        return this.gestaoFilial.keySet();
    }

    // Retorna os produtos que existem na GestaoFilial

    public Set<String> getProdutos(String codCliente){
        return this.gestaoFilial.get(codCliente).keySet();
    }

    // Retorna uma Fatura de um produto que um cliente comprou num dado mês e numa dada filial

    public Fatura getFatura (String codCliente, String codProduto, int mes, int filial){
        return this.gestaoFilial.get(codCliente).get(codProduto).getFatura(mes, filial);
    }

    // Determina se um cliente existe na GestaoFilial

    public boolean clienteExiste (String codCliente){
        return this.gestaoFilial.containsKey(codCliente);
    }
    
    // Determina se um produto foi comprado por um clientes

    public boolean produtoExiste (String codCliente, String codProduto){
        return this.gestaoFilial.get(codCliente).containsKey(codProduto);
    }
    
    // Determina se um produto existe na GestaoFilial

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