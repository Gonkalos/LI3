#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include <gmodule.h>
#include "CatalogoProdutos.h"
#include "CatalogoClientes.h"
#include "FaturacaoGlobal.h"
#include "GestaoFilial.h"
#include "Leitura.h"
#include "Queries.h"
#include "Navegador.h"

/*Falta colocar o FilialGlobais*/
void q1(CatalogoClientes* catClientes,CatalogoProdutos* catProdutos,FaturacaoGlobal* faturacao,GestaoClientes *gc){
    int numValidos = 0;
    int numLidos = 0;
    numLidos = lerClientes(catClientes,gc,&numValidos);
    printf("O ficheiro lido foi o Clientes.txt\nForam lidos %d clientes, dos quais %d sao validos.\n",numLidos,numValidos);
    numLidos = lerProdutos(catProdutos,faturacao,&numValidos);
    printf("\n\nO ficheiro lido foi o Produtos.txt\nForam lidos %d produtos, dos quais %d sao validos.\n",numLidos,numValidos);
    numLidos = lerVendas(*catProdutos,*catClientes,faturacao,gc,&numValidos);
    printf("\n\nO ficheiro lido foi o Vendas.txt\nForam lidos %d produtos, dos quais %d sao validos.\n",numLidos,numValidos);
}


void q2 (CatalogoProdutos catProdutos, char c){
    int nTotal = 0;
    char* produtos [MAXPRODUTOS];
    querie2 (catProdutos, c, &nTotal, produtos);
    navegador (produtos, 0, 'i', nTotal);
    printf ("Número de produtos: %d\n", nTotal);
}


void q3 (FaturacaoGlobal faturacao, int mes, char* produto, char tipo){
    if (tipo == 'G') querie3G (faturacao, mes, produto);
    if (tipo == 'F') querie3F (faturacao, mes, produto);
}


void q6 (FaturacaoGlobal faturacao){
    int naoComprados = 0;
    querie6 (faturacao, &naoComprados);
    printf ("Não foram comprados %d produtos.\n", naoComprados);
}


void q8 (FaturacaoGlobal faturacao, int mes1, int mes2){
    int numVendas = 0;
    double totFaturado = 0.0;
    querie8F (faturacao, mes1, mes2, &totFaturado, &numVendas);
    printf ("Entre os meses %d e %d foi faturado %f.\n", mes1, mes2, totFaturado);
    printf ("Unidades Vendidas: %d\n", numVendas);
}









