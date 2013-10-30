package com.eim.util.ejb;

import java.rmi.RemoteException;
import java.util.Observable;

import javax.ejb.EJBException;


public class ObservableSessionBean extends Observable{

    private static final long serialVersionUID = 4457935896829351961L;

	public ObservableSessionBean() {
		super();
	}

	public void ejbActivate() throws EJBException, RemoteException {
		notifyObservers(SessionEJBEvent.ACTIVATE);
	}

	public void ejbPassivate() throws EJBException, RemoteException {
		notifyObservers(SessionEJBEvent.PASSIVATE);
	}

	public void ejbRemove() throws EJBException, RemoteException {
		notifyObservers(SessionEJBEvent.REMOVE);
	}

}
