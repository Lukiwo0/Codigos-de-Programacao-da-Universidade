package Atividade02.Exercicio01;

public class Produto {

    String nomeproduto;
    double precoproduto;
    int quantidadeproduto;
    double valortotalproduto;

    public void exibirInfo() {
        System.out.println("Nome: " + nomeproduto);
        System.out.println("Preco: " + precoproduto);
        System.out.println("Quantidade: " + quantidadeproduto);
        System.out.println("Valor total no estoque: " + valortotalproduto);
    }

    public void adiconarProduto(int addquantidade) {
        this.quantidadeproduto = this.quantidadeproduto + addquantidade;
        this.valortotalproduto = this.quantidadeproduto * this.precoproduto;
    }

    public void removerProduto(int remquantidade) {
        this.quantidadeproduto = this.quantidadeproduto - remquantidade;
        this.valortotalproduto = this.quantidadeproduto * this.precoproduto;
    }

}
