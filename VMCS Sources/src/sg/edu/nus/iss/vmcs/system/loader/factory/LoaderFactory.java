package sg.edu.nus.iss.vmcs.system.loader.factory;

import sg.edu.nus.iss.vmcs.store.loader.StoreLoader.StoreType;
import sg.edu.nus.iss.vmcs.system.Environment;
import sg.edu.nus.iss.vmcs.system.loader.LoaderDAO;
import sg.edu.nus.iss.vmcs.system.loader.LoaderDAO.StorageType;

public class LoaderFactory {
	
	private LoaderFactory(){
		// Private instantiation
	}
	
	public static LoaderDAO buildLoader(StoreType storeType){
		LoaderDAO loaderDAO = null;
		StorageType storageType = StorageType.valueOf(Environment.getStorageType().toUpperCase());
		
		switch(storageType){
		case PROPERTY:
			loaderDAO = PropertyLoaderFactory.buildLoader(storeType);
			break;
		default:
			loaderDAO = PropertyLoaderFactory.buildLoader(storeType);
			break;
		}
		
		return loaderDAO;
	}

}
