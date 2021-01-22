#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include <gmodule.h>
#include "FaturacaoGlobal.h"



struct faturacaoglobal{
    GHashTable* hash;
};

struct fatura{
    int numVendas_N;
    int numVendas_P;
    double totalFaturado_N;
    double totalFaturado_P;
};


static guint funcaoHash(gconstpointer produto){
    return g_str_hash(produto);
}


static gboolean comparaChavesProduto(gconstpointer produto1, gconstpointer produto2){
    return g_str_equal(produto1,produto2);
}


/*
void libertaMemoriaF(gpointer key,gpointer value,gpointer user_data){
    printf("CENAS\n");
    FaturacaoFilial fatFilial = (FaturacaoFilial)value;
    FaturacaoMes fatMes = NULL;
    Fatura f = NULL;
    int i,j;
    if(fatFilial){
        for(i = 0; i<NUMFILIAIS ; i++){
            fatMes = fatFilial[i];
            if(fatMes){
                for(j = 0; j<NUMMESES ; j++){
                    f = fatMes[j];
                    if(f){
                        g_free(f);
                    }
                }
                g_free(fatMes);
            }
        }
        g_free(fatFilial);
    }
}


void libertaTodaMemoriaFaturacao(gpointer faturacao){
    if(faturacao){
        g_hash_table_foreach(faturacao,libertaMemoriaF,NULL);
    }
}
*/



FaturacaoGlobal inicializaFaturacao(FaturacaoGlobal faturacao,char* produto){
    if(!faturacao){
        faturacao = (FaturacaoGlobal)g_new0(struct faturacaoglobal,1);
    }
    if(!faturacao->hash){
        faturacao->hash = g_hash_table_new_full(funcaoHash,comparaChavesProduto,free,NULL);
    }
    char* aux = (char*)g_new(char*,strlen(produto)+1);
    strcpy(aux,produto);
    g_hash_table_insert(faturacao->hash,aux,NULL);
    return faturacao;
}


Fatura criaFatura(Fatura f){
    f = (Fatura)g_new(struct fatura,1);
    f->numVendas_N = 0;
    f->numVendas_P = 0;
    f->totalFaturado_N = 0;
    f->totalFaturado_P = 0;
    return f;
}


Fatura atualizaFatura(Fatura f,int precoUnit,int quantidade,char tipo){
    if(!f){
        f = criaFatura(f);
    }
    if(tipo == 'N'){
        f->numVendas_N += quantidade;
        f->totalFaturado_N += quantidade * precoUnit;
    }else{
        f->numVendas_P += quantidade;
        f->totalFaturado_P += quantidade * precoUnit;
    }
    return f;
}


FaturacaoGlobal insereFaturacao(FaturacaoGlobal faturacao,char* codProd,int precoUnit,int quantidade,char tipo,int mes,int filial){
    if(g_hash_table_contains(faturacao->hash,codProd)){
        FaturacaoFilial fatFilial = (FaturacaoFilial)g_hash_table_lookup(faturacao->hash,codProd);
        FaturacaoMes fatMes = NULL;
        Fatura f = NULL;
        if(!fatFilial){
            fatFilial = (FaturacaoFilial)g_new0(FaturacaoMes,NUMFILIAIS);
            fatMes = (FaturacaoMes)g_new0(struct fatura,NUMMESES);
            f = atualizaFatura(f,precoUnit,quantidade,tipo);
            fatMes[mes-1] = f;
            fatFilial[filial-1] = fatMes;
        }else{
            if(!fatMes){
                fatMes = (FaturacaoMes)g_new0(struct fatura,NUMMESES);
            }
            fatMes = fatFilial[filial-1];
            f = atualizaFatura(f,precoUnit,quantidade,tipo);
        }
        g_hash_table_insert(faturacao->hash,codProd,fatFilial);
    }
    return faturacao;
}


void libertaMemoriaFaturacao(FaturacaoGlobal faturacao){
    g_hash_table_destroy(faturacao->hash);
    g_free(faturacao);
}


static void imprimeFatura(Fatura f){
    if(f){
        printf("FATURA:\n");
        printf("\tTipo N:\n");
        printf("\t\tNumero de Vendas: %d\n",f->numVendas_N);
        printf("\t\tTotal Faturado: %.2lf\n",f->totalFaturado_N);
        printf("\tTipo P:\n");
        printf("\t\tNumero de Vendas: %d\n",f->numVendas_P);
        printf("\t\tTotal Faturado: %.2lf\n",f->totalFaturado_P);
    }
}


