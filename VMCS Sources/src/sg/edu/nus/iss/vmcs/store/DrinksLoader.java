package sg.edu.nus.iss.vmcs.store;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.system.DrinksLoaderDAO;

public class DrinksLoader implements StoreLoader{
	
	private DrinksLoaderDAO drinksLoader;

	@Override
	public DrinksStore initialize()  throws IOException{
		this.drinksLoader = new DrinksLoaderDAO();
		return drinksLoader.ititialize();
	}

	@Override
	public void save(Store store) throws IOException {
		drinksLoader.save(store);
	}

}
