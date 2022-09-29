package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Desempenho;
import br.com.alura.tdd.modelo.Funcionario;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReajusteServiceTest {

    private ReajusteService service;
    private Funcionario funcionario;

    @BeforeEach //coloca o inicializar() no começo de todos os métodos
    public void inicializar() {
        service = new ReajusteService();
        funcionario = new Funcionario("Riane", LocalDate.now(), new BigDecimal("1000"));
    }

//
    @AfterEach // coloca o finalizar() no fim de todos os métodos
    public void finalizar() {
        System.out.println("FIM!");
    }

    @BeforeAll // antes de todos - tem de ser static
    public static void antesDeTodos() {
        System.out.println("ANTES DE TODOS");
    }

    @AfterAll // depois de todos - tem de ser static
    public static void depoisDeTodos() {
        System.out.println("DEPOIS DE TODOS");
    }
//

    //a desejar : 3%
    @Test
    public void desempenhoRuimRecebe3PorCentoDeReajuste() {

        service.reajuste(funcionario, Desempenho.A_DESEJAR);//.A_DESEJAR : constante
        assertEquals(new BigDecimal("1030.00"), Funcionario.getSalario());
    }

    //bom : 15%
    @Test
    public void desempenhoBomRecebe15PorCentoDeReajuste() {

        service.reajuste(funcionario, Desempenho.BOM);
        assertEquals(new BigDecimal("1120.00"), Funcionario.getSalario());
    }

    //otimo : 20%
    @Test
    public void desempenhoOtimoRecebe20PorCentoDeReajuste() {

        service.reajuste(funcionario, Desempenho.OTIMO);
        assertEquals(new BigDecimal("1180.00"), Funcionario.getSalario());
    }

}
