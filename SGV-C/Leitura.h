#ifndef LEITURA_H
#define LEITURA_H
#define MAXBUFFVENDA 33
#define MAXVENDAS 1000000

/*Tipo de dados relativos à Venda*/
typedef struct venda *Venda;


int validaProduto(char* produto);

int validaCliente(char* cliente);

int lerClientes(CatalogoClientes* catClientes, GestaoClientes* gc, int* numValidos);

int lerProdutos(CatalogoProdutos* catProdutos,FaturacaoGlobal* faturacao,int* numValidos);

int validaVenda(Venda v,CatalogoProdutos catProdutos,CatalogoClientes catClientes);

Venda criaVenda(char* venda,CatalogoProdutos catProdutos,CatalogoClientes catClientes);

void imprimeVenda(Venda v);

int lerVendas(CatalogoProdutos catProdutos,CatalogoClientes catClientes,FaturacaoGlobal* faturacao,GestaoClientes* gc,int* numValidos);

#endif