package sg.edu.nus.iss.vmcs.customer.state;

import sg.edu.nus.iss.vmcs.customer.TransactionContext;
import sg.edu.nus.iss.vmcs.customer.TransactionController;
import sg.edu.nus.iss.vmcs.store.DrinksBrand;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreItem;

public class TerminateState extends TransactionState {
	private TransactionController txCtrl;
	
	public TerminateState(TransactionController txCtrl){
		this.txCtrl= txCtrl;
	}

	@Override
	public void startTransaction(TransactionContext context,int drinkIdentifier) {
		// TODO Auto-generated method stub
		txCtrl.setSelection(drinkIdentifier);
		StoreItem storeItem=txCtrl.getMainController().getStoreController().getStoreItem(Store.DRINK,txCtrl.getSelection());
		DrinksBrand drinksBrand=(DrinksBrand)storeItem.getContent();
		txCtrl.setPrice(drinksBrand.getPrice());
		txCtrl.getChangeGiver().resetChange();
		txCtrl.getDispenseController().ResetCan();
		txCtrl.getChangeGiver().displayChangeStatus();
		txCtrl.getDispenseController().allowSelection(false);
		txCtrl.getCoinReceiver().startReceiver();
		txCtrl.getCustomerPanel().setTerminateButtonActive(true);
		context.setState(new ReceiveCoinState(txCtrl));
	}

	@Override
	public void processMoneyReceived(TransactionContext context,int total) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void completeTransaction(TransactionContext context) {
		// TODO Auto-generated method stub
		
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
