#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include <gmodule.h>
#include "CatalogoProdutos.h"

struct catalogoprodutos{
    GTree* tree;
};


static gint comparaProdutos(gconstpointer produto1, gconstpointer produto2,gpointer user_data){
    return (strcmp(produto1,produto2));
}


static gboolean imprimeP(gpointer key,gpointer value, gpointer data){
    printf("%s\n",key);
    return FALSE;
}


CatalogoProdutos insereProduto(CatalogoProdutos catProdutos, char* produto){
    if(!catProdutos){
        /*catProdutos = (CatalogoProdutos) malloc(sizeof(struct catalogoprodutos));*/
        catProdutos = (CatalogoProdutos) g_new(struct catalogoprodutos,1);
        catProdutos->tree = NULL;
    }
    if(!catProdutos->tree){
        catProdutos->tree = g_tree_new_full(comparaProdutos,NULL,g_free,NULL);
    }
    /*char* aux = (char*)malloc(sizeof(char)*(PRODUTO+1));*/
    char* aux = (char*)g_new(char*,PRODUTO+1);
    strcpy(aux,produto);
    g_tree_insert(catProdutos->tree,aux,(gpointer)1);
    return catProdutos;
}


int existeProduto(CatalogoProdutos catProdutos,char* produto){
    int r = 0;
    if(catProdutos){
        r = g_tree_lookup(catProdutos->tree,produto) != NULL;
    }
    return r;
}


void libertaMemoriaProdutos(CatalogoProdutos catProdutos){
    g_tree_destroy(catProdutos->tree);
    free(catProdutos);    
}


void imprimeProdutos(CatalogoProdutos catProdutos){
    if(catProdutos && catProdutos->tree){
        g_tree_foreach(catProdutos->tree,imprimeP,NULL);
    }
}



struct querie2struct{
    char c;
    int n;
    char* q2Array[MAXPRODUTOS];
};


Querie2Struct newQuerie2 (Querie2Struct q, char c, int n){
    q = (Querie2Struct)g_new(struct querie2struct,1);
    q->c = c;
    q->n = n;
    return q;
}


gboolean querie2Aux (gpointer key, gpointer value, Querie2Struct q){
    char* produto = (char*)malloc(sizeof(char)*(PRODUTO+1));
    strcpy (produto, key);
    if (produto[0] == q->c){
        q->q2Array[q->n] = (char*)malloc(sizeof(char)*(PRODUTO+1));
        strcpy (q->q2Array[q->n], produto);
        q->n++;
    }
    return FALSE;
}


void querie2 (CatalogoProdutos catProdutos, char c, int* nTotal, char* produtos[]){
    if (catProdutos){
        int i;
        Querie2Struct q;
        q = newQuerie2 (q, c, 0);
        g_tree_foreach (catProdutos->tree, querie2Aux, q);
        (*nTotal) = q->n;
        for (i = 0; i < q->n; i++){
            produtos[i] = (char*)malloc(sizeof(char)*(PRODUTO+1));
            strcpy (produtos[i], q->q2Array[i]);
            /* printf ("%s\n", q->q2Array[i]); */
        }
    }
}













