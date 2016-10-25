package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.customer.state.TerminateState;
import sg.edu.nus.iss.vmcs.customer.state.TransactionState;

public class TransactionContext {
	private TransactionState currentState;

	public TransactionContext(TransactionController txCtrl) {
		currentState = new TerminateState(txCtrl);
	}

	public void setState(TransactionState transactionState) {
		currentState = transactionState;
	}

	public TransactionState getState() {
		return currentState;
	}
	
	public void startTransaction(int drinkIdentifier) {
		currentState.startTransaction(this, drinkIdentifier);
	}

	public void processMoneyReceived(int total) {
		currentState.processMoneyReceived(this, total);
	}

	public void completeTransaction() {
		currentState.completeTransaction(this);
	}
	
	public void cancelTransaction(){
		currentState.cancelTransaction(this);
	}
	
	public void terminateTransaction(){
		currentState.terminateTransaction(this);
	}

	public void terminateFault() {
		currentState.terminateFault(this);
	}
}
