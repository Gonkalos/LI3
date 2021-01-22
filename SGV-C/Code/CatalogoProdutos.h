#ifndef CATALOGOPRODUTOS_H
#define CATALOGOPRODUTOS_H
#define MAXBUFFPRODUTO 9   
#define MAXPRODUTOS 200000
#define PRODUTO 6

/*Tipos de dados relativos ao Catalogo de Produtos*/
typedef struct catalogoprodutos *CatalogoProdutos;


/*Insere um produto no Catalogo de Produtos;
  Retorna:
   - O Catalogo de Produtos resultante da insercao de um produto;
 */
CatalogoProdutos insereProduto(CatalogoProdutos catProdutos, char *produto);

/*Determina se um produto existe;
 Retorna:
    - 1 se existir;
    - 0 se nao existir;
 */
int existeProduto(CatalogoProdutos catProdutos,char *produto);

void libertaMemoriaProdutos(CatalogoProdutos catProdutos);

/*Imprime todos os Produtos do Catalogo de Produtos*/
void imprimeProdutos(CatalogoProdutos catProdutos);


typedef struct querie2struct *Querie2Struct;

Querie2Struct newQuerie2 (Querie2Struct q, char c, int n);

void querie2 (CatalogoProdutos catProdutos, char c, int* nTotal, char* produtos[]);


#endif