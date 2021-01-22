#ifndef FATURACAO_H
#define FATURACAO_H
#define NUMFILIAIS 3
#define NUMMESES 12



typedef struct faturacaoglobal *FaturacaoGlobal;

typedef struct fatura *Fatura;

typedef Fatura *FaturacaoMes;

typedef FaturacaoMes *FaturacaoFilial;



FaturacaoGlobal inicializaFaturacao(FaturacaoGlobal faturacao,char* produto);

Fatura criaFatura(Fatura f);

Fatura atualizaFatura(Fatura f,int precoUnit,int quantidade,char tipo);

FaturacaoGlobal insereFaturacao(FaturacaoGlobal faturacao,char* codProd,int precoUnit,int quantidade,char tipo,int mes,int filial);

void libertaMemoriaFaturacao(FaturacaoGlobal faturacao);

void imprimeFaturacao(FaturacaoGlobal faturacao);

void querie3G (FaturacaoGlobal faturacao, int mes, char* produto);

void querie3F (FaturacaoGlobal faturacao, int mes, char* produto);

void querie6Aux (gpointer key, gpointer value, int* naoComprados);

void querie6 (FaturacaoGlobal faturacao, int* naoComprados);

typedef struct querie8struct *Querie8Struct;

Querie8Struct newQuerie8 (Querie8Struct q, int mes1, int mes2, double totFaturado, int quantidade);

void querie8FAux (gpointer key, gpointer value, Querie8Struct q);

void querie8F (FaturacaoGlobal faturacao, int mes1, int mes2, double* totFaturado, int* numVendas);



#endif