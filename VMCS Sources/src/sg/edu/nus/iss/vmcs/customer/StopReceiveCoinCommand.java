package sg.edu.nus.iss.vmcs.customer;
/**
 * This concrete command class manages the stop receiving the coin into vending machine
 * 
 */
public class StopReceiveCoinCommand implements CoinCommand {

	private CustomerPanel cPanel;
    
    public StopReceiveCoinCommand(CustomerPanel cPanel) {
        this.cPanel = cPanel;
    }

    @Override
    public void execute(Object object) {
    	cPanel.getCoinInputBox().setActive(false);
    }
}
