package MVC.Modelos.ModelosBase;

import MVC.Modelos.ModelosBase.*;

public interface IProduto{

    public String getCodigoProduto();

    public boolean equals(Object o);

    public String toString();

    public Produto clone();
}
