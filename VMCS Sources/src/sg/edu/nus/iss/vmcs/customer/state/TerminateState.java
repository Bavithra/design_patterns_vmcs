package sg.edu.nus.iss.vmcs.customer.state;

import sg.edu.nus.iss.vmcs.customer.TransactionChain;
import sg.edu.nus.iss.vmcs.customer.TransactionController;

public class TerminateState implements TransactionState {

	@Override
	public void handle(TransactionChain wrapper,TransactionController txCtrl) {
		if(txCtrl.getCustomerPanel()!=null){
			txCtrl.getCustomerPanel().setTerminateButtonActive(false);
			txCtrl.getDispenseController().allowSelection(true);
		}
		wrapper.setState(new ReceiveCoinState());
	}

}
