package Executavel;

import java.io.IOException;
import java.util.Scanner;

import Banco.Banco;
import Conta.ContaBancaria;
import Conta.ContaCorrente;
import Conta.ContaPoupanca;
import Dados.ContaMostrarDados;
import Dados.Relatorio;

public class PrincipalBanco {

	private static Scanner scan;
	private static Banco banco = new Banco();

	public static void main(String[] args) throws IOException, InterruptedException {
		while (true) {
			boolean status = false;
			int option = mostrarMenu();

			limpaTela();

			switch (option) {
			case 1:
				status = criarConta();
				break;
			case 2:
				status = selecionarConta();
				break;
			case 3:
				status = removerConta();
				break;
			case 4:
				gerarRelatorio();
				status = true;
				break;
			case 5:
				return;
			default:
				break;
			}
			if (status)
				System.out.println("Operação realizada com sucesso");
			else
				System.out.println("Operação não realizada");
			Thread.sleep(1500);
			limpaTela();
		}
	}

	public static int mostrarMenu() {
		scan = new Scanner(System.in);
		System.out.println("\t Menu Banco Permanete");
		System.out.println("1. Criar conta");
		System.out.println("2. Selecionar conta");
		System.out.println("3. Remover conta");
		System.out.println("4. Gerar relatório");
		System.out.println("5. Finalizar");

		return scan.nextInt();
	}

	public static boolean criarConta() {
		scan = new Scanner(System.in);
		System.out.println("\t Menu Criar Conta");
		System.out.println("1. Criar conta poupança");
		System.out.println("2. Criar conta corrente");
		int option = scan.nextInt();
		System.out.println("Numero da Conta");
		int number = scan.nextInt();

		if (option == 1) {
			ContaPoupanca cp = new ContaPoupanca(number);
			banco.inserir(cp);
			return true;
		} else if (option == 2) {
			ContaCorrente cc = new ContaCorrente(number);
			banco.inserir(cc);
			return true;
		}
		return false;
	}

	public static boolean selecionarConta() throws InterruptedException {
		scan = new Scanner(System.in);
		System.out.println("\t Menu Selecionar Conta");
		System.out.println("Digite o numero da conta");
		int number = scan.nextInt();

		ContaBancaria cb = banco.procurarConta(number);

		if (cb != null) {
			return selecionarContaMenu(cb);
		} else {
			System.err.println("Conta inexistente");
		}

		return false;
	}

	public static boolean selecionarContaMenu(ContaBancaria conta) throws InterruptedException {
		while (true) {
			String option = selecionarContamostrarMenu();
			boolean status = false;
			double saldo = 0;
			int number = 0;
			Relatorio relatorio = new Relatorio();
			switch (option) {
			case "a":
				System.out.print("\nDigite o valor a depositar: R$");
				saldo = scan.nextDouble();
				status = conta.depositar(saldo);
				break;
			case "b":
				System.out.print("\nDigite o valor a sacar: R$");
				saldo = scan.nextDouble();
				status = conta.sacar(saldo);
				break;
			case "c":
				System.out.print("\nDigite o valor a transferir: R$");
				saldo = scan.nextDouble();

				System.out.print("\nDigite o numero da conta que deseja transferir:");
				number = scan.nextInt();
				ContaBancaria cb = banco.procurarConta(number);
				if (cb != null) {
					status = conta.transferir(saldo, cb);
				} else {
					System.err.println("Conta inexistente");
				}
				break;
			case "d":
				relatorio.gerarRelatorio((ContaMostrarDados) conta);
				status = true;
				break;
			case "e":
				return true;
			}
			if (status)
				System.out.println("Operação realizada com sucesso");
			else
				System.out.println("Operação não realizada");

			Thread.sleep(1500);
			limpaTela();

		}
	}

	public static String selecionarContamostrarMenu() {
		scan = new Scanner(System.in);
		System.out.println("\t Menu Selecionar Conta");
		System.out.println("a. Depositar");
		System.out.println("b. Sacar");
		System.out.println("c. Transferir");
		System.out.println("d. Gerar Relatorio");
		System.out.println("e. Retornar ao menu anterior");

		return scan.next();
	}

	public static boolean removerConta() {
		scan = new Scanner(System.in);
		System.out.println("\t Menu Remover Conta");
		System.out.println("Digite o numero da conta");
		int number = scan.nextInt();

		System.out.println("Tem certeza que deseja remover a conta de numero:" + number + "\n(s|n):");
		String option = scan.next();
		option = option.toLowerCase();

		if (option.equals("s")) {
			boolean exec = banco.remover(number);
			if (exec) {
				return true;
			} else {
				System.err.println("Conta inexistente ");
			}
		}
		return false;
	}

	public static boolean gerarRelatorio() {
		banco.mostrarDados();
		return true;
	}

	public static void limpaTela() {
		for (int i = 0; i < 3; i++) {
			System.out.println("");
		}
	}
}
