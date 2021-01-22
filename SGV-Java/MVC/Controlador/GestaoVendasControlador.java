package MVC.Controlador;



import java.util.List;
import MVC.Input;
import MVC.IGestaoVendasControlador;
import MVC.IGestaoVendasVista;
import MVC.IGestaoVendasModelos;
import MVC.Vista.VistaErro;
import MVC.Vista.MenuPrincipal;
import MVC.Vista.MenuConsultasEstatisticas;
import MVC.Vista.MenuConsultasInterativas;
import MVC.Vista.ListagemLista;
import MVC.Vista.ListagemSimples;
import MVC.Vista.MenuOpcoes;
import MVC.Vista.Listagem;
import MVC.Exceptions.*;

/**
 * Escreva a descrição da classe GestaoVendasControlador aqui.
 * 
 * @author (seu nome) 
 * @version (08/06/2019)
 */
public class GestaoVendasControlador implements IGestaoVendasControlador
{
    /** Modelos */
    private IGestaoVendasModelos modelos;
    /** Vista */
    private IGestaoVendasVista vista;
    
    /**
     * Atualiza os modelos
     * @param modelos novo valor de modelos
     */
    public void setModelos(IGestaoVendasModelos modelos){
        this.modelos = modelos;
    }
    
    /**
     * Atualiza a vista
     * @param vista novo valor de vista
     */
    public void setVista(IGestaoVendasVista vista){
        this.vista = vista;
    }
    
    /**
     * Método que dá início ao controlador(Executa o menu principal)
     */
    public void executa(){
        int opcao = -1;
        do{
            vista.show();
            opcao = Input.lerInt();
            switch(opcao){
                case 0 : break;
                case 1 : this.executaConsultasEstatisticas();
                         break;
                case 2 : this.executaConsultasInterativas();
                         break;
                default : setVista(new VistaErro());
                          vista.show();
                          Input.lerString();
                          opcao = -1;
                         break;
            }
            setVista(new MenuPrincipal());
        }while(opcao != 0);
    }
    
    
    private void executaConsultasEstatisticas(){
        int opcao = -1;
        setVista(new MenuConsultasEstatisticas());
        do{
            vista.show();
            opcao = Input.lerInt();
            switch(opcao){
                case 0 : break;
                case 1 : this. numTotalVendasMes();
                         break;
                case 2 : this.vendasGlobalFilial();
                         break;
                case 3 : this.numClientesMes();
                         break;
                default : setVista(new VistaErro());
                          vista.show();
                          Input.lerString();
                          setVista(new MenuConsultasEstatisticas());
                          opcao = -1;
            }
        }while(opcao != 0);
    }
    
    private void executaConsultasInterativas(){
        int opcao = -1;
        setVista(new MenuConsultasInterativas());
        do{
            vista.show();
            opcao = Input.lerInt();
            switch(opcao){
                case 0 : setVista(new MenuPrincipal());
                        break;
                case 1 : this.q1();
                         break;
                case 2 : this.q2();
                         break;
                case 3 : this.q3();
                         break;
                case 4 : this.q4();
                         break;
                case 5 : this.q5();
                         break;
                case 6 : this.q6();
                         break;
                case 7 : this.q7();
                         break;
                case 8 : this.q8();
                         break;
                case 9 : this.q9();
                         break;
                case 10 : this.q10();
                         break;
                default : setVista(new VistaErro());
                          vista.show();
                          Input.lerString();
                          setVista(new MenuConsultasInterativas());
                          opcao = -1;
            }
        }while(opcao != 0);
    }
    
    
    
