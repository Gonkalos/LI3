package MVC.Modelos.Catalogos;

import MVC.Modelos.ModelosBase.Venda;
import MVC.Modelos.ModelosBase.Fatura;
import java.util.Collection;
import java.util.Set;

public interface IGestaoFilial{
    /**
     * Metodo que devolve a representação em String do GestaoFilial
     * @return String gestor
     */
    public String toString();
    /**
     * Insere todas as vendas de uma Collection<Venda>
     * @param Collection<Venda>
     */
    public void adicionaVendas (Collection<Venda> vendas);
    /**
     * Liberta o catálogo GestaoFilial da memória
     */
    public void clear();
    /**
     * Retorna os clientes que existem na GestaoFilial
     * @return Set<String>
     */
    public Set<String> getClientes();
    /**
     * Retorna os produtos que existem na GestaoFilial
     * @return Set<String>
     */
    public Set<String> getProdutos(String codCliente);
    /**
     * Retorna uma Fatura de um produto que um cliente comprou num dado mês e numa dada filial
     * @param Strig, String, int, int
     * @return Fatura
     */
    public Fatura getFatura (String codCliente, String codProduto, int mes, int filial);
    /**
     * Determina se um cliente existe na GestaoFilial
     * @param String
     * @return boolean
     */
    public boolean clienteExiste (String codCliente);
    /**
     * Determina se um produto foi comprado por um clientes
     * @param String, String
     * @return boolean
     */
    public boolean produtoExiste (String codCliente, String codProduto);
    /**
     * Determina se um produto existe na GestaoFilial
     * @param String
     * @return boolean
     */
    public boolean produtoExiste(String codProduto);
    
}
