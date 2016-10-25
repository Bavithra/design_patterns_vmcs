package sg.edu.nus.iss.vmcs.customer;

import java.awt.Frame;

public abstract class CustomerPanelBuilder {
	
	private CoinInputBox coinInputBox;
	private DrinkSelectionBox drinkSelectionBox;
	
	/*public abstract CustomerPanel createCustomerPanel(Frame fr, TransactionController txCtrl);*/
	
	protected CustomerPanelBuilder buildCoinInputBox(CoinInputBox coinInputBox){
		this.coinInputBox = coinInputBox;
		return this;
	}
	
	protected CustomerPanelBuilder buildDrinkSelectionBox(DrinkSelectionBox drinkSelectionBox){;
		this.drinkSelectionBox = drinkSelectionBox;
		return this;
	}
	
	protected CustomerPanel create(Frame fr, TransactionController ctrl){
		CustomerPanel customerPanel = new CustomerPanel(fr, ctrl, this);
		return customerPanel;
	}

	public CoinInputBox getCoinInputBox() {
		return coinInputBox;
	}

	public DrinkSelectionBox getDrinkSelectionBox() {
		return drinkSelectionBox;
	}
	
	
	
}
