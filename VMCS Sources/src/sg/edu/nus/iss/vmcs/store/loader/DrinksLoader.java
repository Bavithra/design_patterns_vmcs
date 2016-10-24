package sg.edu.nus.iss.vmcs.store.loader;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.store.DrinksStore;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.system.loader.DrinksPropertyDAO;

public class DrinksLoader extends StoreLoader{
	
	public DrinksLoader(){
		super();
		if(loaderDAO == null)
			if(type.equalsIgnoreCase(PROPERTY))
				loaderDAO = new DrinksPropertyDAO();
	}

	@Override
	public DrinksStore initialize()  throws IOException{
		return (DrinksStore) loaderDAO.ititialize();
	}

	@Override
	public void save(Store store) throws IOException {
		loaderDAO.save(store);
	}

}
