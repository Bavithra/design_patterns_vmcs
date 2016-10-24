package sg.edu.nus.iss.vmcs.store.loader;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.system.Environment;
import sg.edu.nus.iss.vmcs.system.loader.LoaderDAO;

public abstract class StoreLoader {
	
	static final String PROPERTY = "property";
	
	protected LoaderDAO loaderDAO;
	protected String type;
	
	public StoreLoader() {
		this.type = Environment.getStorageType();
	}
	public Store initialize() throws IOException{
		return loaderDAO.ititialize();
	}
	public void save(Store store) throws IOException{
		loaderDAO.save(store);
	}

}
