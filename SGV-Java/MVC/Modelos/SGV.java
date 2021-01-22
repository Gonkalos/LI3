package MVC.Modelos;

import java.util.InputMismatchException;
import java.lang.NumberFormatException;
import MVC.Modelos.Leitura;
import MVC.Modelos.Catalogos.*;
import MVC.Modelos.ModelosBase.*;
import MVC.IGestaoVendasModelos;
import MVC.Modelos.Leitura;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.Collections;
import java.util.Iterator;
import java.text.DecimalFormat;
import java.io.FileNotFoundException;
import MVC.Modelos.Meses;
import MVC.Exceptions.*;

public class SGV implements IGestaoVendasModelos{
    
    // Variáveis de Instância
    /** Catálogo de Clientes */
    private CatalogoClientes catClientes;
     /** Catálogo de Produtos */
    private CatalogoProdutos catProdutos;
     /** Faturação */
    private Faturacao faturacao;
    /** Gestão de filial */
    private GestaoFilial gestaoFilial;
    
    /** Constante que representa o número de meses */
    private static int numMeses = Constantes.numMeses;
    /** Constante que representa o número de filias */
    private static int numFiliais = Constantes.numFiliais;
    
    // Construtores
    
    /**
     * Construtores da classe SGV
     * Declaração dos construtores por omissao (vazio), parametrizado e de cópia
     */
    
    /**
     * Construtor por omissão de SGV
     */
    public SGV(){
        this.catClientes = new CatalogoClientes();
        this.catProdutos = new CatalogoProdutos();
        this.faturacao = new Faturacao();
        this.gestaoFilial = new GestaoFilial();
    }
    
    // -------------------------------------------------------------- Leitura -------------------------------------------------------------- \\
    
    /**
     * Povoa os cátalogos do sistema.
     * @param titulo
     */
    public void lerFicheiros(String titulo) throws FileNotFoundException{
        Leitura leitura;
        
        leitura = new Leitura(Constantes.ficheiroClientes);
        Collection<String> clientes = leitura.readFilesWithIO();
        this.catClientes.validaClientes(clientes);
        
        leitura = new Leitura(Constantes.ficheiroProdutos);
        Collection<String> produtos = leitura.readFilesWithIO();
        this.catProdutos.validaProdutos(produtos);
        
        leitura = new Leitura("./" + titulo + ".txt");
        Collection<String> vendasLidas = leitura.readFilesWithIO();
        
        for (String p : produtos){
            try{
                faturacao.insereProduto(p);
            }catch(ProdutoJaExisteException e){
                System.out.println(e.getMessage());
            }
        }
        
        
        clientes.clear();
        produtos.clear();
        
        Collection<Venda> vendas = new ArrayList<>();
        Venda venda = null;
        if(vendasLidas != null){
            for(String v : vendasLidas){
                venda = Venda.validaVenda(v);
                if(venda != null){
                    vendas.add(venda);
                }
            }
        }
        vendasLidas.clear();
        this.faturacao.adicionaVendas(vendas);
        this.gestaoFilial.adicionaVendas(vendas);
        vendas.clear();
    }
        
    // --------------------------------------------------------- Queries Interativas -------------------------------------------------------- \\
    
