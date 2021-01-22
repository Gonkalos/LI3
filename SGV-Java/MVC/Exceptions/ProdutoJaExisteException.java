package MVC.Exceptions;


/**
 * Escreva a descrição da classe FaturaJaExisteException aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class ProdutoJaExisteException extends Exception
{
    public ProdutoJaExisteException(String s){
        super(s);
    }
}
