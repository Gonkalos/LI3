#ifndef GESTAOFILIAL_H
#define GESTAOFILIAL_H
#define NUMFILIAIS 3
#define NUMMESES 12


typedef struct gestaoclientes *GestaoClientes;

typedef struct gestaoprodutos *GestaoProdutos;

typedef struct quantidades *Quantidades;

typedef Quantidades *GestaoMes;

typedef GestaoMes *GestaoFilial;



GestaoClientes newGC(GestaoClientes gc, char* cliente);

GestaoClientes insertGC (GestaoClientes gc, char* produto, int quantidade, char tipo, char* cliente, int mes, int filial);

#endif