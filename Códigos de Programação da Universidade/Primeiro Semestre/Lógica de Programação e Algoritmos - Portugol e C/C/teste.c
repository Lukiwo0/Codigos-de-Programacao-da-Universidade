#include <stdio.h>

int main() {
  //valor é a variável que
  //será apontada pelo ponteiro
  int valor = 27;
  
  //declaração de variável ponteiro
  int *ptr;
  
  //atribuindo o endereço da variável valor ao ponteiro
  ptr = &valor;
  
  printf("Utilizando ponteiros\n\n");
  printf ("Conteudo da variavel valor: %d\n", valor);
  //vai aparecer 27 que é o contéudo atribuido anteriormente a variável valor

  printf ("Endereço da variavel valor: %p\n", &valor);
  //vai aparecer o endereço dessa variável na memória 

  printf ("Conteudo apontado pelo ponteiro ptr: %d\n", *ptr);
  //como esse ponteiro tá apontando para a variável valor vai aparecer 27 

  printf ("Endereço apontado pelo ponteiro ptr: %p\n", ptr);
  //como esse ponteiro tá apontando para a variável valor vai aparecer o endereço dela 

  printf ("Endereço do ponteiro ptr: %p\n", &ptr);
  //vai aparecer o endereço desse ponteiro na memória 

  return(0);
}