    private void q1() {
        String titulo = "------- Produtos nunca comprados -------";
        int opcao = 0;
        int pagina = 0;
        int paginaOP = 0;
        List<String> l = this.modelos.q1();
        setVista(new ListagemLista(titulo, l));
        do {
            vista.show(pagina);
            opcao = Input.lerInt();
            switch(opcao) {
                case 1 : pagina -= 1;
                         if(pagina < 0) pagina = 0;
                         vista.show(pagina);
                         break;
                case 2:
                        pagina += 1;
                        if(pagina >= vista.getNumPaginas()) pagina = vista.getNumPaginas() - 1;
                        vista.show(pagina);
                        break;
                case 3: int elem = vista.getNumPaginas();
                        setVista(new ListagemSimples("\n\nQual a página?"));
                        vista.show();
                        paginaOP = Input.lerInt();
                        if(paginaOP < 0 || paginaOP > elem){
                            setVista(new VistaErro());
                            vista.show();
                            Input.lerString();
                        }else{
                             pagina = paginaOP-1;
                        }
                        setVista(new ListagemLista(titulo, l));
                        vista.show(pagina);
                        break;
                case 0: setVista(new ListagemSimples("\n\nTotal produtos nunca comprados: " + l.size() + " produtos.\n\n\nPressione 'Enter' para retroceder."));
                        vista.show();
                        Input.lerString();
                        setVista(new MenuConsultasInterativas());
                        break;
                default: setVista(new VistaErro());
                         vista.show();
                         Input.lerString();
                         setVista(new ListagemLista(titulo, l));
                    break;
            }
        }while(opcao != 0);
        l.clear();
    }
    
    
    private void q2() {
        String titulo = "------- Número total de vendas realizadas e de clientes distintos que as fizeram -------";
        int opcao = 0;
        setVista(new MenuOpcoes());
        int mes = 0;
        do {
            vista.show();
            opcao = Input.lerInt();
            switch(opcao) {
                case 1: setVista(new Listagem(titulo,"Introduza o mês pretendido."));
                        vista.show();
                        mes = Input.lerInt();
                        mes--;
                        if(mes<0 && mes>11){
                            setVista(new VistaErro());
                            vista.show();
                            Input.lerString();
                            setVista(new MenuOpcoes());
                        }else{
                            setVista(new Listagem(titulo,this.modelos.q2F(mes)));
                            vista.show();
                            setVista(new ListagemSimples("\n\nPressione 'Enter' para retroceder."));
                            vista.show();
                            Input.lerString();
                            setVista(new MenuOpcoes());
                        }
                        break;
                case 2: setVista(new Listagem(titulo,"Introduza o mês pretendido."));
                        vista.show();
                        mes = Input.lerInt();
                        mes--;
                        if(mes<0 && mes>11){
                            setVista(new VistaErro());
                            vista.show();
                            Input.lerString();
                            setVista(new MenuOpcoes());
                        }else{
                            setVista(new Listagem(titulo,this.modelos.q2G(mes)));
                            vista.show();
                            setVista(new ListagemSimples("\n\nPressione 'Enter' para retroceder."));
                            vista.show();
                            Input.lerString();
                            setVista(new MenuOpcoes());
                        }
                        break;
                case 0: setVista(new ListagemSimples("\n\nPressione 'Enter' para retroceder."));
                        vista.show();
                        Input.lerString();
                        setVista(new MenuConsultasInterativas());
                        break;
                default: setVista(new VistaErro());
                         vista.show();
                         Input.lerString();
                         setVista(new MenuOpcoes());
                    break;
            }
        }while(opcao != 0);
    }
    
