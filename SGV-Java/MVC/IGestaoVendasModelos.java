package MVC;



import java.util.List;
import MVC.Exceptions.*;
import java.io.FileNotFoundException;
/**
 * Escreva a descrição da interface IGereVendasModelos aqui.
 * 
 * @author (seu nome) 
 * @version (número da versão ou data)
 */

public interface IGestaoVendasModelos
{
    /**
     * Lista ordenada alfabeticamente com os códigos dos produtos nunca comprados e o seu respectivo total
     * @return List<String>
     */
    public List<String> q1();
    /**
     * Dado um mês válido, determinar o número total global de vendas realizadas e 
     * o número total de clientes distintos que as fizeram, filial a filial
     * @param mes
     * @return String
     */
    public String q2F(int mes); 
    /**
     * Dado um mês válido, determinar o número total global de vendas realizadas e 
     * o número total de clientes distintos que as fizeram
     * @param mes
     * @return String
     */
    public String q2G(int mes); 
    /**
     * Lista de quantas compras um cliente fez, quantos produtos distintos comprou e quanto gastou no total.
     * @param codCliente
     * @return List<String>
     */         
    public List<String> q3(String codCliente) throws ClienteNaoExisteException;
    /**
     * Lista de quantas vezes um produto foi comprado, por quantos clientes diferentes e 
     * o total facturado.
     * @param codProduto
     * @return List<String>
     */
    public List<String> q4(String codProd) throws ProdutoNaoExisteException;
    /**
     * Lista de códigos de produtos e quantos que um cliente mais comprou.
     * @param codCliente
     * @return List<String>
     */
    public List<String> q5 (String codCliente) throws ClienteNaoExisteException;
    /**
     * Lista dos n produtos mais vendidos em todo o ano (em número de unidades vendidas) indicando o número total de 
     * distintos clientes que o compraram
     * @param x
     * @return List<String>
     */
    public List<String> q6 (int x) throws NumeroInvalidoException;
    /**
     * Três maiores compradores em termos de dinheiro facturado, para cada filial.
     * @return String
     */
    public String q7 ();
    /**
     * Lista dos códigos dos n clientes que compraram mais produtos diferentes,indicando quantos
     * (Ordenados por número de produtos)
     * @param x
     * @return List<Strig>
     */
    public List<String> q8 (int x) throws NumeroInvalidoException;
    /**
     * Lista dos n clientes que mais o compraram um produto e, para cada um, qual o valor gasto.
     * @param codProduto, n
     * @return List<String>
     */
    public List<String> q9 (String codProduto, int n) throws NumeroInvalidoException, ProdutoNaoExisteException;
    /**
     * Lista de mês a mês, e para cada mês filial a filial, a facturação total com cada produto.
     * @return List<String>
     */
    public List<String> q10();
    /**
     * Número total de compras por mês
     * @return String
     */
    public String numTotalVendasMes();
    /**
     * Facturação total por mês para cada filial e o valor total global.
     * @return String
     */
    public String vendasGlobalFilial();
    /** 
     * Lista de números de distintos clientes que compraram em cada mês filial a filial
     * @return List<String>
     */
    public List<String> numClientesMes();
    /**
     * Povoa os cátalogos do sistema.
     * @param titulo
     */
    public void lerFicheiros(String titulo) throws FileNotFoundException;
}
