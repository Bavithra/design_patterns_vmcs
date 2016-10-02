package sg.edu.nus.iss.vmcs.store;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.system.CashLoaderDAO;

public class CashLoader implements StoreLoader{
	
	private CashLoaderDAO cashLoader;

	@Override
	public CashStore initialize() throws IOException {
		this.cashLoader = new CashLoaderDAO();
		return cashLoader.ititialize();
	}

	@Override
	public void save(Store store) throws IOException {
		cashLoader.save(store);;
	}

}
