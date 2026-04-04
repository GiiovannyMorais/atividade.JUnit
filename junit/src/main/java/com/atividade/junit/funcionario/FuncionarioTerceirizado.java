package com.atividade.junit.funcionario;

public class FuncionarioTerceirizado extends Funcionario {

    private double despesaAdicional;

    public FuncionarioTerceirizado(String nome, int horas, double valorHora, double despesa) {
        super(nome, horas, valorHora);
        setDespesaAdicional(despesa);
    }

    @Override
    public double calcularPagamento() {
        double base = super.calcularPagamento(); // já valida mínimo e teto

        double bonus = despesaAdicional * 1.1;

        double total = base + bonus;

        if (total > 10000.0) {
            throw new IllegalArgumentException("O salário não pode ultrapassar o teto de 10000.00");
        }

        if (total < 1518.0) {
            throw new IllegalArgumentException("O salário não pode ser menor que o mínimo.");
        }

        return total;
    }

    public void setDespesaAdicional(double despesa) {
        if (despesa > 1000.0) {
            throw new IllegalArgumentException("A despesa adicional não pode ultrapassar R$ 1000.00");
        }
        this.despesaAdicional = despesa;
    }
}