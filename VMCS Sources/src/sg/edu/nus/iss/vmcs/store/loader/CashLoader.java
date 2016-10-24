package sg.edu.nus.iss.vmcs.store.loader;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.store.CashStore;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.system.loader.CashPropertyDAO;

public class CashLoader extends StoreLoader{
	
	public CashLoader() {
		super();
		if(loaderDAO == null)
			if(type.equalsIgnoreCase(PROPERTY))
				loaderDAO = new CashPropertyDAO();
	}

	@Override
	public CashStore initialize() throws IOException {
		return (CashStore) loaderDAO.ititialize();
	}

	@Override
	public void save(Store store) throws IOException {
		loaderDAO.save(store);;
	}

}
