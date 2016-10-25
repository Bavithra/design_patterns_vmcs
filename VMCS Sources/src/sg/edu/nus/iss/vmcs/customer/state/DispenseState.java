package sg.edu.nus.iss.vmcs.customer.state;

import sg.edu.nus.iss.vmcs.customer.ChangeGiver;
import sg.edu.nus.iss.vmcs.customer.CoinReceiver;
import sg.edu.nus.iss.vmcs.customer.DispenseController;
import sg.edu.nus.iss.vmcs.customer.TransactionContext;
import sg.edu.nus.iss.vmcs.customer.TransactionController;

public class DispenseState extends TransactionState{
	private TransactionController txCtrl;
	private DispenseController dispenseController;
	private CoinReceiver coinReceiver;
	private ChangeGiver changeGiver;
	
	public DispenseState(TransactionController txCtrl){
		this.txCtrl = txCtrl;
		dispenseController = txCtrl.getDispenseController();
		coinReceiver = txCtrl.getCoinReceiver();
		changeGiver = txCtrl.getChangeGiver();
	}

	@Override
	public void startTransaction(TransactionContext context, int drinkIdentifier) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processMoneyReceived(TransactionContext context, int total) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void completeTransaction(TransactionContext context) {
		// TODO Auto-generated method stub
		System.out.println("CompleteTransaction: Begin");
		this.dispenseController.dispenseDrink(this.txCtrl.getSelection());
		int totalMoneyInserted=coinReceiver.getTotalInserted();
		int change=totalMoneyInserted-this.txCtrl.getPrice();
		if(change>0){
			this.changeGiver.giveChange(change);
		}
		else{
			this.txCtrl.getCustomerPanel().setChange(0);
		}
		coinReceiver.storeCash();
		this.dispenseController.allowSelection(true);
		
		this.txCtrl.refreshMachineryDisplay();
		System.out.println("CompleteTransaction: End");
		context.setState(new TerminateState(txCtrl));
		
	}

	@Override
	public void cancelTransaction(TransactionContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminateTransaction(TransactionContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminateFault(TransactionContext context) {
		// TODO Auto-generated method stub
		
	}

}