    Comparator<String> comparaProdutos = (a,b) -> a.compareTo(b);
    /**
     * Lista ordenada alfabeticamente com os códigos dos produtos nunca comprados e o seu respectivo total
     * @return List<String>
     */
    public List<String> q1 (){
        List<String> aux = new ArrayList<>();
        boolean repetido = false;
        Fatura fatura = null;
        for(String codProduto : faturacao.getProdutos()){
            repetido = false;
            for(int i = 0 ; i < numMeses && !repetido ; i++){
                for(int j = 0; j < numFiliais && !repetido ; j++){
                    fatura = this.faturacao.getFatura(codProduto,i,j);
                    if(!fatura.faturaVazia() && fatura != null){
                        repetido = true;
                    }
                }
            }
            if(fatura != null && !aux.contains(codProduto) && !repetido){
                aux.add(codProduto);
            }
        }
        aux.sort(comparaProdutos);
        return aux;
    }
    
    
    /**
     * Dado um mês válido, determinar o número total global de vendas realizadas e 
     * o número total de clientes distintos que as fizeram, filial a filial
     * @param mes
     * @return String
     */
    public String q2F (int mes){
        int numVendasFilial1 = 0, numVendasFilial2 = 0, numVendasFilial3 = 0;
        int numClienteFilial1 = 0, numClienteFilial2 = 0, numClienteFilial3 = 0;
        boolean clienteComprou, clienteComprouFilial1, clienteComprouFilial2, clienteComprouFilial3;
        for(String codCliente : this.gestaoFilial.getClientes()){
            clienteComprou = false; clienteComprouFilial1 = false; clienteComprouFilial2 = false; clienteComprouFilial3 = false;
            for (String codProduto : this.gestaoFilial.getProdutos(codCliente)){
                for(int j = 0; j<numFiliais ; j++){
                    Fatura fatura = this.gestaoFilial.getFatura(codCliente, codProduto, mes, j);
                    if(fatura != null){
                        if (j == 0) {numVendasFilial1 += fatura.getQuantidade(); if (fatura.getQuantidade()>0) clienteComprouFilial1 = true;}
                        if (j == 1) {numVendasFilial2 += fatura.getQuantidade(); if (fatura.getQuantidade()>0) clienteComprouFilial2 = true;}
                        if (j == 2) {numVendasFilial3 += fatura.getQuantidade(); if (fatura.getQuantidade()>0) clienteComprouFilial3 = true;}
                        if(fatura.getQuantidade()>0) clienteComprou = true;
                    }
                }
            }
            if (clienteComprouFilial1 == true) numClienteFilial1 += 1;
            if (clienteComprouFilial2 == true) numClienteFilial2 += 1;
            if (clienteComprouFilial3 == true) numClienteFilial3 += 1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Meses.getMes(mes) + ":\n\n");
        sb.append("\tFilial 1: " + numVendasFilial1 + " vendas; \n\t\t  " + numClienteFilial1 + " clientes." +"\n\n");
        sb.append("\tFilial 2: " + numVendasFilial2 + " vendas; \n\t\t  " + numClienteFilial2 + " clientes." +"\n\n");
        sb.append("\tFilial 3: " + numVendasFilial3 + " vendas; \n\t\t  " + numClienteFilial3 + " clientes." +"\n\n");
        return sb.toString();
    }
    
    /**
     * Dado um mês válido, determinar o número total global de vendas realizadas e 
     * o número total de clientes distintos que as fizeram
     * @param mes
     * @return String
     */
    public String q2G(int mes){
        boolean clienteComprou;
        int numTotalVendas = 0, numTotalClientes = 0;
        for(String codCliente : this.gestaoFilial.getClientes()){
            clienteComprou = false;
            for (String codProduto : this.gestaoFilial.getProdutos(codCliente)){
                for(int j = 0; j<numFiliais ; j++){
                    Fatura fatura = this.gestaoFilial.getFatura(codCliente, codProduto, mes, j);
                    if(fatura != null){
                        numTotalVendas += fatura.getQuantidade();
                        if(fatura.getQuantidade() > 0){
                            clienteComprou = true;
                        }
                    }
                }
            }
            if (clienteComprou == true) numTotalClientes += 1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Meses.getMes(mes) + ":\n");
        sb.append("\tNúmero total de vendas: "+ numTotalVendas + ";\n");
        sb.append("\tNúmero total de clientes: " + numTotalClientes + ".\n");
        return sb.toString();
    }
    
