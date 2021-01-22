CC=gcc
CFLAGS=-Wall -ansi -O2 -g
GLIBCFLACGS=`pkg-config --cflags glib-2.0`
GLIBLIBS=`pkg-config --libs glib-2.0`

SGV: CatalogoClientes.o CatalogoProdutos.o FaturacaoGlobal.o GestaoFilial.o Leitura.o Queries.o Navegador.o main.c
	$(CC) $(CFLAGS) $(GLIBCFLACGS) $(GLIBLIBS) CatalogoClientes.o CatalogoProdutos.o FaturacaoGlobal.o GestaoFilial.o Leitura.o Queries.o Navegador.o main.c -o SGV


CatalogoClientes.o: CatalogoClientes.c 
	$(CC) $(CFLAGS) $(GLIBCFLACGS) -c CatalogoClientes.c -o CatalogoClientes.o


CatalogoProdutos.o: CatalogoProdutos.c
	$(CC) $(CFLAGS) $(GLIBCFLACGS) -c CatalogoProdutos.c -o CatalogoProdutos.o


FaturacaoGlobal.o: FaturacaoGlobal.c
	$(CC) $(CFLAGS) $(GLIBCFLACGS) -c FaturacaoGlobal.c -o FaturacaoGlobal.o


GestaoFilial.o: GestaoFilial.c
	$(CC) $(CFLAGS) $(GLIBCFLACGS) -c GestaoFilial.c -o GestaoFilial.o


Leitura.o: Leitura.c
	$(CC) $(CFLAGS) $(GLIBCFLACGS) -c Leitura.c -o Leitura.o


Queries.o: Queries.c
	$(CC) $(CFLAGS) $(GLIBCFLACGS) -c Queries.c -o Queries.o


Navegador.o: Navegador.c
	$(CC) $(CFLAGS) $(GLIBCFLACGS) -c Navegador.c -o Navegador.o

	
	
clean:
	rm SGV
	rm *.o
	rm -rf SGV.dSYM
