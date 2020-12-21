package MVC;

import java.util.List;
import MVC.Exceptions.*;
import java.io.FileNotFoundException;

public interface IGestaoVendasModelos {

    public List<String> q1();

    public String q2F(int mes); 

    public String q2G(int mes); 

    public List<String> q3(String codCliente) throws ClienteNaoExisteException;

    public List<String> q4(String codProd) throws ProdutoNaoExisteException;

    public List<String> q5(String codCliente) throws ClienteNaoExisteException;

    public List<String> q6(int x) throws NumeroInvalidoException;

    public String q7();

    public List<String> q8(int x) throws NumeroInvalidoException;

    public List<String> q9(String codProduto, int n) throws NumeroInvalidoException, ProdutoNaoExisteException;

    public List<String> q10();

    public String numTotalVendasMes();

    public String vendasGlobalFilial();

    public List<String> numClientesMes();

    public void lerFicheiros(String titulo) throws FileNotFoundException;
}
