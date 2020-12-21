package MVC;

public interface IGestaoVendasControlador {

    public void setModelos(IGestaoVendasModelos modelos);

    public void setVista(IGestaoVendasVista vista);

    public void executa();
}