    private void q3(){
        String titulo = "------- Dados refentes às compras de um cliente -------";
        int opcao = 0;
        int pagina = 0;
        int paginaOP = 0;
        setVista(new Listagem(titulo,"Introduza um código de cliente."));
        vista.show();
        String codCli = Input.lerString();
        try{
            List<String> l = this.modelos.q3(codCli);
            setVista(new ListagemLista(titulo, l));
            do {
                vista.show(pagina);
                opcao = Input.lerInt();
                switch(opcao) {
                    case 1: pagina -= 1;
                            if(pagina < 0) pagina = 0;
                            vista.show(pagina);
                            break;
                    case 2: pagina += 1;
                            if(pagina >= vista.getNumPaginas()) pagina = vista.getNumPaginas() - 1;
                            vista.show(pagina);
                            break;
                    case 3: int elem = vista.getNumPaginas();
                            setVista(new ListagemSimples("\n\nQual a página?"));
                            vista.show();
                            paginaOP = Input.lerInt();
                            if(paginaOP < 0 || paginaOP > elem){
                                setVista(new VistaErro());
                                vista.show();
                                Input.lerString();
                            }else{
                                pagina = paginaOP-1;
                            }
                            setVista(new ListagemLista(titulo, l));
                            vista.show(pagina);
                            break;
                    case 0: setVista(new ListagemSimples("\n\n\nPressione 'Enter' para retroceder."));
                            vista.show();
                            Input.lerString();
                            setVista(new MenuConsultasInterativas());
                            break;
                    default: setVista(new VistaErro());
                             vista.show();
                             Input.lerString();
                             setVista(new ListagemLista(titulo, l));
                             break;
                }
            }while(opcao != 0);
            l.clear();
        }catch(ClienteNaoExisteException e){
            setVista(new ListagemSimples("\fNão existe nenhum cliente com o código " + e.getMessage() + ".\n\nPara Prosseguir pressione 'Enter'."));
            vista.show();
            Input.lerString();
            setVista(new MenuConsultasInterativas());
        }
    }
    
