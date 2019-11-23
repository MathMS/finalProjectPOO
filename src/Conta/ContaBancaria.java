package Conta;

public abstract class ContaBancaria {
	public int number;
	public double saldo;

	public ContaBancaria(int number, double saldo) {
		this.number = number;
		this.saldo = saldo;
	}

	public abstract boolean sacar(double valor);

	public abstract boolean depositar(double valor);
	
	public abstract boolean transferir(double valor, ContaBancaria conta);
}
