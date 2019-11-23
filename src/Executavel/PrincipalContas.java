package Executavel;

import Conta.ContaBancaria;
import Conta.ContaCorrente;
import Conta.ContaPoupanca;
import Dados.ContaMostrarDados;
import Dados.Relatorio;

public class PrincipalContas {

	public static void main(String[] args) {
		ContaBancaria cb1 = new ContaPoupanca(1);
		ContaBancaria cb2 = new ContaCorrente(2);

		cb1.depositar(150.00);
		cb2.depositar(1253.00);

		cb1.sacar(1650);

		cb2.sacar(4000);

		Relatorio relatorio = new Relatorio();

		relatorio.gerarRelatorio((ContaMostrarDados) cb1);
		relatorio.gerarRelatorio((ContaMostrarDados) cb2);

	}

}
