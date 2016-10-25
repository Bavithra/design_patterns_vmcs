package sg.edu.nus.iss.vmcs.customer.state;

import sg.edu.nus.iss.vmcs.customer.TransactionContext;

/**
 * This interface illustrate the basic method for all the Transaction State list
 * below <br>
 * 1- Dispense drink. <br>
 * 2- Give change if necessary. <br>
 * 3- Store the Coins that have been entered into the Cash Store. <br>
 * 4- Reset the Drink Selection Box to allow further transactions.
 */
public abstract class TransactionState {

	public abstract void startTransaction(TransactionContext context,int drinkIdentifier);

	public abstract void processMoneyReceived(TransactionContext context,int total);

	public abstract void completeTransaction(TransactionContext context);

	public abstract void cancelTransaction(TransactionContext context);

	public abstract void terminateTransaction(TransactionContext context);

	public abstract void terminateFault(TransactionContext context);

}
