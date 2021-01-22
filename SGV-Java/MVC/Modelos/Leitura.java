package MVC.Modelos;

import java.util.Collection;
import java.util.List;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Classe que implementa uma Leitura.
 * String fileName representa o nome do Ficheiro.
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public class Leitura{
    //Variáveis de instancia
    
    /**  Nome do ficheiro */
    private String fileName;
    
    //Construtores
    
    /**
     * Construtores da classe Leitura
     * Declaração dos construtores por omissao (vazio), parametrizado e de cópia
     */
    /**
     * Construtor por omissão de Leitura
     */
    public Leitura(){
        this.fileName = "";
    }
    
    /**
     * Construtor parametrizado de Leitura
     * Aceita como parâmetro um nome de um ficheiro
     * @param fileName
     */
    public Leitura (String fileName){
        this.fileName = fileName;
    }
    
    /**
     * Construtor de cópia de Leitura
     * Aceita como parâmetro outra Leitura e utiliza os métodos
     * de acesso ao valor das variáveis de instância
     * @param Leitura original
     */
    public Leitura (Leitura leitura){
        this.fileName = leitura.getFileName();
    }
    
    //métodos de instância
    
    // Gets
    
    /**
      * Devolve o nome do ficheiro
      * @return fileName
      */
    public String getFileName(){
        return this.fileName;
    }
    
    // Equals
    
    /**
     * Verifica a igualdade de dois objectos
     * @param objecto
     * @return boolean
     */
    public boolean equals (Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Leitura l = (Leitura)o;
        return this.fileName.equals(l.getFileName());
    }
    
    // Clone
    
    /**
     * Cria uma cópia de Leitura
     * @return Leitura
     */
    public Leitura clone(){
        return new Leitura(this);
    }
    
    
    /**
     * Lê o ficheiro texto e devolve toda a informação com base
     * numa coleção de String's
     * @return Collection<String>
     */
    public Collection<String> readFilesWithIO() throws FileNotFoundException{
        Path p;
        if ((!this.fileName.equals("./Vendas_3M.txt")) && (!this.fileName.equals("./Vendas_5M.txt")) && 
            (!this.fileName.equals("./Clientes.txt")) && (!this.fileName.equals("./Produtos.txt"))){
            File file = new File(Constantes.ficheiroVendas);
            if (!file.exists()) throw new FileNotFoundException(Constantes.ficheiroVendas);
            p = Paths.get(Constantes.ficheiroVendas);
        }
        else{
            File file = new File(this.fileName);
            if (!file.exists()) throw new FileNotFoundException(this.fileName);
            p = Paths.get(this.fileName);
        }
        List<String> l = null;
        try{
            l = Files.readAllLines(p,StandardCharsets.UTF_8);
        }catch(IOException e){
            e.printStackTrace();
        }
        return l;
    }
}