package sg.edu.nus.iss.vmcs.system.loader;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.store.CashStore;
import sg.edu.nus.iss.vmcs.store.CashStoreItem;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.loader.StoreLoader.StoreType;
import sg.edu.nus.iss.vmcs.system.Environment;
import sg.edu.nus.iss.vmcs.system.loader.property.CashPropertyLoader;


public class CashPropertyDAO extends LoaderDAO{
	
	private CashPropertyLoader cashLoader;
	
	public CashPropertyDAO(StorageType storageType){
		super(StoreType.CASH, storageType);
	}

	@Override
	public CashStore ititialize() throws IOException {		
		return initializeFromProperty();
	}

	@Override
	public void save(Store cStore) throws IOException {
		if(cStore == null)
			return;
		
		saveToProperty(cStore);
	}
	
	/**
	 * This method initialize the {@link CashStore}.
	 * @throws IOException if fail to initialize cash store; reading properties.
	 */
	private CashStore initializeFromProperty() throws IOException {
		CashStore cStore = new CashStore();
		this.cashLoader = new CashPropertyLoader(Environment.getCashPropFile());
		cashLoader.initialize();
		
		// get the cash file from the environment property file;
		int numOfItems = cashLoader.getNumOfItems();
		cStore.setStoreSize(numOfItems);

		for (int i = 0; i < numOfItems; i++) {
		    CashStoreItem item = (CashStoreItem) cashLoader.getItem(i);
			cStore.addItem(i, item);
		}
		
		return cStore;
	}

	/**
	 * This method saves the attributes of the {@link CashStore} to the input file.
	 * @throws IOException if fail to save cash properties.
	 */
	private void saveToProperty(Store cStore) throws IOException {
		int size = cStore.getStoreSize();
		cashLoader.setNumOfItems(size);
		for (int i = 0; i < size; i++) {
			cashLoader.setItem(i, cStore.getStoreItem(i));
		}
		cashLoader.saveProperty();
		
	}

}
