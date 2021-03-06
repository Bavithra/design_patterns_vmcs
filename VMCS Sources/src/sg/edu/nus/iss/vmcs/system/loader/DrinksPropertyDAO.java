package sg.edu.nus.iss.vmcs.system.loader;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.store.DrinksStore;
import sg.edu.nus.iss.vmcs.store.DrinksStoreItem;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreObject;
import sg.edu.nus.iss.vmcs.store.loader.StoreLoader.StoreType;
import sg.edu.nus.iss.vmcs.system.Environment;
import sg.edu.nus.iss.vmcs.system.loader.property.DrinkPropertyLoader;

public class DrinksPropertyDAO extends LoaderDAO{
	
	private DrinkPropertyLoader drinksLoader;
	
	public DrinksPropertyDAO(StorageType storageType){
		super(StoreType.DRINK, storageType);
	}

	@Override
	public DrinksStore ititialize()  throws IOException {
		return initializeDrinkStore();
	}

	@Override
	public void save(Store dStore) throws IOException {
		if(dStore == null)
			return;
		
		saveDrinksProperties(dStore);
	}
	
	/**
	 * This method initialize the {@link DrinksStore}.
	 * @throws IOException if fail to initialize drinks store; reading properties.
	 */
	private DrinksStore initializeDrinkStore()  throws IOException{
		DrinksStore dStore = new DrinksStore();
		this.drinksLoader =
				new DrinkPropertyLoader(Environment.getDrinkPropFile());
		drinksLoader.initialize();

		// get the drink file from the environment property file;
		int numOfItems = drinksLoader.getNumOfItems();
		dStore.setStoreSize(numOfItems);

		for (int i = 0; i < numOfItems; i++) {
            DrinksStoreItem item = (DrinksStoreItem) drinksLoader.getItem(i);
			StoreObject brand = item.getContent();
			StoreObject existingBrand = dStore.findObject(brand.getName());
			if (existingBrand != null) {
			    item.setContent(existingBrand);
			}
			dStore.addItem(i, item);
		}
		
		return dStore;
	}
	
	/**
	 * This method saves the attributes of the {@link DrinksStore} to the input file.
	 * It saves the drink property when simulation is ended.
	 * @throws IOException if fail to save drinks properties.
	 */
	private void saveDrinksProperties(Store dStore) throws IOException {
		int size = dStore.getStoreSize();
		drinksLoader.setNumOfItems(size);
		for (int i = 0; i < size; i++) {
			drinksLoader.setItem(i, dStore.getStoreItem(i));
		}
		drinksLoader.saveProperty();
	}

}
