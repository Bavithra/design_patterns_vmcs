package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.customer.state.TerminateState;
import sg.edu.nus.iss.vmcs.customer.state.TransactionState;

public class TransactionChain {
	private TransactionState currentState;

	public TransactionChain() {
		currentState = new TerminateState();
	}

	public void setState(TransactionState transactionState) {
		currentState = transactionState;
	}
	
	public TransactionState getState(){
		return currentState;
	}
	
	public void handle(TransactionController txCtrl){
		currentState.handle(this,txCtrl);
	}
}
