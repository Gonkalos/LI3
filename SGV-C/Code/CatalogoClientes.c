#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <gmodule.h>
#include "CatalogoClientes.h"

struct catalogoclientes{
    GTree* tree;
};


static gint comparaClientes(gconstpointer cliente1, gconstpointer cliente2,gpointer user_data){
    return (strcmp(cliente1,cliente2));
}


gboolean imprimeC(gpointer key,gpointer value, gpointer data){
    printf("%s\n",key);
    return FALSE;
}


CatalogoClientes insereCliente(CatalogoClientes catClientes,char* cliente){
    if(!catClientes){
        //catClientes = (CatalogoClientes) malloc(sizeof(struct catalogoclientes));
        catClientes = (CatalogoClientes)g_new(struct catalogoclientes,1);
        catClientes->tree = NULL;
    }
    if(!catClientes->tree){
        catClientes->tree = g_tree_new_full(comparaClientes,NULL,g_free,NULL);
    }
    //char* aux = (char*)malloc(sizeof(char)*(CLIENTE+1));
    char* aux = (char*)g_new(char*,CLIENTE+1);
    strcpy(aux,cliente);
    g_tree_insert(catClientes->tree,aux,(gpointer)1);
    return catClientes;
}


int existeCliente(CatalogoClientes catClientes,char *cliente){
    int r = 0;
    if(catClientes){
        r = (g_tree_lookup(catClientes->tree,cliente) != NULL);
    }
    return r;
}


void libertaMemoriaClientes(CatalogoClientes catClientes){
    g_tree_destroy(catClientes->tree);
    free(catClientes);
}


void imprimeClientes(CatalogoClientes catClientes){
    if(catClientes && catClientes->tree){
        g_tree_foreach(catClientes->tree,imprimeC,NULL);
    }
}