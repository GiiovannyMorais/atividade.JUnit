package com.atividade.junit.funcionario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FuncionarioTerceirizadoTest {

    private FuncionarioTerceirizado funcionario;

    @BeforeEach
    void setup() {
        String nome = "Maria";
        int horasTrabalhadas = 20;
        double valorHora = 40.0;
        double despesa = 200.0;
        funcionario = new FuncionarioTerceirizado(nome, horasTrabalhadas, valorHora, despesa);
    }

    // ✅ Pagamento válido
    @Test
    void deveCalcularPagamentoCorretamente() {
        double pagamento = funcionario.calcularPagamento();

        // 20 * 40 * 4 = 3200 + (200 * 1.1 = 220) = 3420
        assertEquals(3420.0, pagamento, 0.001);
    }

    // ❌ Despesa acima do limite
    @Test
    void testarModificarDespesaAcimaDoLimiteGeraErro() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> funcionario.setDespesaAdicional(1000.01)
        );

        assertEquals("A despesa adicional não pode ultrapassar R$ 1000.00",
                exception.getMessage());
    }

    // ❌ Pagamento ultrapassa teto
    @Test
    void testarDespesaValidaMasPagamentoUltrapassaTeto() {

        funcionario.setHorasTrabalhadas(40);
        funcionario.setValorPorHoras(151.80);
        funcionario.setDespesaAdicional(1000.0);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> funcionario.calcularPagamento()
        );

        assertEquals("O salário não pode ultrapassar o teto de 10000.00",
                exception.getMessage());
    }

    // ❌ Pagamento abaixo do mínimo
    @Test
    void testarPagamentoAbaixoDoMinimoGeraErro() {

        funcionario.setHorasTrabalhadas(5);
        funcionario.setValorPorHoras(20);
        funcionario.setDespesaAdicional(0);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> funcionario.calcularPagamento()
        );

        assertEquals("O salário não pode ser menor que o mínimo.",
                exception.getMessage());
    }

    // ✅ Despesa válida gera pagamento válido
    @Test
    void testarModificarDespesaGeraPagamentoValido() {

        funcionario.setDespesaAdicional(300.0);

        double pagamento = funcionario.calcularPagamento();

        // 3200 + (300 * 1.1 = 330) = 3530
        assertEquals(3530.0, pagamento, 0.001);
    }

    // ✅ Limite exato da despesa
    @Test
    void deveAceitarDespesaNoLimiteMaximo() {

        funcionario.setDespesaAdicional(1000.0);

        double pagamento = funcionario.calcularPagamento();

        // 3200 + 1100 = 4300
        assertEquals(4300.0, pagamento, 0.001);
    }
}