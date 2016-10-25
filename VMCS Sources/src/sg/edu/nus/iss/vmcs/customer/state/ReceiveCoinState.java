package sg.edu.nus.iss.vmcs.customer.state;

import sg.edu.nus.iss.vmcs.customer.CoinReceiver;
import sg.edu.nus.iss.vmcs.customer.DispenseController;
import sg.edu.nus.iss.vmcs.customer.TransactionContext;
import sg.edu.nus.iss.vmcs.customer.TransactionController;

public class ReceiveCoinState extends TransactionState {
	private TransactionController txCtrl;
	private DispenseController dispenseController;
	private CoinReceiver coinReceiver;
	
	public ReceiveCoinState(TransactionController txCtrl){
		this.txCtrl = txCtrl;
		dispenseController = txCtrl.getDispenseController();
		coinReceiver = txCtrl.getCoinReceiver();
	}

	@Override
	public void startTransaction(TransactionContext context, int drinkIdentifier) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processMoneyReceived(TransactionContext context, int total) {
		// TODO Auto-generated method stub
		if(total>=txCtrl.getPrice()){
			context.setState(new DispenseState(txCtrl));
			txCtrl.completeTransaction();
		} else{
			txCtrl.getCoinReceiver().continueReceive();
		}
		
	}

	@Override
	public void completeTransaction(TransactionContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelTransaction(TransactionContext context) {
		// TODO Auto-generated method stub
		System.out.println("CancelTransaction: Begin");
		coinReceiver.stopReceive();
		coinReceiver.refundCash();
		this.dispenseController.allowSelection(true);
		this.txCtrl.refreshMachineryDisplay();
		System.out.println("CancelTransaction: End");
		context.setState(new TerminateState(txCtrl));
	}

	@Override
	public void terminateTransaction(TransactionContext context) {
		// TODO Auto-generated method stub
		System.out.println("TerminateTransaction: Begin");
		this.dispenseController.allowSelection(false);
		coinReceiver.stopReceive();
		coinReceiver.refundCash();
		if(this.txCtrl.getCustomerPanel() != null){
			this.txCtrl.getCustomerPanel().setTerminateButtonActive(false);
		}
		this.txCtrl.refreshMachineryDisplay();
		System.out.println("TerminateTransaction: End");
		context.setState(new TerminateState(txCtrl));
	}

	@Override
	public void terminateFault(TransactionContext context) {
		// TODO Auto-generated method stub
		System.out.println("TerminateFault: Begin");
		this.dispenseController.allowSelection(false);
		coinReceiver.refundCash();
		this.txCtrl.refreshMachineryDisplay();
		System.out.println("TerminateFault: End");
		context.setState(new TerminateState(txCtrl));
	}




}
