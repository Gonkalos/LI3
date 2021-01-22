#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <gmodule.h>
#include "GestaoFilial.h"


struct gestaoclientes{
    GTree* tree;
}; 


struct gestaoprodutos{
    GHashTable* hash;
};


struct quantidades{
    int num_N;
    int num_P;
};



static guint funcaoHashG(gconstpointer produto){
    return g_str_hash(produto);
}


static gboolean comparaChavesProdutoG(gconstpointer produto1, gconstpointer produto2){
    return g_str_equal(produto1,produto2);
}


static Quantidades criaQuantidades(Quantidades q){
    q = (Quantidades)g_new(struct quantidades,1);
    q->num_N = 0;
    q->num_P = 0;
    return q;
}

static Quantidades atualizaQuantidades(Quantidades q,int quantidade, char tipo){
    if(!q){
        q = criaQuantidades(q);
    }
    if(tipo == 'N'){
        q->num_N += quantidade;
    }else{
        q->num_P += quantidade;
    }
    return q;
}

static GestaoProdutos newGP(GestaoProdutos gp,char* produto){
    if(!gp){
        gp = (GestaoProdutos)g_new0(struct gestaoprodutos,1);
    }
    if(!gp->hash){
        gp->hash = g_hash_table_new_full(funcaoHashG,comparaChavesProdutoG,free,NULL);
    }
    char* aux = (char*)g_new(char*,strlen(produto)+1);
    strcpy(aux,produto);
    g_hash_table_insert(gp->hash,aux,NULL);
    return gp;
}


static GestaoProdutos insertGP(GestaoProdutos gp, char* produto, int quantidade, char tipo, int mes, int filial){
    if(g_hash_table_contains(gp->hash,produto)){
        GestaoFilial gFilial = (GestaoFilial)g_hash_table_lookup(gp->hash,produto);
        GestaoMes gMes = NULL;
        Quantidades q = NULL;
        if(!gFilial){
            gMes = (GestaoMes)g_new0(struct quantidades,NUMMESES);
            q = atualizaQuantidades(q,quantidade,tipo);
            gMes[mes-1] = q;
            gFilial[filial-1] = gMes;
        }else{
            if(!gMes){
                gMes = (GestaoMes)g_new0(struct quantidades,NUMMESES);
            }
            gMes = gFilial[filial-1];
            q = atualizaQuantidades(q,quantidade,tipo);
        }
        g_hash_table_insert(gp->hash,produto,NULL);
    }
    return gp;
}


/*--------------------------------------------------------------------*/

static gint comparaGClientes(gconstpointer cliente1, gconstpointer cliente2,gpointer user_data){
    return (strcmp(cliente1,cliente2));
}


/* Inicializa a struct */
GestaoClientes newGC(GestaoClientes gc, char* cliente){
    if(!gc){
        gc = (GestaoClientes)g_new0(struct gestaoclientes,1);
    }
    if(!gc->tree){
        gc->tree = g_tree_new_full(comparaGClientes,NULL,g_free,NULL);
    } 
    char* aux = (char*)g_new(char*,strlen(cliente)+1);
    strcpy (aux, cliente);
    g_tree_insert (gc->tree, aux,(gpointer)1);
    return gc;
}


GestaoClientes insertGC (GestaoClientes gc, char* produto, int quantidade, char tipo, char* cliente, int mes, int filial){
    if (g_tree_lookup(gc->tree, cliente) != NULL){
        GestaoProdutos gp = NULL;
        if(g_tree_lookup (gc->tree,cliente) == (gpointer)1){
            gp = newGP(gp, produto);
        }else{
            gp = g_tree_lookup(gc->tree,cliente);
        }
        /*gp = insertGP(gp, produto, quantidade, tipo, mes, filial);*/
        g_tree_insert(gc->tree,cliente,gp);
    }
    return gc;
}