    /**
     * Lista de quantas compras um cliente fez, quantos produtos distintos comprou e quanto gastou no total.
     * @param codCliente
     * @return List<String>
     */         
    public List<String> q3 (String codCliente) throws ClienteNaoExisteException{
        List <String> result = new ArrayList<>(numMeses);
        int numVendas = 0, numProdutos = 0;
        double faturado = 0.0;
        DecimalFormat formatter = new DecimalFormat("#0.00"); 
        if (!this.gestaoFilial.clienteExiste(codCliente)){
            throw new ClienteNaoExisteException(codCliente);
        }
        for (int i = 0; i < numMeses; i++){
            StringBuilder sb = new StringBuilder();
            sb.append(Meses.getMes(i) + ":\n");
            numVendas = 0;
            faturado = 0.0;
            numProdutos = 0;
            for (String codProduto : this.gestaoFilial.getProdutos(codCliente)){
                for (int j = 0; j < numFiliais; j++){
                    Fatura fatura = this.gestaoFilial.getFatura(codCliente, codProduto, i, j);
                    if(fatura != null){
                        numVendas += fatura.getQuantidade();
                        faturado += fatura.getFaturado();
                    }
                }
                if (numVendas > 0) numProdutos++;
            }
            sb.append("Número de compras: " + numVendas + "\n");
            sb.append("Total gasto: " + formatter.format(faturado) + "\n");
            sb.append("Número de produtos comprados: " + numProdutos + "\n");
            result.add(i, sb.toString());
        }
        return result;
    }
       
    
    /**
     * Lista de quantas vezes um produto foi comprado, por quantos clientes diferentes e 
     * o total facturado.
     * @param codProduto
     * @return List<String>
     */
    public List<String> q4 (String codProduto) throws ProdutoNaoExisteException{
        int numClientes = 0;
        int numVendas = 0;
        double totalFaturado = 0;
        int mes = 0; 
        DecimalFormat formatter = new DecimalFormat("#0.00"); 
        if(!this.gestaoFilial.produtoExiste(codProduto)){
            throw new ProdutoNaoExisteException(codProduto);
        }
        List<String> resultado = new ArrayList<>();
        resultado.add("--------------------- Produto: " + codProduto + " ---------------------\n");
        for(int i = 0 ; i < numMeses ; i++){
            StringBuilder sb = new StringBuilder();
            numVendas = 0;
            totalFaturado = 0;
            sb.append("\t" + Meses.getMes(i) + " : \n");
            numClientes = 0;
            for(String codCliente : this.gestaoFilial.getClientes()){
                if(this.gestaoFilial.produtoExiste(codCliente, codProduto)){
                    for(int j = 0; j < numFiliais ; j++){
                        Fatura fatura = this.gestaoFilial.getFatura(codCliente, codProduto, i, j);
                        if(fatura != null){
                            numVendas += fatura.getQuantidade();
                            totalFaturado += fatura.getFaturado();
                            if(!fatura.faturaVazia()){
                                numClientes++;
                            }
                        }
                    }
                }
            }
            sb.append("\t   Número de Vendas: " + numVendas + ";\n");
            sb.append("\t   Total Faturado: " + formatter.format(totalFaturado) + ";\n");
            sb.append("\t   Número de clientes diferentes " + numClientes + ";\n\n");
            resultado.add(sb.toString());
        }
        return resultado;
    }
       
