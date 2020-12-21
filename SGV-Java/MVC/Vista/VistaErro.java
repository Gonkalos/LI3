package MVC.Vista;


import MVC.IGestaoVendasVista;

/**
 * Classe que implementa um erro
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public class VistaErro implements IGestaoVendasVista
{
    //Imprime um erro
    
    /** 
     * Método que apresenta o erro
     */
    public void show(){
        System.out.println("\f##########################################");
        System.out.println("############# Opção Inválido #############");
        System.out.println("##########################################");
        System.out.println("\n\n\nIntroduza 'Enter' para prosseguir.");
    }
    
    public void show(int n){}
    
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
