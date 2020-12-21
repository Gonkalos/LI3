#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <gmodule.h>
#include "CatalogoProdutos.h"
#include "CatalogoClientes.h"
#include "FaturacaoGlobal.h"
#include "GestaoFilial.h"
#include "Queries.h"


void ajuda (int i){
	printf("Pediu ajuda para a opcao: %d\n", i);
	if (i == 1) printf("Ler os 3 ficheiros (Produtos, Clientes e Vendas), cujos nomes poderão ser introduzidos pelo utilizador ou, opcionalmente, assumidos por omissão (sendo neste caso os ficheiros anteriormente referidos). O resultado desta leitura deverá ser a apresentação imediata ao utilizador do nome do ficheiro lido e que vai ser usado, o número total de linhas lidas e validadas. Note-se que qualquer nova leitura destes ficheiros deverá de imediato reiniciar e refazer as estruturas de dados em memória como se de uma reinicialização so tratasse.\n");
	if (i == 2) printf("Determinar a lista e o nº total de produtos cujo código se inicia por uma dada letra (maiúscula); Apresentar tal lista ao utilizador e permitir que o mesmo navegue na mesma, página a página, sendo tal lista apresentada por ordem alfabética.\n");
	if (i == 3) printf("Dado um mês e um código de produto, ambos válidos, determinar e apresentar o número total de vendas (nº de registos de venda) e o total facturado com esse produto em tal mês, distinguindo os totais em modo N e os totais em modo P. O utilizador deverá decidir se pretende o resultado global ou os resultados filial a filial para todas as 3 filiais.\n ");
	if (i == 4) printf("Determinar a lista ordenada dos códigos dos produtos (e o seu número total) que ninguém comprou, podendo o utilizador decidir igualmente se pretende valores totais ou divididos pelas filiais. \n");
	if (i == 5) printf("Determinar a lista ordenada de códigos de clientes que realizaram compras em todas as filiais; \n");
	if (i == 6) printf("Determinar o número de clientes registados que não realizaram compras bem como o número de produtos que ninguém comprou. \n");
	if (i == 7) printf(" Dado um código de cliente, criar uma tabela com o número total de produtos comprados (ou seja a soma das quantidades de todas as vendas do produto), mês a mês (para meses em que não comprou a entrada deverá ficar a 0). A tabela deverá ser apresentada em ecrã organizada por filial. \n");
	if (i == 8) printf("Dado um intervalo fechado de meses, por exemplo [1..3], determinar o total de vendas (nº de registos de venda) registadas nesse intervalo e o total facturado; \n");
	if (i == 9) printf("Dado um código de produto e uma filial, determinar os códigos (e número total) dos clientes que o compraram, distinguindo entre compra N e compra P\n");
	if (i == 10) printf("Dado um código de cliente e um mês, determinar a lista de códigos de produtos que mais comprou por quantidade e não por facturação), por ordem descendente; \n");
	if (i == 11) printf("Criar uma lista dos N produtos mais vendidos em todo o ano, indicando o número total de clientes e o número de unidades vendidas, filial a filial; \n");
	if (i == 12) printf("Dado um código de cliente determinar quais os códigos dos 3 produtos em que mais gastou dinheiro durante o ano; \n");
    if (i == 13) printf("FAQS\n");
    if (i == 14) printf("Opção para sair do menu e encerrar o programa.");	
    getchar();
}



void libertaMemoria(CatalogoClientes catClientes, CatalogoProdutos catProdutos,FaturacaoGlobal faturacao){
    libertaMemoriaClientes(catClientes);
    libertaMemoriaProdutos(catProdutos);
    libertaMemoriaFaturacao(faturacao);
}