static void imprimeMeses(FaturacaoMes fatMeses){
    if(fatMeses){
        int j;
        for(j = 0; j<NUMMESES; j++){
            imprimeFatura(fatMeses[j]);
        }
    }
}


static void imprimeFiliais(FaturacaoFilial fatFilial){
    if(fatFilial){
        int i;
        for(i = 0; i<NUMFILIAIS ; i++){
            imprimeMeses(fatFilial[i]);
        }
    }
}


static void imprimeF(gpointer key,gpointer value,gpointer user_data){
    if(key){
        printf("CHAVE   %s\n",key);
    }
    imprimeFiliais(value);
}


void imprimeFaturacao(FaturacaoGlobal faturacao){
    if(faturacao){
        g_hash_table_foreach(faturacao->hash,imprimeF,NULL);
    }
}







/* Queries */


void querie3G (FaturacaoGlobal faturacao, int mes, char* produto){
    int i;
    int numN = 0;
    int numP = 0; 
    if (faturacao && faturacao->hash){
        if (g_hash_table_contains(faturacao->hash,produto)){
            FaturacaoFilial fatFilial = (FaturacaoFilial)g_hash_table_lookup(faturacao->hash,produto);
            FaturacaoMes fatMes;
            Fatura f;
            for (i = 0; i < NUMFILIAIS; i++){
                fatMes = fatFilial[i];
                
                if (fatMes){
                    f = fatMes[mes];
                    if (f){ 
                        numN += f->numVendas_N;
                        numP += f->numVendas_P;
                    }
                }
            }
            printf ("Vendas N: %d\n", numN);
            printf ("Vendas P: %d\n", numP);
        }
    }
}


void querie3F (FaturacaoGlobal faturacao, int mes, char* produto){
    int i;
    int numN = 0;
    int numP = 0;
    if (faturacao && faturacao->hash){
        if (g_hash_table_contains(faturacao->hash,produto)){
            FaturacaoFilial fatFilial = (FaturacaoFilial)g_hash_table_lookup(faturacao->hash,produto);
            FaturacaoMes fatMes;
            Fatura f;
            for (i = 0; i < NUMFILIAIS; i++){
                printf ("Filial %d\n", i+1);
                fatMes = fatFilial[i];
                if (fatMes){
                    f = fatMes[mes];
                    if (f){
                        numN = f->numVendas_N;
                        numP = f->numVendas_P;
                    }
                }
                printf ("Vendas N: %d\n", numN);
                printf ("Vendas P: %d\n", numP);
            }
        }
    }
}


void querie6Aux (gpointer key, gpointer value, int* naoComprados){
    if (!value) (*naoComprados)++;
}


void querie6 (FaturacaoGlobal faturacao, int* naoComprados){
    if (faturacao){ 
        g_hash_table_foreach (faturacao->hash, querie6Aux, naoComprados);
    }
}


struct querie8struct{
    int mes1;
    int mes2;
    double totFaturado;
    int quantidade;
};


Querie8Struct newQuerie8 (Querie8Struct q, int mes1, int mes2, double totFaturado, int quantidade){
    q = (Querie8Struct)g_new(struct querie8struct,1);
    q->mes1 = mes1;
    q->mes2 = mes2;
    q->totFaturado = totFaturado;
    q->quantidade = quantidade;
    return q;
}


void querie8FAux (gpointer key, gpointer value, Querie8Struct q){
    
    int i, j;
    FaturacaoFilial fatFilial = (FaturacaoFilial)value;
    FaturacaoMes fatMes = NULL;
    Fatura f = NULL;
    if (fatFilial){
        for (i = 0; i < NUMFILIAIS; i++){
            fatMes = fatFilial[i];
            if (fatMes){
                for (j = q->mes1; j <= q->mes2; j++){
                    f = fatMes[j];
                    if (f){
                        q->quantidade += f->numVendas_N;
                        q->quantidade += f->numVendas_P;
                        q->totFaturado += f->totalFaturado_N;
                        q->totFaturado += f->totalFaturado_P;
                    }
                }
            }
        }
    }
}
            

void querie8F (FaturacaoGlobal faturacao, int mes1, int mes2, double* totFaturado, int* numVendas){
    if (faturacao){
        Querie8Struct q = NULL;
        double t = 0.0;
        int n = 0;
        q = newQuerie8 (q, mes1, mes2, t, n); 
        g_hash_table_foreach (faturacao->hash, querie8FAux, q);
        (*totFaturado) = q->totFaturado;
        (*numVendas) = q->quantidade;
    }
}

      





                

                








