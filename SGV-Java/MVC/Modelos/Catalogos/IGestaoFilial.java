package MVC.Modelos.Catalogos;

import MVC.Modelos.ModelosBase.Venda;
import MVC.Modelos.ModelosBase.Fatura;
import java.util.Collection;
import java.util.Set;

public interface IGestaoFilial{

    public String toString();

    public void adicionaVendas(Collection<Venda> vendas);

    public void clear();

    public Set<String> getClientes();

    public Set<String> getProdutos(String codCliente);

    public Fatura getFatura(String codCliente, String codProduto, int mes, int filial);

    public boolean clienteExiste(String codCliente);

    public boolean produtoExiste(String codCliente, String codProduto);

    public boolean produtoExiste(String codProduto); 
}
