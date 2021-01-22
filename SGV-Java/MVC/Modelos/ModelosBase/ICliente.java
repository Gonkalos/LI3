package MVC.Modelos.ModelosBase;

import MVC.Modelos.ModelosBase.Cliente;

/**
 * Interface ICliente da classe Cliente.
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public interface ICliente
{
    /**
      * Devolve o código de Cliente
      * @return codigoCliente
      */
    public String getCodigoCliente();
    /**
     * Verifica a igualdade de dois objectos
     * @param objecto
     * @return boolean
     */
    public boolean equals(Object o);
    /**
     * Metodo que devolve a representação em String do Cliente
     * @return codigoCliente
     */
    public String toString();
    /**
     * Cria uma cópia de Cliente
     * @return Cliente
     */
    public Cliente clone();
}
