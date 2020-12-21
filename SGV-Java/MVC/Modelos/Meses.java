package MVC.Modelos;

public enum Meses {

    Janeiro, Fevereiro, Março, Abril, Maio, Junho, Julho, Agosto, Setembro, Outubro, Novembro, Dezembro;

    // Gets

    public static Meses getMes(int valor){
        return values()[valor];
    }
}
