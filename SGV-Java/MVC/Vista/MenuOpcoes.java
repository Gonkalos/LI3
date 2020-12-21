package MVC.Vista;

import MVC.IGestaoVendasVista;

/**
 * Classe que implementa o menu de opções
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public class MenuOpcoes implements IGestaoVendasVista
{
    //Imprime um erro
    
    /** 
     * Método que apresenta o menu de opções
     */
    
    public void show() {
        System.out.println("\f|*********************************************************************************|");
        System.out.println("|************************** Sistema de Gestão de Vendas **************************|");
        System.out.println("|*********************************************************************************|\n\n");
        System.out.println("---- Número total de vendas realizadas e de clientes distintos que as fizeram ----\n");
        System.out.println(" 1 - Filial a Filial.");
        System.out.println(" 2 - Global.");
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
