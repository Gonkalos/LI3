package MVC;


/**
 * Interface IGestaoVendasVista da classe GestãoVendasApp
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */

public interface IGestaoVendasVista
{
    /**
     * Método que pede uma opção para apresentar um menu
     */
    public void show();
    /**
     * Método para apresentar um menu e ler uma opção
     * @param op
     */
    public void show(int opcao);
    /**
     * Devolve a página atual
     * @return 0
     */
    public int getPaginaAtual();
    /**
     * Devolve o número de páginas
     * @return 0
     */
    public int getNumPaginas();
}
