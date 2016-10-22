package sg.edu.nus.iss.vmcs.customer;
/**
 * This concrete command class manages the resetting of all CustomerPanel fields.
 * 
 */
public class ResetCoinCommand implements CoinCommand {

	private CustomerPanel cPanel;
    
    public ResetCoinCommand(CustomerPanel cPanel) {
        this.cPanel = cPanel;
    }

    @Override
    public void execute(Object object) {
    	if(object == null) {
        	cPanel.displayInvalidCoin(false);
            cPanel.setChange("");
            cPanel.getCoinInputBox().setActive(false); 		
    	}
    	else{
			cPanel.setTotalMoneyInserted(0);
    	}
    }
}