    private void q4 (){
        String titulo = "------- Dados refentes às vendas de um produto -------";
        int opcao = 0;
        int pagina = 0;
        int paginaOP = 0;
        setVista(new Listagem(titulo,"Introduza um código de produto."));
        vista.show();
        String codProd = Input.lerString();
        try{
            List<String> l = this.modelos.q4(codProd);
            setVista(new ListagemLista(titulo, l));
            do {
                vista.show(pagina);
                opcao = Input.lerInt();
                switch(opcao) {
                    case 1: pagina -= 1;
                            if(pagina < 0) pagina = 0;
                            vista.show(pagina);
                            break;
                    case 2: pagina += 1;
                            if(pagina >= vista.getNumPaginas()) pagina = vista.getNumPaginas() - 1;
                            vista.show(pagina);
                            break;
                    case 3: int elem = vista.getNumPaginas();
                            setVista(new ListagemSimples("\n\nQual a página?"));
                            vista.show();
                            paginaOP = Input.lerInt();
                            if(paginaOP < 0 || paginaOP > elem){
                                setVista(new VistaErro());
                                vista.show();
                                Input.lerString();
                            }else{
                                pagina = paginaOP-1;
                            }
                            setVista(new ListagemLista(titulo, l));
                            vista.show(pagina);
                            break;
                    case 0: setVista(new ListagemSimples("\n\n\nPressione 'Enter' para retroceder."));
                            vista.show();
                            Input.lerString();
                            setVista(new MenuConsultasInterativas());
                            break;
                    default: setVista(new VistaErro());
                             vista.show();
                             Input.lerString();
                             setVista(new ListagemLista(titulo, l));
                             break;
                }
            }while(opcao != 0);
            l.clear();
        }catch(ProdutoNaoExisteException e){
            setVista(new ListagemSimples("\fNão existe nenhum produto com o código " + e.getMessage() + ".\n\nPara Prosseguir pressione 'Enter'."));
            vista.show();
            Input.lerString();
            setVista(new MenuConsultasInterativas());
        }
    }
    
    
    private void q5 (){
        String titulo = "------- TOP de produtos que um cliente comprou -------";
        int opcao = 0;
        int pagina = 0;
        int paginaOP = 0;
        setVista(new Listagem(titulo,"Introduza um código de cliente."));
        vista.show();
        String codCli = Input.lerString();
        try{
            List<String> l = this.modelos.q5(codCli);
            setVista(new ListagemLista(titulo, l));
            do {
                vista.show(pagina);
                opcao = Input.lerInt();
                switch(opcao) {
                    case 1: pagina -= 1;
                            if(pagina < 0) pagina = 0;
                            vista.show(pagina);
                            break;
                    case 2: pagina += 1;
                            if(pagina >= vista.getNumPaginas()) pagina = vista.getNumPaginas() - 1;
                            vista.show(pagina);
                            break;
                    case 3: int elem = vista.getNumPaginas();
                            setVista(new ListagemSimples("\n\nQual a página?"));
                            vista.show();
                            paginaOP = Input.lerInt();
                            if(paginaOP < 0 || paginaOP > elem){
                                setVista(new VistaErro());
                                vista.show();
                                Input.lerString();
                            }else{
                                pagina = paginaOP-1;
                            }
                            setVista(new ListagemLista(titulo, l));
                            vista.show(pagina);
                            break;
                    case 0: setVista(new ListagemSimples("\n\nO cliente com o código " + codCli + " comprou  "+ l.size() +" produtos.\n\n\nPressione 'Enter' para retroceder."));
                            vista.show();
                            Input.lerString();
                            setVista(new MenuConsultasInterativas());
                            break;
                    default: setVista(new VistaErro());
                             vista.show();
                             Input.lerString();
                             setVista(new ListagemLista(titulo, l));
                             break;
                }
            }while(opcao != 0);
            l.clear();
        }catch(ClienteNaoExisteException e){
            setVista(new ListagemSimples("\fNão existe nenhum cliente com o código " + e.getMessage() + ".\n\nPara Prosseguir pressione 'Enter'."));
            vista.show();
            Input.lerString();
            setVista(new MenuConsultasInterativas());
        }
    }
    
    
    private void q6 (){
        String titulo = "------- TOP de produtos ano e número de clientes que os compraram -------";
        int opcao = 0;
        int pagina = 0;
        int paginaOP = 0;
        setVista(new Listagem(titulo,"Introduza um número."));
        vista.show();
        int escolha = Input.lerInt();
        try{
            List<String> l = this.modelos.q6(escolha);
            setVista(new ListagemLista(titulo, l));
            do {
                vista.show(pagina);
                opcao = Input.lerInt();
                switch(opcao) {
                    case 1: pagina -= 1;
                            if(pagina < 0) pagina = 0;
                            vista.show(pagina);
                            break;
                    case 2: pagina += 1;
                            if(pagina >= vista.getNumPaginas()) pagina = vista.getNumPaginas() - 1;
                            vista.show(pagina);
                            break;
                    case 3: int elem = vista.getNumPaginas();
                            setVista(new ListagemSimples("\n\nQual a página?"));
                            vista.show();
                            paginaOP = Input.lerInt();
                            if(paginaOP < 0 || paginaOP > elem){
                                setVista(new VistaErro());
                                vista.show();
                                Input.lerString();
                            }else{
                                pagina = paginaOP-1;
                            }
                            setVista(new ListagemLista(titulo, l));
                            vista.show(pagina);
                            break;
                    case 0: setVista(new ListagemSimples("\n\n\nPressione 'Enter' para retroceder."));
                            vista.show();
                            Input.lerString();
                            setVista(new MenuConsultasInterativas());
                            break;
                    default: setVista(new VistaErro());
                             vista.show();
                             Input.lerString();
                             setVista(new ListagemLista(titulo, l));
                             break;
                }
            }while(opcao != 0);
            l.clear();
        }catch(NumeroInvalidoException e){
            setVista(new ListagemSimples("\fO número " + e.getMessage() + " não satisafaz os requisitos.\n\nPara Prosseguir pressione 'Enter'."));
            vista.show();
            Input.lerString();
            setVista(new MenuConsultasInterativas());
        }
    }
    
    
    private void q7 (){
        String titulo = "------- TOP 3 maiores compradores filial a filial -------";
        String s = this.modelos.q7();
        setVista(new Listagem(titulo, s));
        vista.show();
        setVista(new ListagemSimples("\n\n\nPressione 'Enter' para retroceder."));
        vista.show();
        Input.lerString();
        setVista(new MenuConsultasInterativas());
    }
    
    
    private void q8(){
        String titulo = "------- TOP n maiores compradores de produtos distintos -------";
        int opcao = 0;
        int pagina = 0;
        int paginaOP = 0;
        setVista(new Listagem(titulo,"Introduza o número de produtos que pretende."));
        vista.show();
        int escolha = Input.lerInt();
        try{
            List<String> l = this.modelos.q8(escolha);
            setVista(new ListagemLista(titulo, l));
            do {
                vista.show(pagina);
                opcao = Input.lerInt();
                switch(opcao) {
                    case 1: pagina -= 1;
                            if(pagina < 0) pagina = 0;
                            vista.show(pagina);
                            break;
                    case 2: pagina += 1;
                            if(pagina >= vista.getNumPaginas()) pagina = vista.getNumPaginas() - 1;
                            vista.show(pagina);
                            break;
                    case 3: int elem = vista.getNumPaginas();
                            setVista(new ListagemSimples("\n\nQual a página?"));
                            vista.show();
                            paginaOP = Input.lerInt();
                            if(paginaOP < 0 || paginaOP > elem){
                                setVista(new VistaErro());
                                vista.show();
                                Input.lerString();
                            }else{
                                pagina = paginaOP-1;
                            }
                            setVista(new ListagemLista(titulo, l));
                            vista.show(pagina);
                            break;
                    case 0: setVista(new ListagemSimples("\n\n\nPressione 'Enter' para retroceder."));
                            vista.show();
                            Input.lerString();
                            setVista(new MenuConsultasInterativas());
                            break;
                    default: setVista(new VistaErro());
                             vista.show();
                             Input.lerString();
                             setVista(new ListagemLista(titulo, l));
                             break;
                }
            }while(opcao != 0);
            l.clear();
        }catch(NumeroInvalidoException e){
            setVista(new ListagemSimples("\fO número " + e.getMessage() + " não satisafaz os requisitos.\n\nPara Prosseguir pressione 'Enter'."));
            vista.show();
            Input.lerString();
            setVista(new MenuConsultasInterativas());
        }
    }
    
    
    private void q9() {
        String titulo = "------- TOP n clientes que compraram um determinado produto -------";
        int opcao = 0;
        int pagina = 0;
        int paginaOP = 0;
        setVista(new Listagem(titulo,"Introduza o código de um produto."));
        vista.show();
        String escolha = Input.lerString();
        setVista(new Listagem(titulo,"Introduza o número de clientes que pretende listar."));
        vista.show();
        int n = Input.lerInt();
        try{
            List<String> l = this.modelos.q9(escolha,n);
            setVista(new ListagemLista(titulo, l));
            do {
                vista.show(pagina);
                opcao = Input.lerInt();
                switch(opcao) {
                    case 1: pagina -= 1;
                            if(pagina < 0) pagina = 0;
                            vista.show(pagina);
                            break;
                    case 2: pagina += 1;
                            if(pagina >= vista.getNumPaginas()) pagina = vista.getNumPaginas() - 1;
                            vista.show(pagina);
                            break;
                    case 3: int elem = vista.getNumPaginas();
                            setVista(new ListagemSimples("\n\nQual a página?"));
                            vista.show();
                            paginaOP = Input.lerInt();
                            if(paginaOP < 0 || paginaOP > elem){
                                setVista(new VistaErro());
                                vista.show();
                                Input.lerString();
                            }else{
                                pagina = paginaOP-1;
                            }
                            setVista(new ListagemLista(titulo, l));
                            vista.show(pagina);
                            break;
                    case 0: setVista(new ListagemSimples("\n\n\nPressione 'Enter' para retroceder."));
                            vista.show();
                            Input.lerString();
                            setVista(new MenuConsultasInterativas());
                            break;
                    default: setVista(new VistaErro());
                             vista.show();
                             Input.lerString();
                             setVista(new ListagemLista(titulo, l));
                             break;
                }
            }while(opcao != 0);
            l.clear();
        }catch(NumeroInvalidoException e){
            setVista(new ListagemSimples("\fO número " + e.getMessage() + " não satisafaz os requisitos.\n\nPara Prosseguir pressione 'Enter'."));
            vista.show();
            Input.lerString();
            setVista(new MenuConsultasInterativas());
        }catch(ProdutoNaoExisteException e){
            setVista(new ListagemSimples("\fNão existe nenhum produto com o código " + e.getMessage() + ".\n\nPara Prosseguir pressione 'Enter'."));
            vista.show();
            Input.lerString();
            setVista(new MenuConsultasInterativas());
        }
    }
    
    
    private void q10() {
        String titulo = "------- Faturação total de cada produto, mês a mês, filial a filial -------";
        int opcao = 0;
        int pagina = 0;
        int paginaOP = 0;
        List<String> l = this.modelos.q10();
        setVista(new ListagemLista(titulo, l));
        do {
            vista.show(pagina);
            opcao = Input.lerInt();
            switch(opcao) {
                case 1 : pagina -= 1;
                         if(pagina < 0) pagina = 0;
                         vista.show(pagina);
                         break;
                case 2:
                        pagina += 1;
                        if(pagina >= vista.getNumPaginas()) pagina = vista.getNumPaginas() - 1;
                        vista.show(pagina);
                        break;
                case 3: int elem = vista.getNumPaginas();
                        setVista(new ListagemSimples("\n\nQual a página?"));
                        vista.show();
                        paginaOP = Input.lerInt();
                        if(paginaOP < 0 || paginaOP > elem){
                            setVista(new VistaErro());
                            vista.show();
                            Input.lerString();
                        }else{
                             pagina = paginaOP-1;
                        }
                        setVista(new ListagemLista(titulo, l));
                        vista.show(pagina);
                        break;
                case 0: setVista(new ListagemSimples("\n\n\nPressione 'Enter' para retroceder."));
                        vista.show();
                        Input.lerString();
                        setVista(new MenuConsultasInterativas());
                        break;
                default: setVista(new VistaErro());
                         vista.show();
                         Input.lerString();
                         setVista(new ListagemLista(titulo, l));
                    break;
            }
        }while(opcao != 0);
        l.clear();
    }
    
