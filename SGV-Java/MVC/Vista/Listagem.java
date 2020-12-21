package MVC.Vista;

import MVC.IGestaoVendasVista;
/**
 * Classe que implementa uma listagem.
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public class Listagem implements IGestaoVendasVista
{
    //Variáveis de instancia
    
    /**
     * Título da listagem
     */
    private String titulo;
    /**
     * Conteudo da listagem
     */
    private String conteudo;
    
    //Construtores
    
    /**
     * Construtores da classe Listagem
     * Declaração dos construtores por omissao (vazio) e parametrizado
     */
    
    /**
     * Construtor por omissão da listagem
     */
    public Listagem() {
        this.titulo = "n/a";
        this.conteudo = "n/a";
    }
    
    /**
     * Construtor parametrizado da listagem
     * @param titulo e conteudo
     */
    public Listagem(String titulo,String conteudo) {
        this.titulo = titulo;
        this.conteudo = conteudo;
    }
    
    //métodos de instância
    
    //Gets
    
    /**
     * Devolve o número de páginas
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
    
    /**
     * Método para apresentar o conteudo caso exista
     */
    public void show() {
        System.out.println("\f");
        System.out.println(titulo + "\n");
        if(this.conteudo.isEmpty()){
            System.out.println("#### Não existem elementos a listar. ###");
        }else{
            System.out.println(conteudo);
        }
    }
    
    public void show(int n) {}
}
