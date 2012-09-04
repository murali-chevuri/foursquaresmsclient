package com.apps.foursquare.client.sms;






public class SMSTest {

	public static void main(String[] args){
//		
		SMSClient sc = new SMSClient(1);
		int i = sc.sendMessage("+919900250755","Hello world....");
		
	//	sc.sendMessage("09500182065","Hello world");
//		
		
//		CommPortIdentifier portId = null;
//		try {
//	           // System.out.println(parameters.getPortName());
//			portId = CommPortIdentifier.getPortIdentifier("COM10");
//			System.out.println("success");
//		} catch (NoSuchPortException e) {
//	           // System.out.println("Yes the problem is here 1 ");
//	            e.printStackTrace();
//		   // throw new SerialConnectionException(e.getMessage());
//		}catch(Exception e)
//	        {
//	          //  System.out.println("ErrorErrorErrorError");
//	            e.printStackTrace();
//	        }
//		
//		
//		try {
//			SerialPort sPort = (SerialPort)portId.open("SMSConnector", 3000000);
//			System.out.println("success.... open port "+ sPort.getOutputStream());
//		} catch (PortInUseException e) {
//	       System.out.println("open exception");     
//		   
//		}catch(Exception e){
//			
//		}
//		
	}
}
	

