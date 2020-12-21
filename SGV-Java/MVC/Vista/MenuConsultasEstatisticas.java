package MVC.Vista;

import MVC.IGestaoVendasVista;

/**
 * Classe que implementa o menu de consultas estatisticas
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public class MenuConsultasEstatisticas implements IGestaoVendasVista
{
    //Imprime um menu
    
    /** 
     * Método que apresenta o menu de consultas estatisticas
     */
    public void show() {
        System.out.println("\f|*********************************************************************************|");
        System.out.println("|************************** Sistema de Gestão de Vendas **************************|");
        System.out.println("|*********************************************************************************|\n\n");
        System.out.println("----------------------------- Consultas Estatísticas -----------------------------\n");
        System.out.println(" 1 - Número total de compras por mês.");
        System.out.println(" 2 - Faturação total por mês, filial a filial e global.");
        System.out.println(" 3 - Número de clientes que compraram em cada mês, filial a filial.");
        System.out.println(" 0 - Sair");
        System.out.println("\nInsira Opção:");
    }
    
    public void show(int n){ }
    
    //métodos de instância
    
    //Gets
    
    /**
     * Devolve o numero de páginas
     * @return 0
     */
    public int getNumPaginas(){
        return 0;
    }
    
    /**
     * Devolve a página atual
     * @return 0
     */
    public int getPaginaAtual(){
        return 0;
    }
}
