package com.apps.foursquare.client.sms;

import java.util.Enumeration;

import javax.comm.CommPortIdentifier;

public class ListPorts {


	public static void main(String args[]) {
	Enumeration ports = CommPortIdentifier.getPortIdentifiers();
	while (ports.hasMoreElements()) {
	CommPortIdentifier port = (CommPortIdentifier)ports.nextElement();
	String type;
	switch (port.getPortType()) {
	case CommPortIdentifier.PORT_PARALLEL:
	type = "Parallel"; 
	break;
	case CommPortIdentifier.PORT_SERIAL:
	type = "Serial"; 
	break;
	default: /// Shouldn't happen
	type = "Unknown"; 
	break;
	}
	System.out.println(port.getName() + ": " + type+":"+port.isCurrentlyOwned());
	}
	}
}
