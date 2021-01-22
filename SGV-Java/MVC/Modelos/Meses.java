package MVC.Modelos;


/**
 * Enumeração que implementa todos os meses
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public enum Meses
{
    Janeiro, Fevereiro, Março, Abril, Maio, Junho, Julho, Agosto, Setembro, Outubro, Novembro, Dezembro;
    
    //métodos de instância
    
    //Gets
    
    /**
     * Devolve um mês
     * @return Mês
     */
    public static Meses getMes(int valor){
        return values()[valor];
    }
}
