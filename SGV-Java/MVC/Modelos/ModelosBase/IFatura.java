package MVC.Modelos.ModelosBase;


/**
 * Interface IFatura da classe Fatura.
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */

public interface IFatura
{
    /**
      * Devolve a quantidade de tipo Promoção
      * @return quantidadeP
      */
    public int getQuantidadeP();
    /**
      * Devolve a quantidade de tipo Normal
      * @return quantidadeN
      */
    public int getQuantidadeN();
    /**
      * Devolve o preço de tipo Promoção
      * @return precoP
      */
    public double getPrecoP();
    /**
      * Devolve o preço de tipo Normal
      * @return precoN
      */
    public double getPrecoN();
    /**
      * Devolve o total faturado
      * @return totalFaturado
      */
    public double getFaturado();
    /**
      * Devolve a quantidade total.
      * @return quantidadeTotal
      */
    public int getQuantidade();
    /**
     * Verifica a igualdade de dois objectos
     * @param objecto
     * @return boolean
     */
    public boolean equals(Object o);
    /**
     * Método que devolve a representação em String da Fatura
     * @return String com quantidadeN, precoN, quantidadeP e precoP 
     */
    public String toString();
    /**
     * Cria uma cópia de Fatura
     * @return Fatura
     */
    public Fatura clone();
    /**
     * Verifica se uma Fatura é vazia(não existem quantidades).
     * @return boolean
     */
    public boolean faturaVazia();
    /**
     * Atualiza a Fatura(preço e quantidade) tendo em conta o tipo.
     * @param quantidade, preco, tipo
     */
    public void atualizaFatura(int quantidade, double faturado, String tipo);
    
}
