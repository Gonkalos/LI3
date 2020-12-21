package MVC.Modelos.Catalogos;

import MVC.Modelos.ModelosBase.*;
import MVC.Modelos.Constantes;
import java.io.Serializable;
import java.util.*;
import java.text.DecimalFormat;
import MVC.Exceptions.ProdutoJaExisteException;
import MVC.Modelos.Meses;

public class Faturacao implements Serializable, IFaturacao{
    
    // Variáveis de Instância

    private Map<String,Matriz> faturas;

    final static int numMeses = Constantes.numMeses;

    final static int numFiliais = Constantes.numFiliais;
    
    // Construtores

    public Faturacao(){
        this.faturas = new HashMap<>();
    }

    public Faturacao(Map<String,Matriz> faturas){
        this.faturas = new HashMap<>();
        for(String codProduto : faturas.keySet()){
            Matriz matriz = this.faturas.get(codProduto);
            Matriz matrizAux = new Matriz();
            this.faturas.put(codProduto, matrizAux);
        }
    }

    public Faturacao(Faturacao faturacao){
        this.faturas = faturacao.getFaturas();
    }

    // Gets

    public Map<String,Matriz> getFaturas(){
        Map<String,Matriz> faturasAux = new HashMap<>();
        for(String codProduto : this.faturas.keySet()){
            Matriz matriz = this.faturas.get(codProduto);
            Matriz matrizAux = new Matriz();
            for(int i = 0; i < numMeses; i++){
                for (int j = 0; j < numFiliais; j++){
                    Fatura fatura = matriz.getFatura(i,j);
                    matrizAux.setFatura(fatura, i, j);
                }
            }
            faturasAux.put(codProduto, matrizAux);
        }
        return faturasAux;
    }
    
    // Insere um produto

    public void insereProduto(String produto) throws ProdutoJaExisteException{
        if(this.faturas.containsKey(produto)){
            throw new ProdutoJaExisteException(produto);
        }
        this.faturas.put(produto, new Matriz());
    }
    
    // Atualiza a Faturação com uma venda

    private void atualizaFaturacao(Venda venda){
        if(this.faturas.containsKey(venda.getCodProd())){
            Fatura fatura = this.faturas.get(venda.getCodProd()).getFatura(venda.getMes() - 1, venda.getFilial()-1);
            fatura.atualizaFatura(venda.getQuantidade(),venda.getPreco(),venda.getTipo());
        }
    }
    
    // Adiciona uma venda

    public void adicionaVendas (Collection<Venda> vendas){
        for (Venda venda : vendas){
            this.atualizaFaturacao(venda);
        }
    }
    
    // toString

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Faturacao Global:\n");
        for(String s: this.faturas.keySet()){
            sb.append("Produto :" + s);
            Matriz matriz = faturas.get(s);
            for(int i = 0 ; i < numMeses ; i++){
                sb.append("\n----------- Mes: " + Meses.getMes(i) + " -----------\n");
                for(int j = 0 ; j < numFiliais ; j++){
                    Fatura fatura = matriz.getFatura(i,j);
                    sb.append("Filial " + (j+1) +"\n" + fatura.toString() + "\n");
                }
            }
        }
        return sb.toString();
    }

    // Método que limpa uma fatura

    public void clear(){
        for(Matriz matriz : this.faturas.values()){
            matriz.clear();
        }
        this.faturas.clear();
    }
    
    // Devolve o produto

    public Set<String> getProdutos(){
        return this.faturas.keySet();
    }
    
    // Devolve uma fatura atravez do código do produto, do mês e de uma filial

    public Fatura getFatura(String codProduto, int mes, int filial){
        return this.faturas.get(codProduto).getFatura(mes, filial).clone();
    }
    
    // Método que retorna o tamanho de uma fatura

    public int size(){
        return this.faturas.size();
    }
}