package br.com.alura.tdd.modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Funcionario {

	private String nome;
	private LocalDate dataAdmissao;
	private static BigDecimal salario;

	public Funcionario(String nome, LocalDate dataAdmissao, BigDecimal salario) {
		this.nome = nome;
		this.dataAdmissao = dataAdmissao;
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public static BigDecimal getSalario() {
		return salario;
	}

	public void reajustaSalario(BigDecimal reajuste) {
		salario = salario.add(reajuste);
		arredondarSalario();
	}

	private void arredondarSalario() {
		salario = salario.setScale(2, RoundingMode.HALF_UP);
	}
}
