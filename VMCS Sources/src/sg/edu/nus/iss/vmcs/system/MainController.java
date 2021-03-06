/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.system;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import sg.edu.nus.iss.vmcs.customer.CustomerPanel;
import sg.edu.nus.iss.vmcs.customer.TransactionController;
import sg.edu.nus.iss.vmcs.machinery.MachineryController;
import sg.edu.nus.iss.vmcs.maintenance.MaintenanceController;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This object is the main controller of the vending machine&#46; It coordinates the main operations of the VMCS&#46;
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class MainController implements Observer{
	private SimulationController  simulatorCtrl;
	private MachineryController   machineryCtrl;
	private MaintenanceController maintenanceCtrl;
	private TransactionController txCtrl;
	private StoreController       storeCtrl;

	/**
	 * This constructor creates an instance of MainController object.
	 * @param propertyFile the property file name.
	 * @throws VMCSException 
	 */
	public MainController(String propertyFile) throws VMCSException {
		Environment.initialize(propertyFile);
	}

	/**
	 * This method will initiate the creation of all the control objects necessary for
	 * simulation of the vending machine, and initiate the display of the SimulatorControlPanel
	 * (the main menu).
	 * @throws VMCSException if fail to initialize.
	 */
	public void start() throws VMCSException {
		initialize();
		simulatorCtrl.displaySimulatorControlPanel();
		simulatorCtrl.setSimulationActive(false);
	}

	/**
	 * This method creates all the control objects.
	 * @throws VMCSException if fail to load drinks properties or cash properties.
	 */
	public void initialize() throws VMCSException {
		try {
			storeCtrl = new StoreController();
			storeCtrl.initialize();
			simulatorCtrl = new SimulationController(this);
			machineryCtrl = new MachineryController(this);
			machineryCtrl.initialize();
			maintenanceCtrl = new MaintenanceController(this);
			txCtrl=new TransactionController(this);
			// Added for implementing Observer pattern
			maintenanceCtrl.addObserver(this);
		} catch (IOException e) {
			throw new VMCSException(
				"MainController.initialize",
				e.getMessage());
		}
	}

	/**
	 * This method returns the SimulationController.
	 * @return the SimulationController.
	 */
	public SimulationController getSimulationController() {
		return simulatorCtrl;
	}

	/**
	 * This method returns the SimulatorControlPanel.
	 * @return the SimulatorControlPanel.
	 */
	public SimulatorControlPanel getSimulatorControlPanel() {
		return simulatorCtrl.getSimulatorControlPanel();
	}

	/**
	 * This method returns the StoreController.
	 * @return the StoreController.
	 */
	public StoreController getStoreController() {
		return storeCtrl;
	}

	/**
	 * This method returns the MachineryController&#46; 
	 * @return the MachineryController&#46;
	 */
	public MachineryController getMachineryController() {
		return machineryCtrl;
	}

	/**
	 * This method returns the MaintenanceController&#46;
	 * @return the MaintenanceController&#46;
	 */
	public MaintenanceController getMaintenanceController() {
		return maintenanceCtrl;
	}
	
	/**
	 * This method returns the TransactionController.
	 * @return the TransactionController.
	 */
	public TransactionController getTransactionController() {
		return txCtrl;
	}

	/**
	 * This method destroys all the object instances created for opening the vending
	 * machine&#46; It will instruct the SimulationController to close down (by closing
	 * down all the vending machine panels) and the TransactionController to close
	 * down&#46; It will also close down other control objects and the entity objects
	 * created for simulating the vending machine&#46;
	 */
	public void closeDown() {
		try {
			storeCtrl.closeDown();
		} catch (Exception e) {
			System.out.println("Error closing down the stores: " + e);
		}
		machineryCtrl.closeDown();
		maintenanceCtrl.closeDown();
		simulatorCtrl.closeDown();
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o.equals(maintenanceCtrl)) {
			// Get the state
			if (maintenanceCtrl.getMaintainerLoggedInState()) {
				// The Maintainer is logged in
				txCtrl.terminateTransaction();
			}else {
				// The Maintainer is logged out
				CustomerPanel custPanel= txCtrl.getCustomerPanel();
				if(custPanel==null){
					getSimulatorControlPanel().setActive(SimulatorControlPanel.ACT_CUSTOMER, true);
				}
				else{
					txCtrl.refreshCustomerPanel();
				}
			}
		}
	}
}//End of class MainController