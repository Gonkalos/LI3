package MVC.Vista;

import MVC.IGestaoVendasVista;

/**
 * Classe que implementa o menu principal
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public class MenuPrincipal implements IGestaoVendasVista {
    
    //Imprime um menu
    
    /** 
     * Método que apresenta o menu principal  
     */
    public void show() {
        System.out.println("|\f|***************************************************************************|");
        System.out.println("|*********************** Sistema de Gestão de Vendas ***********************|");
        System.out.println("|***************************************************************************|\n\n");
        System.out.println("------------------------------ Menu Principal ------------------------------\n");
        System.out.println("1 - Consultas Estatísticas");
        System.out.println("2 - Consultas Interativas");
        System.out.println("0 - Sair");
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
