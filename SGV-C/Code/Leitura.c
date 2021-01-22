#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <gmodule.h> 
#include "CatalogoProdutos.h"
#include "CatalogoClientes.h"
#include "FaturacaoGlobal.h"
#include "GestaoFilial.h"
#include "Leitura.h"


struct venda {
    char* codProd;
    double precoUnit;
    int quantidade;
    char tipo;
    char* codCli;
    int mes;
    int filial;
};


/*Validar um cliente tendo em conta que cada codigo de produto formado por:
 - 1 letra maiuscula;
 - 4 digitos, compreendidos entre 1000 e 5000;
 Retorna:
 - 1 se o cliente for valido;
 - 0 em caso contrario;
 */
int validaCliente(char* cliente){
    int numero = 0;
    int r = 0;
    if(*cliente >= 'A' && *cliente <= 'Z'){
        numero = atoi(cliente+1);
        if(numero >= 1000 && numero <= 5000){
            r = 1;
        }
    }
    return r;
}


/*Validar um produto tendo em conta que cada codigo de produto formado por:
 - 2 letras maiusculas;
 - 4 digitos, compreendidos entre 1000 e 9999;
 Retorna:
 - 1 se o produto for valido;
 - 0 em caso contrario;
 */
int validaProduto(char* produto){
    int numero = 0;
    int r = 0;
    if((*produto >='A' && *produto <='Z') && (*(produto+1) >= 'A' && *(produto+1) <= 'Z')){
        numero = atoi(produto+2);
        if(numero>=1000 && numero <= 9999){
            r = 1;
        }
    }
    return r;
}


/*Ler ficheiro de clientes */
int lerClientes(CatalogoClientes* catClientes, GestaoClientes* gc, int* numValidos){
    (*numValidos) = 0;
    FILE *fp = fopen("../Files/Clientes.txt","r");
    int i = 0;
    if(!fp){
        perror("Ocorreu um erro ao abrir o ficheiro de Clientes!\n");
        i = -1;
    }else{
        char buff[MAXBUFFCLIENTE];
        char* cliente;
        
        while(fgets(buff,MAXBUFFCLIENTE,fp)){
            cliente = strtok(buff, "\n\r");
            if(validaCliente(cliente) == 1){
                *catClientes = insereCliente(*catClientes,cliente);
                *gc = newGC (*gc, cliente);
                (*numValidos)++;
            }
            i++;
        }
        fclose(fp);
    }
    return i;
}


/*Ler ficheiro de produtos */
int lerProdutos(CatalogoProdutos* catProdutos,FaturacaoGlobal* faturacao,int* numValidos){
    (*numValidos) = 0;
    FILE *fp = fopen("../Files/Produtos.txt","r");
    int i = 0;
    if(!fp){
        perror("Ocorreu um erro ao abrir o ficheiro de Produtos!\n");
        i = -1;
    }else{
        char buff[MAXBUFFPRODUTO];
        char* produto;
        while(fgets(buff,MAXBUFFPRODUTO,fp)){
            produto = strtok(buff, "\n\r");
            if(validaProduto(produto) == 1){
                *catProdutos = insereProduto(*catProdutos,produto);
                *faturacao = inicializaFaturacao(*faturacao,produto);
                (*numValidos)++;
            }
            i++;
        }
        fclose(fp);
    }
    return i;
}


/*Valida uma venda tendo em conta que uma Venda e formada por:
 - Um produto;
 - O preco unitario, compreendido entre 0.0 e 999.99;
 - O numero inteiro da quantidade comprada entre 1 e 200;
 - O tipo da compra, N ou P;
 - Um cliente;
 - O mes da compra;
 - A filial, entre 1 e 3;
 Retorna:
 - 1 se a venda for valida;
 - 0 em caso contrario;
 */
int validaVenda(Venda v,CatalogoProdutos catProdutos,CatalogoClientes catClientes){
    int r = 0;
    if( ((existeProduto(catProdutos,v->codProd) == 1)) &&
       ((v->precoUnit >= 0) && (v->precoUnit <= 999.99)) &&
       ((v->quantidade >= 1)  && (v->quantidade <= 200)) &&
       ((v->tipo == 'N') || (v->tipo == 'P')) &&
       ((existeCliente(catClientes,v->codCli) == 1)) &&
       ((v->mes >= 1) && (v->mes <=12)) &&
       ((v->filial >= 1) && (v->filial <= 3)) ){
        r = 1;
    }
    return r;
}


void libertaMemoriaVenda(Venda v){
    if(v){
        /*if(v->codProd){
            g_free(v->codProd);
        }
        if(v->codCli){
            g_free(v->codCli);
        }*/
        g_free(v);
    }
}

/*Cria uma Venda;
    Retorna:
        - uma Venda valida;
        - NULL em caso contrario;
 */ 
Venda criaVenda(char* venda,CatalogoProdutos catProdutos,CatalogoClientes catClientes){
    //Venda v = (Venda)malloc(sizeof(struct venda));
    Venda v = (Venda)g_new(struct venda,1);
    //v->codProd = (char*)malloc(PRODUTO+1,sizeof(char));
    v->codProd = (char*)g_new(char*,PRODUTO+1);
    //v->codCli = (char*)malloc(CLIENTE+1,sizeof(char));
    v->codCli = (char*)g_new(char*,CLIENTE+1);
    sscanf(venda,"%s %lf %d %c %s %d %d",v->codProd,&(v->precoUnit),&(v->quantidade),&(v->tipo),v->codCli,&(v->mes),&(v->filial));
    
    if(validaVenda(v,catProdutos,catClientes) == 0){
        libertaMemoriaVenda(v);
        v = NULL;
    }
    return v;
}


void imprimeVenda(Venda v){
    if(v){
        printf("Struct Venda:\n");
        printf("\tProduto: %s\n",v->codProd);
        printf("\tPreço Unidade: %.2lf \n",v->precoUnit);
        printf("\tQuantidade: %d\n",v->quantidade);
        printf("\tTipo: %c\n",v->tipo);
        printf("\tCliente: %s\n",v->codCli);
        printf("\tMes: %d\n",v->mes);
        printf("\tFilial: %d\n",v->filial);
    }
}


int lerVendas(CatalogoProdutos catProdutos,CatalogoClientes catClientes,FaturacaoGlobal* faturacao,GestaoClientes* gc,int* numValidos){
    (*numValidos) = 0;
    FILE *fp = fopen("../Files/Vendas_1M.txt","r");
    int i = 0;

    if(!fp){
        perror("Ocoreu um erro ao abrir o ficheiro de Vendas!\n");
        i = -1;
    }
    else {
        char buff[MAXBUFFVENDA];
        char* venda = NULL;
        Venda v = NULL;
        while (fgets(buff, MAXBUFFVENDA, fp)){
            venda = strtok (buff, "\n\r");
            v = criaVenda(venda,catProdutos,catClientes);
            if(v){

                if(faturacao){
                    *faturacao = insereFaturacao(*faturacao,v->codProd,v->precoUnit,v->quantidade,v->tipo,v->mes,v->filial);
                }

                if(gc){
                    *gc = insertGC(*gc,v->codProd,v->quantidade,v->tipo,v->codCli,v->mes,v->filial);
                }
            
                libertaMemoriaVenda(v);
                v = NULL;
                (*numValidos)++;
            }
            i++;
        }
    }
    return i;
}