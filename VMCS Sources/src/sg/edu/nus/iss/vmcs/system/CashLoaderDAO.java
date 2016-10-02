package sg.edu.nus.iss.vmcs.system;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.store.CashStore;
import sg.edu.nus.iss.vmcs.store.CashStoreItem;
import sg.edu.nus.iss.vmcs.store.Store;


public class CashLoaderDAO implements LoaderDAO{
	
	private CashPropertyLoader cashLoader;
	private String type;
	
	public CashLoaderDAO(){
		this.type = Environment.getStorageType();
	}

	@Override
	public CashStore ititialize() throws IOException {
		CashStore cStore = null;
		
		if(type.equalsIgnoreCase(PROPERTY)){
			cStore = initializeFromProperty();
		}
		
		return cStore;
	}

	@Override
	public void save(Store cStore) throws IOException {
		if(type.equalsIgnoreCase(PROPERTY)){
			saveToProperty(cStore);
		}
		
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
