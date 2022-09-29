package br.com.alura.leilao;

import br.com.alura.leilao.dao.PagamentoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Pagamento;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.service.GeradorDePagamento;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.*;

public class GeradorDePagamentoTest {

    private GeradorDePagamento gerador;

    @Mock
    private PagamentoDao pagamentoDao;

    @Captor//capturar um objeto que foi passado por metodo de um mock
    private ArgumentCaptor<Pagamento> captor;

    @Mock
    private Clock clock;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        this.gerador = new GeradorDePagamento(pagamentoDao, clock);
    }

    @Test
    public void deveriaCriarPagamentoParaVencedorDoLeilao() {
        Leilao leilao = leiloes();
        Lance vencedor = leilao.getLanceVencedor();

        LocalDate data = LocalDate.of(2022, 07, 14);

        Instant instant = data.atStartOfDay(ZoneId.systemDefault()).toInstant();//pega data, transforma localdatetime, pega zoneid do sistema e converte para instant

        Mockito.when(clock.instant()).thenReturn(instant);
        Mockito.when(clock.getZone()).thenReturn(ZoneId.systemDefault());

        gerador.gerarPagamento(vencedor);

        Mockito.verify(pagamentoDao).salvar(captor.capture());//verifica se o pagamentoDao teve o metodo salvar chamado

        Pagamento pagamento = captor.getValue();
        //verifica data de vencimento do pagamento do vencedor
        Assert.assertEquals(LocalDate.now().plusDays(1),
                pagamento.getVencimento());

        Assert.assertEquals(vencedor.getValor(), pagamento.getValor());//valor do vencedor tem q ser o msm do pagamento
        Assert.assertFalse(pagamento.getPago());//pagamento n pode estar pago
        Assert.assertEquals(vencedor.getUsuario(), pagamento.getUsuario());
        Assert.assertEquals(leilao, pagamento.getLeilao());

    }

    private Leilao leiloes() {
        Leilao leilao = new Leilao("Celular",
                new BigDecimal("500"),
                new Usuario("Fulano"));

        Lance lance = new Lance(new Usuario("Ciclano"),
                new BigDecimal("900"));

        leilao.propoe(lance);
        leilao.setLanceVencedor(lance);

        return leilao;

    }
}
