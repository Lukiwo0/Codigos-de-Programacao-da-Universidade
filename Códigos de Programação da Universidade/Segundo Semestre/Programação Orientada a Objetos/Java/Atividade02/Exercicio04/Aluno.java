package Atividade02.Exercicio04;

public class Aluno {

    String aluno;
    double nota01, nota02, nota03, notafinal, notafaltou;

    public void resultadoFinal() {

        this.notafinal = (this.nota01 + this.nota02 + this.nota03);

        if (notafinal >= 70) {
            System.out.printf("Parabens %s voce esta aprovado!\nSua nota foi: %.2f", this.aluno, this.notafinal);
        } else {
            this.notafaltou = 70 - notafinal;
            System.out.printf("Infelizmente %s voce esta reprovado.\nSua nota foi: %.2f\nFaltou %.2f pontos para você ser aprovado.", this.aluno, this.notafinal, this.notafaltou);
        }
    }
}

