package MVC.Modelos.ModelosBase;


/**
 * Classe que implementa uma Fatura.
 * Os preços são reais e as quantidades inteiras.
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public class Fatura implements IFatura
{
    //Variáveis de instancia
    
    /**  Quantidade de Tipo Promoção */
    private int quantidadeP;
    /**  Quantidade de Tipo Normal */
    private int quantidadeN;
    /**  Preço de Tipo Promoção */
    private double precoP;
    /**  Preço de Tipo Normal */
    private double precoN;
    
    //Construtores
    
    /**
     * Construtores da classe Fatura
     * Declaração dos construtores por omissao (vazio), parametrizado e de cópia
     */
    
    /**
     * Construtor por omissão de Fatura
     */
    public Fatura(){
        this.quantidadeP = 0;
        this.quantidadeN = 0;
        this.precoP = 0.0;
        this.precoN = 0.0;
    }
        
    /**
     * Construtor parametrizado de Fatura
     * Aceita como parâmetro duas quantidades e dois preços, diferenciados em tipo Promoção e Normal
     * @param quantidadeP, quantidadeN, precoP, precoN
     */
    public Fatura(int quantidadeP, int quantidadeN, double precoP, double precoN){
        this.quantidadeP = quantidadeP;
        this.quantidadeN = quantidadeN; 
        this.precoP = precoP;
        this.precoN = precoN;
    }
    
    /**
     * Construtor de cópia de Fatura
     * Aceita como parâmetro outra Fatura e utiliza os métodos
     * de acesso ao valor das variáveis de instância
     * @param Fatura original
     */
    public Fatura(Fatura f){
        this.quantidadeP = f.getQuantidadeP();
        this.quantidadeN = f.getQuantidadeN();
        this.precoP = f.getPrecoP();
        this.precoN = f.getPrecoN();
    }
    
    
    //métodos de instância
    
    //Gets
    
    /**
      * Devolve a quantidade de tipo Promoção
      * @return quantidadeP
      */
    public int getQuantidadeP(){
        return this.quantidadeP;
    }
    
    /**
      * Devolve a quantidade de tipo Normal
      * @return quantidadeN
      */
    public int getQuantidadeN(){
        return this.quantidadeN;
    }
    
    /**
      * Devolve o preço de tipo Promoção
      * @return precoP
      */
    public double getPrecoP(){
        return this.precoP;
    }
    
    /**
      * Devolve o preço de tipo Normal
      * @return precoN
      */
    public double getPrecoN(){
        return this.precoN;
    }
    
    /**
      * Devolve o total faturado
      * @return totalFaturado
      */
    public double getFaturado(){
        return (this.precoP * this.quantidadeP) + (this.precoN * this.quantidadeN);
    }
    
    /**
      * Devolve a quantidade total.
      * @return quantidadeTotal
      */
    public int getQuantidade(){
        return this.quantidadeP + this.quantidadeN;
    }
    
    //Equals
    
    /**
     * Verifica a igualdade de dois objectos
     * @param objecto
     * @return boolean
     */
    public boolean equals(Object o){
        if(o==this) {
            return true;
        }
        if(o==null || o.getClass() != this.getClass()) {
            return false;
        }
        Fatura f = (Fatura)o;
        return (this.quantidadeP == f.getQuantidadeP() && this.quantidadeN == f.getQuantidadeN() && this.precoP == f.getPrecoP() && 
                this.precoN == f.getPrecoP());
    }
    
    // toString
    
    /**
     * Método que devolve a representação em String da Fatura
     * @return String com quantidadeN, precoN, quantidadeP e precoP 
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\t   Tipo N:\n");
        sb.append("\t      -Quantidade: " + this.quantidadeN + ";\n");
        sb.append("\t      -Total Faturado: " + this.precoN + ";\n");
        sb.append("\t   Tipo P:\n");
        sb.append("\t      -Quantidade: " + this.quantidadeP + ";\n");
        sb.append("\t      -Total Faturado: " + this.precoP + ";");
        return sb.toString();
    }   
    
    // Clone

    /**
     * Cria uma cópia de Fatura
     * @return Fatura
     */
    public Fatura clone(){
        return new Fatura(this);
    }
    
    
    /**
     * Verifica se uma Fatura é vazia(não existem quantidades).
     * @return boolean
     */
    public boolean faturaVazia(){
        return quantidadeN == 0 && quantidadeP == 0;
    }
    
    /**
     * Atualiza a Fatura(preço e quantidade) tendo em conta o tipo.
     * @param quantidade, preco, tipo
     */
    public void atualizaFatura(int quantidade, double preco, String tipo){
        if(tipo.equals("N")){
            this.quantidadeN += quantidade;
            this.precoN += preco;
        }else{
            this.quantidadeP += quantidade;
            this.precoP += preco;
        }
    }
}
