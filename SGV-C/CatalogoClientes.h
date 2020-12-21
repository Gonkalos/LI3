#ifndef CATALOGOCLIENTES_H
#define CATALOGOCLIENTES_H
#define MAXBUFFCLIENTE 8
#define MAXCLIENTES 20000
#define CLIENTE 5

/*Tipos de dados relativos ao Catalogo de Clientes*/
typedef struct catalogoclientes *CatalogoClientes;


/*Insere um cliente no Catalogo de Clientes;
  Retorna:
   - O Catalogo de Clientes resultante da insercao de um cliente;
 */
CatalogoClientes insereCliente(CatalogoClientes catClientes,char* cliente);

/*Determina se um cliente existe;
 Retorna:
    - 1 se existir;
    - 0 se nao existir;
 */
int existeCliente(CatalogoClientes catClientes,char *cliente);

void libertaMemoriaClientes(CatalogoClientes catClientes);

/*Imprime todos os Clientes do Catalogo de Clientes*/
void imprimeClientes(CatalogoClientes catClientes);

#endif