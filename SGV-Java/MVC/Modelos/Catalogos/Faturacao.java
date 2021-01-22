package MVC.Modelos.Catalogos;

import MVC.Modelos.ModelosBase.*;
import MVC.Modelos.Constantes;
import java.io.Serializable;
import java.util.*;
import java.text.DecimalFormat;
import MVC.Exceptions.ProdutoJaExisteException;
import MVC.Modelos.Meses;

/**
 * Classe que implementa uma faturação
 * Lista por páginas uma List
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public class Faturacao implements Serializable, IFaturacao{
    
    // Variáveis de Instância
    
    /**
     * Map de faturas com uma String do codigo do cliente e uma matriz
     */
    private Map<String,Matriz> faturas;
    /**
     * Número de meses
     */
    final static int numMeses = Constantes.numMeses;
    /**
     * Número de filiais
     */
    final static int numFiliais = Constantes.numFiliais;
    
    //Construtores
    
    /**
     * Construtores da classe Faturação
     * Declaração dos construtores por omissao (vazio), parametrizado e de cópia
     */
    
    /**
     * Construtor por omissão da Faturação
     */
    public Faturacao(){
        this.faturas = new HashMap<>();
    }
    
    /**
     * Construtor parametrizado da Faturação
     * Aceita como parâmetro um map de faturas com uma String do codigo do cliente e uma matriz
     * @param faturas
     */
    public Faturacao(Map<String,Matriz> faturas){
        this.faturas = new HashMap<>();
        for(String codProduto : faturas.keySet()){
            Matriz matriz = this.faturas.get(codProduto);
            Matriz matrizAux = new Matriz();
            this.faturas.put(codProduto, matrizAux);
        }
    }
    
    /**
     * Construtor de cópia da Faturação
     * Aceita como parâmetro outra Faturação e utiliza os métodos
     * de acesso ao valor da variável de instancia
     * @param Faturação
     */
    public Faturacao(Faturacao faturacao){
        this.faturas = faturacao.getFaturas();
    }
    
    //métodos de instância
    
    //Gets
    
    /**
     * Devolve as faturas
     * @return faturasAux
     */
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
    
    //Insere um produto
    
    /**
     * Método que insere um protudo
     * @param produto
     */
    public void insereProduto(String produto) throws ProdutoJaExisteException{
        if(this.faturas.containsKey(produto)){
            throw new ProdutoJaExisteException(produto);
        }
        this.faturas.put(produto, new Matriz());
    }
    
    // Atualiza a Faturação com uma venda
    
    /**
     * Atualiza a faturação com uma nova venda
     * @param venda
     */
    private void atualizaFaturacao(Venda venda){
        if(this.faturas.containsKey(venda.getCodProd())){
            Fatura fatura = this.faturas.get(venda.getCodProd()).getFatura(venda.getMes() - 1, venda.getFilial()-1);
            fatura.atualizaFatura(venda.getQuantidade(),venda.getPreco(),venda.getTipo());
        }
    }
    
    // Adiciona uma venda
    
    /**
     * Métudo que adiciona uma nova venda
     * @param vendas
     */
    public void adicionaVendas (Collection<Venda> vendas){
        for (Venda venda : vendas){
            this.atualizaFaturacao(venda);
        }
    }
    
    // toString
    
    /**
     * Metodo que devolve a representação em String da Faturação
     * @return sb.toString().
     */
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
    
    // Clear
    
    /**
     * Métudo que limpa uma fatura
     */
    public void clear(){
        for(Matriz matriz : this.faturas.values()){
            matriz.clear();
        }
        this.faturas.clear();
    }
    
    //Gets
    
    /**
     * Devolve o produto
     * @return faturas.keySet()
     */
    public Set<String> getProdutos(){
        return this.faturas.keySet();
    }
    
    /**
     * Devolve uma fatura atravez do código do produto, do mês e de uma filial
     * @param codProduto, mes e filial
     * @return faturas.get(codProduto).getFatura(mes, filial).clone();
     */
    public Fatura getFatura(String codProduto, int mes, int filial){
        return this.faturas.get(codProduto).getFatura(mes, filial).clone();
    }
    
    // Tamanho
    /**
     * Método que retorna o tamanho de uma fatura
     * @return faturas.size()
     */
    public int size(){
        return this.faturas.size();
    }
}