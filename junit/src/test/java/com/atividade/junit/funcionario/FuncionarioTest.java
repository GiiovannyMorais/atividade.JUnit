package com.atividade.junit.funcionario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class FuncionarioTest {

    private Funcionario funcionario;

    @BeforeEach
    void inicializando() {
        int horasTrabalhadas = 20;
        String nome = "João Silva";
        double valorHora = 40.0;
        funcionario = new Funcionario(nome, horasTrabalhadas , valorHora);
    }

    @Test
    void testarCalcularPagamentoValido() {
        double pagamento = funcionario.calcularPagamento();
        assertEquals(3200.0, pagamento, 0.001);
    }

    @Test
    void testarModificarHorasAbaixoLimiteInferiorGeraErro() {
        int horasTrabalhadasInvalida = 4;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> funcionario.setHorasTrabalhadas(horasTrabalhadasInvalida));

        assertEquals("O número de horas trabalhadas por funcionários próprios deve ser um valor entre 5 e 40.",
                exception.getMessage());
    }

    @Test
    void testarModificarHorasAcimaLimiteSuperiorGeraErro() {
        int horasAcimaDoLimite = 41;
       IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> funcionario.setHorasTrabalhadas(horasAcimaDoLimite));

        assertEquals("O número de horas trabalhadas por funcionários próprios deve ser um valor entre 5 e 40.",
                exception.getMessage());
    }
    
    @Test
    void deveLancarErroQuandoPagamentoUltrapassaTeto(){

    funcionario.setValorPorHoras(151.80);
    funcionario.setHorasTrabalhadas(20);

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> funcionario.calcularPagamento()
    );

    assertEquals("O salário não pode ultrapassar o teto de 10000.00",
        exception.getMessage());
    }
    
    @Test
    void testarModificarHorasComValoresValidosProduzPagamentoEsperado(){

           funcionario.setHorasTrabalhadas(30);
           funcionario.setValorPorHoras(40);

           double pagamento = funcionario.calcularPagamento();

      assertEquals(4800.0, pagamento, 0.001);
    }

    @Test
    void testarModificarValorPorHoraAbaixoLimiteInferiorGeraErro() {
    
        double ModificarValorPorHoraAbaixo = 15.17;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> funcionario.setValorPorHoras(ModificarValorPorHoraAbaixo)); // abaixo de 1% de 1518

        assertEquals("O valor por hora precisa ser entre 1% e 10% do salário mínimo.",
                exception.getMessage());
    }
    @Test
    void testarModificarValorPorHoraAcimaLimiteSuperiorGeraErro() {

    double valorAcima = 151.81; // acima de 10%

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> funcionario.setValorPorHoras(valorAcima)
    );

    assertEquals("O valor por hora precisa ser entre 1% e 10% do salário mínimo.",
        exception.getMessage());
    }


    @Test
    void testarValorHoraValidoMasPagamentoUltrapassaTeto(){

      // aqui vai tentar forçar dar erro , tem que ser um valor entre 5 e 40  
    funcionario.setHorasTrabalhadas(160);
    funcionario.setValorPorHoras(151.80);

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> funcionario.calcularPagamento()
    );

    assertEquals("O salário não pode ultrapassar o teto de 10000.00",
        exception.getMessage());
    }

    @Test
    void testarModificarValorPorHoraComValoresValidosProduzPagamentoEsperado() {
        funcionario.setValorPorHoras(50.0);

        double pagamento = funcionario.calcularPagamento();
        // 20 horas * 50,00 * 4 semanas = 4000,00
        assertEquals(4000.0, pagamento, 0.001);
    }
    

    @Test
    void deveLancarErroQuandoValorHoraAbaixoDe1PorCentoDoSalarioMinimo(){

    double salarioMinimo = 1518.0;
    double valorHoraInvalido = salarioMinimo * 0.01 - 0.01;

    assertThrows(IllegalArgumentException.class, () -> {
        funcionario.setValorPorHoras(valorHoraInvalido);
    });
    }

    @Test
    void deveLancarErroQuandoPagamentoForAbaixoDoSalarioMinimo(){

    funcionario.setHorasTrabalhadas(5); // 20h/mês
    funcionario.setValorPorHoras(20);   // 400 total

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> funcionario.calcularPagamento()
    );

    assertEquals("O salário não pode ser menor que o mínimo.",
            exception.getMessage());
}
    @Test
void deveAceitarHorasNoLimiteMinimo(){
    funcionario.setHorasTrabalhadas(5);
    funcionario.setValorPorHoras(80);

    double pagamento = funcionario.calcularPagamento();

    assertEquals(5 * 80 * 4, pagamento, 0.001);
}
@Test
void deveAceitarHorasNoLimiteMaximo(){
    funcionario.setHorasTrabalhadas(40);

    double pagamento = funcionario.calcularPagamento();

    assertEquals(40 * 40 * 4, pagamento, 0.001);
}
@Test
void deveAceitarValorHoraNoLimiteMinimo(){
    funcionario.setValorPorHoras(15.18);

    double pagamento = funcionario.calcularPagamento();

    assertEquals(20 * 15.18 * 4, pagamento, 0.001);
}
@Test
void deveAceitarValorHoraNoLimiteMaximo(){

    funcionario.setValorPorHoras(151.80);

    
    funcionario.setHorasTrabalhadas(5); 

    double pagamento = funcionario.calcularPagamento();

    assertEquals(5 * 151.80 * 4, pagamento, 0.001);
}

}

