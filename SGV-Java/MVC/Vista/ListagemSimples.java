package MVC.Vista;

import MVC.IGestaoVendasVista;

/**
 * Classe que implementa uma listagem simples
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public class ListagemSimples implements IGestaoVendasVista
{
    //Variáveis de instancia
    
    /**
     * Conteudo a listar
     */
    private String conteudo;
    
    //Construtores
    
    /**
     * Construtores da classe Listagem simples
     * Declaração dos construtores por omissao (vazio) e parametrizado
     */
    
    /**
     * Construtor por omissão da Listagem simples
     */
    public ListagemSimples() {
        this.conteudo = "n/a";
    }
    
    /**
     * Construtor parametrizado da Listagem simples
     * @param conteudo
     */
    public ListagemSimples(String conteudo) {
        this.conteudo = conteudo;
    }
    
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
    
    //imprime o conteudo caso exista 
    
    /**
     * Método para apresentar o menu e ler uma opção.
     */
    public void show() {
        if(this.conteudo.isEmpty()){
            System.out.println("#### Não existem elementos a listar. ###");
        }else{
            System.out.println(conteudo);
        }
    }
    
    public void show(int n) {}
}
