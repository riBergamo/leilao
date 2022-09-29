package br.com.alura.tdd.modelo;

import java.math.BigDecimal;

public enum Desempenho {

    A_DESEJAR {
        @Override
        public BigDecimal percentualReajuste() {
            return new BigDecimal("0.03");
        }
    },
    BOM {
        @Override
        public BigDecimal percentualReajuste() {
            return new BigDecimal("0.12");
        }
    },
    OTIMO {
        @Override
        public BigDecimal percentualReajuste() {
            return new BigDecimal("0.18");
        }
    };

    public abstract BigDecimal percentualReajuste();

}
