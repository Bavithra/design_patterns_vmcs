package sg.edu.nus.iss.vmcs.store;

public interface IStoreItemIterator {

	public void first();
	
	public void next();
	
	public boolean is_done();

	public StoreItem current_item();
}
