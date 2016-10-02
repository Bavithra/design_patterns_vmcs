package sg.edu.nus.iss.vmcs.system;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.store.Store;

public interface LoaderDAO {
	
	static final String PROPERTY = "property";
	
	Store ititialize() throws IOException;
	void save(Store store) throws IOException ;

}
