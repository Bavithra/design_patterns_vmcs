package sg.edu.nus.iss.vmcs.customer.state;

import sg.edu.nus.iss.vmcs.customer.TransactionChain;
import sg.edu.nus.iss.vmcs.customer.TransactionController;

public class ReceiveCoinState implements TransactionState {

	@Override
	public void handle(TransactionChain wrapper,TransactionController txCtrl) {
		if(txCtrl.getCustomerPanel()!=null){
			txCtrl.getCustomerPanel().setTerminateButtonActive(true);
			txCtrl.getDispenseController().allowSelection(false);
		}
		wrapper.setState(new TerminateState());
	}


}
