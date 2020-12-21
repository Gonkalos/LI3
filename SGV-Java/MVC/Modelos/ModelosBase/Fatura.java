package MVC.Modelos.ModelosBase;

public class Fatura implements IFatura {

    // Variáveis de instancia

    private int quantidadeP;
    /**  Quantidade de Tipo Normal */
    private int quantidadeN;
    /**  Preço de Tipo Promoção */
    private double precoP;
    /**  Preço de Tipo Normal */
    private double precoN;
    
    //Construtores

    public Fatura(){
        this.quantidadeP = 0;
        this.quantidadeN = 0;
        this.precoP = 0.0;
        this.precoN = 0.0;
    }

    public Fatura(int quantidadeP, int quantidadeN, double precoP, double precoN){
        this.quantidadeP = quantidadeP;
        this.quantidadeN = quantidadeN; 
        this.precoP = precoP;
        this.precoN = precoN;
    }

    public Fatura(Fatura f){
        this.quantidadeP = f.getQuantidadeP();
        this.quantidadeN = f.getQuantidadeN();
        this.precoP = f.getPrecoP();
        this.precoN = f.getPrecoN();
    }

    // Devolve a quantidade de tipo Promoção

    public int getQuantidadeP(){
        return this.quantidadeP;
    }
    
    // Devolve a quantidade de tipo Normal

    public int getQuantidadeN(){
        return this.quantidadeN;
    }
    
    // Devolve o preço de tipo Promoção

    public double getPrecoP(){
        return this.precoP;
    }
    
    // Devolve o preço de tipo Normal

    public double getPrecoN(){
        return this.precoN;
    }
    
    // Devolve o total faturado

    public double getFaturado(){
        return (this.precoP * this.quantidadeP) + (this.precoN * this.quantidadeN);
    }
    
    // Devolve a quantidade total.

    public int getQuantidade(){
        return this.quantidadeP + this.quantidadeN;
    }
    
    // Equals

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

    public Fatura clone(){
        return new Fatura(this);
    }
    
    
    // Verifica se uma Fatura é vazia(não existem quantidades).

    public boolean faturaVazia(){
        return quantidadeN == 0 && quantidadeP == 0;
    }
    
    // Atualiza a Fatura(preço e quantidade) tendo em conta o tipo.

    public void atualizaFatura(int quantidade, double preco, String tipo){
        if(tipo.equals("N")){
            this.quantidadeN += quantidade;
            this.precoN += preco;
        }
        else{
            this.quantidadeP += quantidade;
            this.precoP += preco;
        }
    }
}
