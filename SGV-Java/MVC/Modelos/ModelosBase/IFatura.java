package MVC.Modelos.ModelosBase;

public interface IFatura{

    public int getQuantidadeP();

    public int getQuantidadeN();

    public double getPrecoP();

    public double getPrecoN();

    public double getFaturado();

    public int getQuantidade();

    public boolean equals(Object o);

    public String toString();

    public Fatura clone();

    public boolean faturaVazia();

    public void atualizaFatura(int quantidade, double faturado, String tipo);
}
