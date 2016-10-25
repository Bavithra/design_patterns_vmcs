package sg.edu.nus.iss.vmcs.system.loader;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.loader.StoreLoader.StoreType;;

public abstract class LoaderDAO {
	
	public enum StorageType {
		DEFAULT,PROPERTY
	}
	
	private StoreType storeType = null;
	private StorageType storageType = null;
	
	public LoaderDAO (StoreType storeType, StorageType storageType){
		this.storeType = storeType;
		this.storageType = storageType;
	}
	
	public StoreType getStoreType() {
		return storeType;
	}

	public void setStoreType(StoreType storeType) {
		this.storeType = storeType;
	}

	public StorageType getStorageType() {
		return storageType;
	}

	public void setStorageType(StorageType storageType) {
		this.storageType = storageType;
	}

	public abstract Store ititialize() throws IOException;
	public abstract void save(Store store) throws IOException ;

}
