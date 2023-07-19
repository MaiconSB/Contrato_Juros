package service;

import java.time.LocalDate;

import entities.Contract;
import entities.Installment;

public class ContractService {
	
	private OnlinePaymentService payment;
	

	public ContractService(OnlinePaymentService payment) {
		this.payment = payment;
	}

	public void processContract(Contract contract, int months) {
		
		double basicQuota = contract.getTotalValue() / months;
		
		for(int i = 1; i <= months; i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);	
			
			double interest = payment.interest(basicQuota, i);
			double fee = payment.paymentFee(basicQuota + interest);
			double quota = basicQuota + interest + fee;
			contract.getInstallment().add(new Installment(dueDate, quota));
	
			
		}
	}
}
