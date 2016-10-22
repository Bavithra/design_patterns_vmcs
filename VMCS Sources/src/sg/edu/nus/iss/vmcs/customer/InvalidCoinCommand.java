package sg.edu.nus.iss.vmcs.customer;
/**
 * This concrete command class manages the entry of invalid coin.
 * 
 */
public class InvalidCoinCommand implements CoinCommand{
    private CustomerPanel cPanel; 

    public InvalidCoinCommand(CustomerPanel cPanel) {
        this.cPanel = cPanel;
    }

    @Override
    public void execute(Object object) {
		cPanel.displayInvalidCoin(true);
	    cPanel.setTotalMoneyInserted(0);
		cPanel.setChange("Invalid Coin");
    }


}
