package sg.edu.nus.iss.vmcs.store.loader;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.loader.StoreLoader.StoreType;
import sg.edu.nus.iss.vmcs.system.Environment;
import sg.edu.nus.iss.vmcs.system.loader.LoaderDAO;
import sg.edu.nus.iss.vmcs.system.loader.factory.LoaderFactory;

public abstract class StoreLoader {
	
	public enum StoreType {
		CASH,DRINK
	}
	
	protected LoaderDAO loaderDAO;
	
	private StoreType storeType;
	
	protected StoreLoader(StoreType storeType) {
		this.storeType = storeType;
		loaderDAO = LoaderFactory.buildLoader(storeType);
	}
	public Store initialize() throws IOException{
		return loaderDAO.ititialize();
	}
	public void save(Store store) throws IOException{
		loaderDAO.save(store);
	}

}
