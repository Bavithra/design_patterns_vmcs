package sg.edu.nus.iss.vmcs.customer.state;

import sg.edu.nus.iss.vmcs.customer.TransactionChain;
import sg.edu.nus.iss.vmcs.customer.TransactionController;

/**
 * This interface illustrate the basic method for all the Transaction State list below
 * <br>
 * 1- Dispense drink.
 * <br>
 * 2- Give change if necessary.
 * <br>
 * 3- Store the Coins that have been entered into the Cash Store.
 * <br>
 * 4- Reset the Drink Selection Box to allow further transactions.
 */
public interface TransactionState {
	public  void handle(TransactionChain wrapper,TransactionController txCtrl);
	
}
