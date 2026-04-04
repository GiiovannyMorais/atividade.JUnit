package com.atividade.junit.funcionario;

public class Funcionario {

    private String nome;
    private int horasTrabalhadas; // semanais
    private double valorPorHora;

    private static final double SALARIO_MINIMO = 1518.0;
    private static final double TETO_SALARIO = 10000.0;

    public Funcionario(String nome, int horasTrabalhadas, double valorPorHora) {
        this.nome = nome;
        setHorasTrabalhadas(horasTrabalhadas);
        setValorPorHoras(valorPorHora);
    }

    //  requisito para o calculo
    public double calcularPagamento() {
        double pagamento = horasTrabalhadas * valorPorHora * 4;

        if (pagamento < SALARIO_MINIMO) { // se o pagamento for menor que o salario
            throw new IllegalArgumentException("O salário não pode ser menor que o mínimo.");
        }

        if (pagamento > TETO_SALARIO) {// se o pagamento for maior que o salario
            throw new IllegalArgumentException("O salário não pode ultrapassar o teto de 10000.00");
        }

        return pagamento;
    }

    // setters com validação

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        if (horasTrabalhadas < 5 || horasTrabalhadas > 40) {
            throw new IllegalArgumentException(
                "O número de horas trabalhadas por funcionários próprios deve ser um valor entre 5 e 40."
            );
        }
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public void setValorPorHoras(double valorPorHora) {
        double min = SALARIO_MINIMO * 0.01; // 15.18
        double max = SALARIO_MINIMO * 0.10; // 151.80

        if (valorPorHora < min || valorPorHora > max) {
            throw new IllegalArgumentException(
                "O valor por hora precisa ser entre 1% e 10% do salário mínimo."
            );
        }

        this.valorPorHora = valorPorHora;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}