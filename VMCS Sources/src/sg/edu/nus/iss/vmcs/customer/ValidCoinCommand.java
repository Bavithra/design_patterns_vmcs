package sg.edu.nus.iss.vmcs.customer;

/**
 * This concrete command class manages the entry of valid coin.
 * 
 */
public class ValidCoinCommand implements CoinCommand{
    private CustomerPanel cPanel; 

    public ValidCoinCommand(CustomerPanel cPanel) {
        this.cPanel = cPanel;
    }

    @Override
    public void execute(Object object) {
    	cPanel.getCoinInputBox().setActive(false);
		cPanel.displayInvalidCoin(false);
	    cPanel.setTotalMoneyInserted((int)object);
		cPanel.setChange("");
    }

}
