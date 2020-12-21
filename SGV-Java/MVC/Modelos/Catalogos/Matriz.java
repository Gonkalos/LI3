package MVC.Modelos.Catalogos;

import MVC.Modelos.ModelosBase.*;
import MVC.Modelos.Constantes;
import MVC.Modelos.Meses;
import java.util.Arrays;

public class Matriz{
    
    // Variáveis de Instância
    
    private Fatura[][] matriz;
    
    private static int numMeses = Constantes.numMeses;
    private static int numFiliais = Constantes.numFiliais;
    
    // Construtores
    
    public Matriz(){
        this.matriz = new Fatura[numMeses][numFiliais];
        /*for (int i = 0; i < numMeses; i++){
            for (int j = 0; j < numFiliais; j++){
                matriz[i][j] = new Fatura();
            }
        }*/
    }
    
    public Matriz (Fatura[][] matriz){
        this.matriz = matriz.clone();
    }
    
    // Gets
    
    public Fatura getFatura (int mes, int filial){
        Fatura fatura = this.matriz[mes][filial];
        if (fatura == null){
            this.matriz[mes][filial] = new Fatura();
            return this.matriz[mes][filial];
        }
        else{
            return this.matriz[mes][filial];
        }
    }
    
    public Fatura[] getFiliais (int mes){
        return matriz[mes];
    }
    
    // Sets
    
    public void setFatura (Fatura fatura, int mes, int filial){
        this.matriz[mes][filial] = fatura.clone();
    }
    
    // Equals
    
    public boolean equals (Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Matriz m = (Matriz)o;
        return Arrays.deepEquals(matriz, m.matriz);
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numMeses; i++){
            sb.append(Meses.getMes(i) + ":\n");
            for (int j = 0; j < numFiliais; j++){
                Fatura fatura = this.matriz[i][j];
                sb.append("Filial " + (j+1) + ":\n");
                sb.append(fatura.toString());
            }
        }
        return sb.toString();
    }   
    
    // HashCode
    
    public int hashCode(){
        return Arrays.deepHashCode(matriz);
    }
    
    // Clear
    
    public void clear(){
        for (int i = 0; i < numMeses; i++){
            for (int j = 0; j < numFiliais; j++){
                this.matriz[i][j] = null;
            }
        }
    }
}
