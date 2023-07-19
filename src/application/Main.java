package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import service.ContractService;
import service.PaypalService;

public class Main {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre os dados do contrato: ");
		System.out.print("Numero: ");
		int numContrato = sc.nextInt();
		
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate data = LocalDate.parse(sc.next(), fmt);
		
		System.out.print("Valor do contrato: ");
		double valorContrato = sc.nextDouble();
		
		Contract contract = new Contract(numContrato, data, valorContrato);
		
		System.out.print("Entre com o número de parcelas: ");
		int parcelas = sc.nextInt();
		
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, parcelas);
		
		System.out.println("Parcelas: ");
		for(Installment x : contract.getInstallment()) {
			System.out.println(x);
		}
		
		
		
		sc.close();
	}

	
}
