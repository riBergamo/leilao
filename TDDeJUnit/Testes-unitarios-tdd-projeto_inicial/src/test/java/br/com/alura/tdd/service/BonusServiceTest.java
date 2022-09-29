package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BonusServiceTest  {

    //maior
    @Test
    public void semBonusParaSalariosAltos() {
        BonusService service = new BonusService();

        //testando com exception - assertThrows(nome da classe da exception que se espera, lambda com o método a ser chamado) : o Test verifica se durante a chamada do método ocorreu a Exception, se ocorreu o test da certo
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularBonus(new Funcionario("Nome", LocalDate.now(), new BigDecimal("25000"))));

        //útil quando você quer pegar a mensagem que sai, verificar se ta correta
        /*
        try {
           service.calcularBonus(new Funcionario("Nome", LocalDate.now(), new BigDecimal("25000")));
           fail("Não deu exception");//se nao tiver uma exception cai nessa linha e lança um fail
        } catch (IllegalArgumentException illegalArgumentException) {
           assertEquals("Funcionário com salário maior que R$10.000 não recebe bônus", illegalArgumentException);
        }
        */

    }

    //menor
    @Test
    public void bonusDeDezPorCentoDeSalariosMenores() {
        BonusService service = new BonusService();
        BigDecimal bonus = service.calcularBonus(new Funcionario("Nome", LocalDate.now(), new BigDecimal("2500")));

        assertEquals(new BigDecimal("250.00"), bonus);

    }

    //igual
    @Test
    public void bonusDeDezPorCentoParaSalarioDe10000() {
        BonusService service = new BonusService();
        BigDecimal bonus = service.calcularBonus(new Funcionario("Nome", LocalDate.now(), new BigDecimal("10000")));

        assertEquals(new BigDecimal("1000.00"), bonus);

    }
}