     //estatisticas

    private void numTotalVendasMes(){
        String titulo = "------- Número total de compras por mês -------";
        String s = this.modelos.numTotalVendasMes();
        setVista(new Listagem(titulo, s));
        vista.show();
        setVista(new ListagemSimples("\n\n\nPressione 'Enter' para retroceder."));
        vista.show();
        Input.lerString();
        setVista(new MenuConsultasEstatisticas());
    }
    
    
    private void vendasGlobalFilial(){
        String titulo = "------- Faturação total por mês, filial a filial e global -------";
        String s = this.modelos.vendasGlobalFilial();
        setVista(new Listagem(titulo, s));
        vista.show();
        setVista(new ListagemSimples("\n\n\nPressione 'Enter' para retroceder."));
        vista.show();
        Input.lerString();
        setVista(new MenuConsultasEstatisticas());
    }
    
    private void numClientesMes(){
        String titulo = "------- Faturação total de cada produto, mês a mês, filial a filial -------";
        int opcao = 0;
        int pagina = 0;
        int paginaOP = 0;
        List<String> l = this.modelos.numClientesMes();
        setVista(new ListagemLista(titulo, l));
        do {
            vista.show(pagina);
            opcao = Input.lerInt();
            switch(opcao) {
                case 1 : pagina -= 1;
                         if(pagina < 0) pagina = 0;
                         vista.show(pagina);
                         break;
                case 2:
                        pagina += 1;
                        if(pagina >= vista.getNumPaginas()) pagina = vista.getNumPaginas() - 1;
                        vista.show(pagina);
                        break;
                case 3: int elem = vista.getNumPaginas();
                        setVista(new ListagemSimples("\n\nQual a página?"));
                        vista.show();
                        paginaOP = Input.lerInt();
                        if(paginaOP < 0 || paginaOP > elem){
                            setVista(new VistaErro());
                            vista.show();
                            Input.lerString();
                        }else{
                             pagina = paginaOP-1;
                        }
                        setVista(new ListagemLista(titulo, l));
                        vista.show(pagina);
                        break;
                case 0: setVista(new ListagemSimples("\n\n\nPressione 'Enter' para retroceder."));
                        vista.show();
                        Input.lerString();
                        setVista(new MenuConsultasEstatisticas());
                        break;
                default: setVista(new VistaErro());
                         vista.show();
                         Input.lerString();
                         setVista(new ListagemLista(titulo, l));
                    break;
            }
        }while(opcao != 0);
        l.clear();
    }
}
