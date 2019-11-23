package Conta;

import Dados.ContaMostrarDados;

public class ContaCorrente extends ContaBancaria implements ContaMostrarDados {

	private double limite;

	public ContaCorrente(int number) {
		super(number, 0.0);
		limite = 500.0;
	}

	@Override
	public boolean sacar(double valor) {
		if ((super.saldo - valor) >= (-limite)) {
			super.saldo -= valor;
			return true;
		}
		return false;
	}

	@Override
	public boolean depositar(double valor) {
		super.saldo += valor;
		return true;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	@Override
	public void mostrarDados() {
		System.out.println("Conta Corrente\n" + "Numero: " + super.number + "\nSaldo : R$" + super.saldo
				+ "\nLimite : R$" + limite);

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