int main(){
    system("clear");
    CatalogoClientes catClientes = NULL;
    CatalogoProdutos catProdutos = NULL;
    FaturacaoGlobal faturacao = NULL;
    GestaoClientes gc = NULL;
    int opcao;
    int mes1 = 0,mes2 = 0;
    char letra;
    int r = 0;
    char* codigoProduto = (char*)g_new0(char*,PRODUTO+1);
    char* codigoCliente = (char*)g_new0(char*,PRODUTO+1);;
    do{
        printf("*======================================================*\n");
        printf("|               Sistema Gestao de Vendas               |\n");
        printf("*======================================================*\n");
        printf("\n\t\tMain Menu\n");
        printf("\n\nOPCOES:\n");
        printf("\t1  - Ler ficheiros.\n");
        printf("\t2  - Lista de produtos começados por letra.\n");
        printf("\t3  - Total de vendas e faturado de um produto num mes.\n");
        printf("\t4  - Lista ordenada dos produtos que ninguem comprou.\n");
        printf("\t5  - Lista ordenada dos clientes que realizaram compras em todas as filiais.\n");
        printf("\t6  - Numero de clientes que nao realizaram compras e de produtos que ningueem comprou.\n");
        printf("\t7  - Tabela dos produtos comprados mes a mes, por um dado cliente.\n");
        printf("\t8  - Numero total de vendas e faturado num intervalo de meses.\n");
        printf("\t9  - Clientes que compraram um certo produto numa certa filial.\n");
        printf("\t10 - Produtos mais comprados por um certo cliente num certo mes.\n");
        printf("\t11 - Lista dos N produtos mais vendidos, numero total de clientes e produtos por filial.\n");
        printf("\t12 - Produtos mais comprados por um certo Cliente\n");
        printf("\t13 - Ajuda.\n");
        printf("\t14 - Sair.\n");
        printf("\n\n\n\nOPCAO:");
        scanf("%d",&opcao);
        getchar();
        system("clear");
        switch(opcao){
            printf("*======================================================*\n");
            printf("|               Sistema Gestao de Vendas               |\n");
            printf("*======================================================*\n");
            case 1:
                printf("Leitura dos ficheiros.\n");
                q1(&catClientes,&catProdutos,&faturacao,&gc);
                printf("Pressione Enter para voltar ao menu!\n");
                getchar();
                system("clear");
                break;
            case 2:
                r = 0;
                do{
                    printf("Introduza uma letra maiuscula.\n");
			        letra = getchar();
                    getchar();
                    if(letra >= 'A' && letra <= 'Z'){
                        r = 1;
                    }else{
                        printf("OPCAO INVALIDA!\tTente novamente.\n");
                    }
                }while(r == 0);
                system("clear");
                printf("A letra introduzida foi %c\n",letra);
                q2(catProdutos,letra);
                printf("Pressione Enter para voltar ao menu!\n");
                getchar();
                system("clear");
                break;
            case 3:
                r = 0;
                do{
                    printf("Insira um mes\n");
			        scanf("%d",&mes1);
                    if(mes1 >= 1 && mes1 <= 12){
                        r = 1;
                    }else{
                        printf("Mes invalido!\tTente novamente.\n");
                    }
                }while(r == 0);
                r = 0;
                do{
                    printf("Insira um codigo de Produto.\n");
			        scanf("%s",codigoProduto);
                    if(validaProduto(codigoProduto)){
                        r = 1;
                    }else{
                        printf("Produto invalido!\tTente novamente.\n");
                    }
                }while(r == 0);
                r = 0;
			    do{
                    printf("Pretende os resultados em que formato?\n");
                    printf("\t*Globais presse 'G';\n");
                    printf("\t*Filial a filial presse 'F'.\n");
                    printf("OPCAO: ");
			        scanf("%s",&letra);
                    getchar();
                    if(letra == 'F' || letra == 'G'){
                        r = 1;
                    }else{
                        printf("Opcao Invalida!\tTente novamente.\n");
                    }
                }while(r == 0);
                system("clear");
                printf("| Mes: %d | Produto: %s | Opcao: %c |\n",mes1,codigoProduto,letra);
                q3(faturacao,mes1,codigoProduto,letra);
                printf("Pressione Enter para voltar ao menu!\n");
                getchar();
                system("clear");
                break;
            case 4:
                r = 0;
                do{
                    printf("Pretende os resultados em que formato?\n");
                    printf("\t*Globais presse 'G';\n");
                    printf("\t*Filial a filial presse 'F'.\n");
                    printf("OPCAO: ");
			        scanf("%s",&letra);
                    getchar();
                    if(letra == 'F' || letra == 'G'){
                        r = 1;
                    }else{
                        printf("Opcao Invalida!\tTente novamente.\n");
                    }
                }while(r == 0);
                system("clear");
                printf("| Opcao: %c |\n",letra);
                /*q4(char letra)*/
                printf("\n\n\nPressione Enter a para voltar ao menu!\n");
                getchar();
                system("clear");
                break;
            case 5:
                /*q5()*/
                printf("\n\n\nPressione Enter para voltar ao menu!\n");
                getchar();
                system("clear");
                break;
            case 6:
                q6(faturacao);
                printf("\n\n\nPressione Enter para voltar ao menu!\n");
                getchar();
                system("clear");
                break;
            case 7:
                r = 0;
                do{
                    printf("Introduza o codigo do cliente do qual quer saber a informação.\n");
			        scanf("%s",codigoCliente);
                    getchar();
                    if(validaCliente(codigoCliente)){
                        r = 1;
                    }else{
                        printf("Cliente invalido!\tTente novamente.\n");
                    }
                }while(r == 0);
                system("clear");
                printf("| Cliente: %s |\n",codigoCliente);
                /*q7(char* codigoCliente)*/
                printf("\n\n\nPressione Enter para voltar ao menu!\n");
                getchar();
                system("clear");
                break;
            case 8:
                r = 0;
                do{
                    printf("Introduza o intervalo de meses separados por um espaço.\n");
			        scanf("%d %d",&mes1,&mes2);
                    getchar();
                    if(mes1 >= mes2){
                        printf("Intervalo de meses invalido.\tTente novamente.\n");
                    }else if(mes1 >= 1  && mes1 <= 12 && mes2 >= 1 && mes2 <= 12){
                        r = 1;
                    }else{
                        printf("Meses invalidos.\tTente novamente.\n");
                    }
                }while(r == 0);
                system("clear");
                printf("| Intervalo de meses: [%d,%d] |\n",mes1,mes2);
                q8(faturacao,mes1,mes2);
                printf("\n\n\nPressione Enter para voltar ao menu!\n");
                getchar();
                system("clear");
                break;
            case 9:
                r = 0;
                do{
                    printf("Insira um codigo de Produto.\n");
			        scanf("%s",codigoProduto);
                    if(validaProduto(codigoProduto)){
                        r = 1;
                    }else{
                        printf("Produto invalido!\tTente novamente.\n");
                    }
                }while(r == 0);
                r = 0;
                do{
                    printf("Pretende os resultados em que formato?\n");
                    printf("\t*Globais presse 'G';\n");
                    printf("\t*Filial a filial presse 'F'.\n");
                    printf("OPCAO: ");
			        scanf("%s",&letra);
                    getchar();
                    if(letra == 'F' || letra == 'G'){
                        r = 1;
                    }else{
                        printf("Opcao Invalida!\tTente novamente.\n");
                    }
                }while(r == 0);
                system("clear");
                printf("| Produto: %s | Opcao: %c |\n",codigoProduto,letra);
                /*q9(char* codigoProduto,char letra)*/
                printf("\n\n\nPressione Enter para voltar ao menu!\n");
                getchar();
                system("clear");
                break;
            case 10:
                r = 0;
                do{
                    printf("Introduza o codigo do cliente do qual quer saber a informação.\n");
			        scanf("%s",codigoCliente);
                    getchar();
                    if(validaCliente(codigoCliente)){
                        r = 1;
                    }else{
                        printf("Cliente invalido!\tTente novamente.\n");
                    }
                }while(r == 0);
                r = 0;
                do{
                    printf("Introduza um mes.\n");
			        scanf("%d",&mes1);
                    getchar();
                    if(mes1 >= 1  && mes1 <= 12){
                        r = 1;
                    }else{
                        printf("Mese invalido.\tTente novamente.\n");
                    }
                }while(r == 0);
                system("clear");
                printf("| Cliente: %s | Mes: %d |\n",codigoCliente,mes1);
                /*q10(char* codigoCliente,int mes1)*/
                printf("\n\n\nPressione Enter para voltar ao menu!\n");
                getchar();
                system("clear");
                break;
            case 11:
                r = 0;
                do{
                    printf("De quantos produtos deseja saber?\n");
			        scanf("%d",&opcao);
                    getchar();
                    if(opcao>=1){
                        r = 1;
                    }else{
                        printf("Numero invalido.\tTente novamente.\n");
                    }
                }while(r == 0);
                system("clear");
                printf("| Numero de produtos: %d |\n",opcao);
                /*q11(int opcao)*/
                printf("\n\n\nPressione Enter para voltar ao menu!\n");
                getchar();
                system("clear");
                break;
            case 12:
                r = 0;
                do{
                    printf("Introduza o codigo do cliente do qual quer saber a informação.\n");
			        scanf("%s",codigoCliente);
                    getchar();
                    if(validaCliente(codigoCliente)){
                        r = 1;
                    }else{
                        printf("Cliente invalido!\tTente novamente.\n");
                    }
                }while(r == 0);
                system("clear");
                printf("| Cliente: %s |\n",codigoCliente);
                /*q12(char* codigoCliente)*/
                printf("\n\n\nPressione Enter para voltar ao menu!\n");
                getchar();
                system("clear");
                break;
            case 13:
                printf("Em qual das opções precisa de ajuda?\n");
			    scanf("%d",&opcao);
                system("clear");
                ajuda(opcao);
                printf("\n\n\nPressione Enter para voltar ao menu!\n");
                getchar();
                system("clear");
                break;
            case 14:
                system("clear");
                printf("\t\t\t\t\t\tAte Breve!\n");
                break;
            default:
                printf("OPCAO INVALIDA!\tTente novamente.\n");
        }
        
    }while(opcao != 14);
    libertaMemoria(catClientes,catProdutos,faturacao);
    return 0;
}







/*int main(){
    CatalogoClientes catClientes = NULL;
    CatalogoProdutos catProdutos = NULL;
    FaturacaoGlobal faturacao = NULL;
    GestaoClientes gc = NULL;
    q1 (&catClientes,&catProdutos,&faturacao,&gc);
    q2 (catProdutos, 'K');
    q3 (faturacao, 1, "BC1164", 'G');
    q3 (faturacao, 2, "BC1164", 'F');
    q6 (faturacao);
    q8 (faturacao, 1, 12);
    imprimeFaturacao(faturacao);
    libertaMemoria(catClientes,catProdutos,faturacao);
    return 0;
}*/

