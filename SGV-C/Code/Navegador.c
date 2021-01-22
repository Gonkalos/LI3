#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <gmodule.h>
#include "Navegador.h"


int navegadorAux(char* queri[], int i, char resposta,void (f)(char* queri[],int i,char resposta,int k),int k){

    int contador = IMPRIME;
    int iFixo = 0;

    if(resposta == 'i'){
        while(contador != 0 && i<=k){
            printf("%s\n", queri[i]);
            contador = contador - 1;
            i++;
        }
    }

    if(resposta == 'd'){
        iFixo = i;
        while(contador != 0 && i<=k){
                printf("%s\n", queri[i]);
                contador = contador - 1;
                i++;
            }
        if (i<k) (f)(queri,i,resposta,k);
        if (i==k) (f)(queri,iFixo,resposta,k);
    }

    if(resposta == 'e'){
        i -= IMPRIME*2;
        while(contador >= 1 && i>0){
                printf("%s\n", queri[i]);
                contador = contador - 1;
                i++;
            }
        if (i>1) (f)(queri,i,resposta,k);
        if (i<=0) (f)(queri,0,'i',k);
    }

    return (i);
}


void navegador(char* queri[], int i, char resposta, int k){

    if (resposta == 'i'){
        i = navegadorAux(queri,i,resposta,navegador, k);
    }   
    printf("Se quiser a proxima pagina prima 'd', se quiser a anterior prima 'e', se quiser sair prima '0'\n");
    scanf("%s", &resposta);
    system ("clear");
    if (resposta == 'd' || resposta == 'e') navegadorAux(queri,i,resposta,navegador,k);
}
