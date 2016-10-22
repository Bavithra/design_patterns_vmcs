package sg.edu.nus.iss.vmcs.customer;
/**
 * This concrete command class manages the start receiving the coin into vending machine
 * 
 */
public class StartReceiveCoinCommand implements CoinCommand{

	private CustomerPanel cPanel;
    
    public StartReceiveCoinCommand(CustomerPanel cPanel) {
        this.cPanel = cPanel;
    }

    @Override
    public void execute(Object object) {
    	cPanel.getCoinInputBox().setActive(true);
    	cPanel.setTotalMoneyInserted((int)object);
    }
}
