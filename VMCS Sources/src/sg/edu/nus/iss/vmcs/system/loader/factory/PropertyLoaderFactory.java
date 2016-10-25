package sg.edu.nus.iss.vmcs.system.loader.factory;

import sg.edu.nus.iss.vmcs.store.loader.StoreLoader.StoreType;
import sg.edu.nus.iss.vmcs.system.loader.CashPropertyDAO;
import sg.edu.nus.iss.vmcs.system.loader.DrinksPropertyDAO;
import sg.edu.nus.iss.vmcs.system.loader.LoaderDAO;
import sg.edu.nus.iss.vmcs.system.loader.LoaderDAO.StorageType;

public class PropertyLoaderFactory {
	
	public static LoaderDAO buildLoader(StoreType storeType){
		
		LoaderDAO loaderDAO = null;
		switch(storeType){
		case CASH:
			loaderDAO = new CashPropertyDAO(StorageType.PROPERTY);
			break;
		case DRINK:
			loaderDAO = new DrinksPropertyDAO(StorageType.PROPERTY);
			break;
		}
		
		return loaderDAO;
	}

}
