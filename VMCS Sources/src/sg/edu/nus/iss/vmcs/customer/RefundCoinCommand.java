package sg.edu.nus.iss.vmcs.customer;
/**
 * This concrete command class manages the refunding of inserted coin.
 * 
 */
public class RefundCoinCommand implements CoinCommand{

	private CustomerPanel cPanel;
    
    public RefundCoinCommand(CustomerPanel cPanel) {
        this.cPanel = cPanel;
    }

    @Override
    public void execute(Object object) {
    	cPanel.setChange((int)object);
    	cPanel.setTotalMoneyInserted(0);
    	cPanel.displayInvalidCoin(false);

    }
}
