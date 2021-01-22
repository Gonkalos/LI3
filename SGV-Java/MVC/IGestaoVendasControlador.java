package MVC;


/**
 * Interface IGereVendasControlador da classe GestaoVendasControlador
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */

public interface IGestaoVendasControlador
{
    /**
     * Atualiza os modelos
     * @param modelos novo valor de modelos
     */
    public void setModelos(IGestaoVendasModelos modelos);
    /**
     * Atualiza a vista
     * @param vista novo valor de vista
     */
    public void setVista(IGestaoVendasVista vista);
    /**
     * Método que dá início ao controlador(Executa o menu principal)
     */
    public void executa();
}
