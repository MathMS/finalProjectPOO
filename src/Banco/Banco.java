package Banco;

import java.util.ArrayList;

import Conta.ContaBancaria;
import Dados.ContaMostrarDados;
import Dados.Relatorio;

public class Banco implements ContaMostrarDados {
	private ArrayList<ContaBancaria> contas = new ArrayList<ContaBancaria>();

	public boolean inserir(ContaBancaria conta) {
		if (procurarConta(conta.number) == null) {
			contas.add(conta);
			return true;
		}
		System.err.println("Numero de conta já existente");
		return false;
	}

	public boolean remover(int number) {
		for (ContaBancaria contaBancaria : contas) {
			if (contaBancaria.number == number) {
				contas.remove(contaBancaria);
				return true;
			}
		}
		return false;
	}

	public boolean remover(ContaBancaria conta) {
		return contas.remove(conta);
	}

	public ContaBancaria procurarConta(int number) {
		for (ContaBancaria contaBancaria : contas) {
			if (contaBancaria.number == number) {
				return contaBancaria;
			}
		}
		return null;
	}

	@Override
	public void mostrarDados() {
		Relatorio relatorio = new Relatorio();
		for (ContaBancaria contaBancaria : contas) {
			relatorio.gerarRelatorio((ContaMostrarDados) contaBancaria);
			System.out.println("\n");
		}
	}
}
