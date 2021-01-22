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

public class Leitura {

    // Variáveis de instancia

    private String fileName;
    
    // Construtores

    public Leitura(){
        this.fileName = "";
    }

    public Leitura (String fileName){
        this.fileName = "Files/" + fileName;
    }

    public Leitura (Leitura leitura){
        this.fileName = leitura.getFileName();
    }

    // Gets

    public String getFileName(){
        return this.fileName;
    }
    
    // Equals

    public boolean equals (Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Leitura l = (Leitura)o;
        return this.fileName.equals(l.getFileName());
    }
    
    // Clone

    public Leitura clone(){
        return new Leitura(this);
    }

    // Lê o ficheiro texto e devolve toda a informação com base

    public Collection<String> readFilesWithIO() throws FileNotFoundException{
        Path p;
        if (!this.fileName.equals("Vendas_3M.txt")){
            File file = new File(Constantes.ficheiroVendas);
            if (!file.exists()) throw new FileNotFoundException(Constantes.ficheiroVendas);
            p = Paths.get(Constantes.ficheiroVendas);
        }
        else {
            File file = new File(this.fileName);
            if (!file.exists()) throw new FileNotFoundException(this.fileName);
            p = Paths.get(this.fileName);
        }
        List<String> l = null;
        try{
            l = Files.readAllLines(p,StandardCharsets.UTF_8);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return l;
    }
}