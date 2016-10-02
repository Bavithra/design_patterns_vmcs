package sg.edu.nus.iss.vmcs.store;

import java.io.IOException;

public interface StoreLoader {
	
	Store initialize() throws IOException;
	void save(Store store) throws IOException ;

}