    /**
     * Lista de códigos de produtos e quantos que um cliente mais comprou.
     * @param codCliente
     * @return List<String>
     */
    public List<String> q5 (String codCliente) throws ClienteNaoExisteException{
        if(!this.gestaoFilial.clienteExiste(codCliente)){
            throw new ClienteNaoExisteException(codCliente);
        }
        List<String> resultado = new ArrayList<>();
        Map <String,Integer> resultadoMap = new TreeMap();
        int n = 0;
        for(String codProduto : this.gestaoFilial.getProdutos(codCliente)){
            for(int i = 0; i < numMeses ; i++){
                for(int j = 0; j < numFiliais ; j++){
                    Fatura fatura = this.gestaoFilial.getFatura(codCliente, codProduto, i, j);
                    if(fatura != null){
                        if(resultadoMap.containsKey(codProduto)){
                            n = resultadoMap.get(codProduto);
                        }else{
                            n = 0;
                        }
                        resultadoMap.put(codProduto,n);
                    }
                }
            }
        }
        
        resultadoMap = resultadoMap.entrySet().stream()
                                   .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new));
        resultadoMap.forEach((k,v) -> resultado.add(k));
        resultadoMap.clear();
        return resultado;
    }
    
    /**
     * Lista dos n produtos mais vendidos em todo o ano (em número de unidades vendidas) indicando o número total de 
     * distintos clientes que o compraram
     * @param x
     * @return List<String>
     */
    public List<String> q6 (int x) throws NumeroInvalidoException{
        if(x < 0){
            throw new NumeroInvalidoException("x é menor 0");
        }
        int quantidade = 0;
        int numClientes = 0;
        List<String> resultado = new ArrayList<>();
        Map<String,Integer> maisVendidos = new TreeMap<>();
        Map<String,Integer> clientesDiferentes = new HashMap<>();
        for(String codCliente : this.gestaoFilial.getClientes()){
            for(String codProduto : this.gestaoFilial.getProdutos(codCliente)){
                for(int i = 0; i < numMeses ; i++){
                    for(int j = 0 ; j < numFiliais ; j++){
                        Fatura fatura = this.gestaoFilial.getFatura(codCliente, codProduto, i, j);
                        if(fatura != null){
                            if(maisVendidos.containsKey(codProduto)){
                                quantidade = maisVendidos.get(codProduto);
                            }else{
                                quantidade = 0;
                            }
                        
                            quantidade += fatura.getQuantidade();
                            maisVendidos.put(codProduto,quantidade);
                        }
                    }
                }
                if(clientesDiferentes.containsKey(codProduto)){
                    numClientes = clientesDiferentes.get(codProduto);
                }else{
                    numClientes = 0;
                }
                numClientes++;
                clientesDiferentes.put(codProduto,numClientes);
            }
        }
        
        maisVendidos = maisVendidos.entrySet().stream()
                                   .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                   .limit(x)
                                   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new));
                                   
        maisVendidos.forEach((k,v) -> resultado.add("O produto com o código " + k + " foi comprado por " + clientesDiferentes.get(k) + " clientes diferentes.\n"));                                                    
        maisVendidos.clear();
        clientesDiferentes.clear();
        return resultado;
    }
    
    /**
     * Três maiores compradores em termos de dinheiro facturado, para cada filial.
     * @return String
     */
    public String q7 (){
        StringBuilder sb = new StringBuilder();
        Map<String,Integer> clientes;
        int faturado;
        for(int i = 0 ; i < numFiliais; i++){
            clientes = new TreeMap<>();
            faturado = 0;
            for(String codCliente : this.gestaoFilial.getClientes()){
                for (String codProduto : this.gestaoFilial.getProdutos(codCliente)){
                    for(int j = 0 ; j < numMeses ; j++){
                        Fatura fatura = this.gestaoFilial.getFatura(codCliente, codProduto, j, i);
                        if(fatura != null){
                            if(clientes.containsKey(codCliente)){
                                faturado = clientes.get(codCliente);
                            }else{
                                faturado = 0;
                            }
                            faturado += fatura.getFaturado();
                        }
                    }
                }
                clientes.put(codCliente,faturado);
            }
            
            clientes = clientes.entrySet().stream()
                                   .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                   .limit(3)
                                   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new));
            sb.append("Filial " + (i+1) + ":\n");
            for(String s : clientes.keySet()){
                sb.append("\t" + s + ";\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
   
    /**
     * Lista dos códigos dos n clientes que compraram mais produtos diferentes,indicando quantos
     * (Ordenados por número de produtos)
     * @param x
     * @return List<Strig>
     */
    public List<String> q8 (int x) throws NumeroInvalidoException{
        if(x < 0){
            throw new NumeroInvalidoException("x é menor 0");
        }
        int totalComprado, i, j;
        boolean comprou = false;
        List<String> resultado = new ArrayList<String>();
        Map<String,Integer> clientesFaturados = new HashMap<String,Integer>();
        for(String codCliente : this.gestaoFilial.getClientes()){
            totalComprado = 0;
            for (String codProduto : this.gestaoFilial.getProdutos(codCliente)){
                for(i = 0; i<12 ; i++){
                    for(j = 0; j<numFiliais ; j++){
                        Fatura fatura = this.gestaoFilial.getFatura(codCliente, codProduto, i, j);
                        if((fatura.getQuantidadeP ()>0 || fatura.getQuantidadeN()>0) && fatura != null)comprou = true;
                    }
                }
                if(comprou == true) totalComprado += 1;
                comprou = false;
            }
            clientesFaturados.put(codCliente,totalComprado);
        }
        for(i=0;i<x;i++){
            int max = Collections.max(clientesFaturados.values());
            Iterator it = clientesFaturados.entrySet().iterator();
            j = 0;
            while (it.hasNext() && j!=1) {
                Map.Entry cf = (Map.Entry)it.next();
                if((Integer)cf.getValue() == max) {
                    resultado.add(""+cf.getKey()); 
                    clientesFaturados.remove(cf.getKey()); 
                    j = 1;
                }
            }
        }
        clientesFaturados.clear();
        return resultado;
    }

    
    /**
     * Lista dos n clientes que mais o compraram um produto e, para cada um, qual o valor gasto.
     * @param codProduto, n
     * @return List<String>
     */
    public List<String> q9 (String codProduto, int n) throws NumeroInvalidoException, ProdutoNaoExisteException{
        if(n < 0){
            throw new NumeroInvalidoException("x é menor 0");
        }
        if(!this.gestaoFilial.produtoExiste(codProduto)){
            throw new ProdutoNaoExisteException(codProduto);
        }
        DecimalFormat formatter = new DecimalFormat("#0.00"); 
        List <String> result = new ArrayList<>(n);
        Map <String, Integer> numVendasAux = new HashMap<>();
        Map <String, Double> faturadoAux = new HashMap<>();
        int numVendas = 0;
        double faturado = 0.0;
        for (String codCliente : this.gestaoFilial.getClientes()){
            if (this.gestaoFilial.produtoExiste(codCliente, codProduto)){
                for (int i = 0; i < numMeses; i++){
                    for (int j = 0; j < numFiliais; j++){
                        Fatura fatura = this.gestaoFilial.getFatura(codCliente, codProduto, i, j);
                        if(fatura != null){
                            numVendas += fatura.getQuantidade();
                            faturado += fatura.getFaturado();
                        }
                    }
                }
            }
            numVendasAux.put(codCliente, numVendas);
            faturadoAux.put(codCliente, faturado);
            numVendas = 0;
            faturado = 0;
        }
        for (int i = 0; i < n; i++){
            StringBuilder sb = new StringBuilder();
            int max = Collections.max(numVendasAux.values());
            for (String codCliente : numVendasAux.keySet()){
                if (numVendasAux.get(codCliente) == max && numVendasAux.get(codCliente) != 0){
                    sb.append("Cliente: " + codCliente + " ->  gastou " + formatter.format(faturadoAux.get(codCliente)) + "€\n");
                    result.add(i, sb.toString());
                    numVendasAux.remove(codCliente);
                    break;
                }
            }
        }
        numVendasAux.clear();
        faturadoAux.clear();
        return result;
    }
    
    /**
     * Lista de mês a mês, e para cada mês filial a filial, a facturação total com cada produto.
     * @return List<String>
     */
     public List<String> q10(){
        List<String> aux = new ArrayList<>();
        DecimalFormat formatter = new DecimalFormat("#0.00"); 
        for(String codProduto : this.faturacao.getProdutos()){
            StringBuilder sb = new StringBuilder();
            sb.append("--------------------- Produto: " + codProduto + " ---------------------\n");
            for(int i = 0 ; i < numMeses ; i++){
                sb.append("\t" + Meses.getMes(i) + " : \n");
                sb.append("\t  Filial:\n");
                for(int j = 0; j < numFiliais; j++){
                    Fatura fatura = this.faturacao.getFatura(codProduto, i, j);
                    if(fatura != null){
                        double total = fatura.getFaturado();
                        sb.append("\t   " + (j+1) + " - " + formatter.format(total) + "€;");
                    }
                }
                sb.append("\n\n");
            }
            aux.add(sb.toString());
        }
        return aux;
    }
    
    // --------------------------------------------------------- Queries Estatísticas ------------------------------------------------------- \\
    
    /**
     * Número total de compras por mês
     * @return String
     */
    public String numTotalVendasMes(){
        StringBuilder sb = new StringBuilder();
        int total = 0;
        for(int i = 0;  i < numMeses ; i++){
            total = 0;
            for(String codCliente : this.gestaoFilial.getClientes()){
                for(String codProduto : this.gestaoFilial.getProdutos(codCliente)){
                    for(int j = 0; j < numFiliais ; j++){
                        Fatura fatura = this.gestaoFilial.getFatura(codCliente, codProduto, i, j);
                        if (fatura!=null){
                            total += fatura.getQuantidade();
                        }
                    }
                }
            }
            sb.append("Numero de vendas em " + Meses.getMes(i) + " foram " + total + ".\n");
        }
        return sb.toString();
    }
    
        
    
    /**
     * Facturação total por mês para cada filial e o valor total global.
     * @return String
     */
    public String vendasGlobalFilial(){
        StringBuilder sb = new StringBuilder();
        int numVendasMes, numVendasFilial, numVendasGlobal = 0;
        for (int i = 0; i < numMeses; i++){
            sb.append(Meses.getMes(i) + ":\n");
            numVendasMes = 0;
            for (int j = 0; j < numFiliais; j++){
                numVendasFilial = 0;
                for (String codProduto : this.faturacao.getProdutos()){
                    Fatura fatura = this.faturacao.getFatura(codProduto, i, j);
                    if(fatura != null){
                        numVendasFilial += fatura.getQuantidade();
                    }
                }
                numVendasMes += numVendasFilial;
                sb.append("Filial " + (j+1) + ": " + numVendasFilial + " vendas.\n"); 
            }
            numVendasGlobal += numVendasMes;
            sb.append("" + numVendasMes + " vendas no mês.\n\n\n");
        }
        sb.append("----------- Vendas Globais -----------\n           " + numVendasGlobal + " vendas\n--------------------------------------\n");
        return sb.toString();
    }

    
    /** 
     * Lista de números de distintos clientes que compraram em cada mês filial a filial
     * @return List<String>
     */
    public List<String> numClientesMes(){
        List<String> aux = new ArrayList<>();
        int numClientesMes, numClientesFilial, teste;
        for (int i = 0; i < numMeses; i++){
            StringBuilder sb = new StringBuilder();
            sb.append(Meses.getMes(i) + ":\n");
            numClientesMes = 0;
            for (int j = 0; j < numFiliais; j++){
                numClientesFilial = 0;
                for (String codCliente : this.gestaoFilial.getClientes()){
                    teste = 0;
                    for (String codProduto : this.gestaoFilial.getProdutos(codCliente)){
                        if (teste != 0) break;
                        Fatura fatura = this.gestaoFilial.getFatura(codCliente, codProduto, i, j);
                        if (fatura.getQuantidade() > 0){
                            numClientesFilial++;
                            teste++;
                        }
                    }
                }
                sb.append("\tFilial " + (j+1) + ": " + numClientesFilial + " clientes distintos.\n"); 
            }
            aux.add(sb.toString());
        }
        return aux;
    }
}
