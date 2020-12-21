package MVC.Modelos.ModelosBase;

import MVC.Modelos.ModelosBase.Cliente;

public interface ICliente{

    public String getCodigoCliente();

    public boolean equals(Object o);

    public String toString();

    public Cliente clone();
}
