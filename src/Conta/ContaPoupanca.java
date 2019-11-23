package Conta;

import Dados.ContaMostrarDados;

public class ContaPoupanca extends ContaBancaria implements ContaMostrarDados {

	private double taxaDeOperacao = 0.00;

	public ContaPoupanca(int number) {
		super(number, 0.0);
		taxaDeOperacao = 5.00;
	}

	@Override
	public boolean sacar(double valor) {
		if ((super.saldo - valor - taxaDeOperacao) >= 0) {
			super.saldo -= (valor + taxaDeOperacao);
			return true;
		}
		return false;
	}

	@Override
	public boolean depositar(double valor) {
		if ((super.saldo + valor - taxaDeOperacao) >= 0) {
			super.saldo += valor;
			super.saldo -= taxaDeOperacao;
			return true;
		}
		return false;
	}

	public double getTaxaDeOperacao() {
		return taxaDeOperacao;
	}

	public void setTaxaDeOperacao(double taxaDeOperacao) {
		this.taxaDeOperacao = taxaDeOperacao;
	}

	@Override
	public void mostrarDados() {
		System.out.println("Conta Poupança\n" + "Numero: " + super.number + "\nSaldo : R$" + super.saldo
				+ "\nTaxa de operação : R$" + taxaDeOperacao);

	}

	@Override
	public boolean transferir(double valor, ContaBancaria conta) {
		if (conta != null && this.sacar(valor)) {
			if (conta.depositar(valor))
				return true;
		}
		return false;
	}
}