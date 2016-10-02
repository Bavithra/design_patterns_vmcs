package sg.edu.nus.iss.vmcs.system;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.store.DrinksStore;
import sg.edu.nus.iss.vmcs.store.DrinksStoreItem;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreObject;

public class DrinksLoaderDAO implements LoaderDAO{
	
	private DrinkPropertyLoader drinksLoader;
	private String type;
	
	public DrinksLoaderDAO (){
		this.type = Environment.getStorageType();
	}

	@Override
	public DrinksStore ititialize()  throws IOException {
		DrinksStore dStore = null;
		
		if(type.equals(PROPERTY)){
			dStore = initializeDrinkStore();
		}
		
		return dStore;
	}

	@Override
	public void save(Store dStore) throws IOException {
		if(type.equals(PROPERTY)){
			saveDrinksProperties(dStore);
		}
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
