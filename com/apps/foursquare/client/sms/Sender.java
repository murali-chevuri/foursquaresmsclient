package com.apps.foursquare.client.sms;
/*
 * 
 * @author : William Alexander
 *
*/
import java.util.Date;

public class Sender implements Runnable  {

  private static final long STANDARD=500;
  private static final long LONG=2000;
  private static final long  VERYLONG=20000;

  SerialConnection mySerial =null;

  static final private char cntrlZ=(char)26;
  String in, out;
  Thread aThread=null;
  private long delay=STANDARD;
  String recipient=null;
  String message=null;

 // private String csca="+6596845999"; // the message center
 // private String csca="+919840011003"; // the message center
  
 //private String csca="+919845087001";
 
 private String csca = "+919863002222"; //Reliance message center
  
//  private String csca="+919886005444";
  private SerialParameters defaultParameters= new SerialParameters ("COM13",9600,0,0,8,1,0);
  public int step;
  public int status=-1;
  public long messageNo=-1;
  public String all = "all";
  public String unread= "REC UNREAD";

  public Sender(String recipient, String message){

    this.recipient=recipient;
    this.message=message;

  }
  /**
   * connect to the port and start the dialogue thread
   */
  public int send () throws Exception{

	  System.out.println("run in send()...");
	  
	SerialParameters params = defaultParameters;

    mySerial =new SerialConnection (params);

    mySerial.openConnection();

    aThread=new Thread(this);

    aThread.start() ;
    //log("start");

    return 0;
  }

 
//  public void run(){
//	try{
//	  System.out.println("entered run");
//	 // String result=  mySerial.getIncommingString() ;
//	//  System.out.println("result before is "+result);
//	  System.out.println("port open is "+ mySerial.isOpen());
//	
//		
//		  mySerial.send("atz");
//		  mySerial.send("ath0");
//		  mySerial.send("at+cmgf=1");
//		  mySerial.send("at+csca=\""+csca+"\"");
//		  
//		  while(true){	 	
//		 mySerial.send("at+cmgl=\"rec read\"");
//	//	  mySerial.send("at+cmgr=30");
//		  aThread.sleep(100000);
//	  }
//	}catch(InterruptedException ie){
//		
//	}
//	//  result = mySerial.getIncommingString() ;
//	 // System.out.println("result after is "+result.toString());
//	  
//  }
  /**
   * implement the dialogue thread,
   * message / response via steps,
   * handle time out
   */

  public void run(){

  System.out.println("run in sender...");
	boolean timeOut=false;
    long startTime=(new Date()).getTime();



    while ((step <7) && (!timeOut)){
//      log(""+((new Date()).getTime() - startTime);
      //check where we are in specified delay
      timeOut=((new Date()).getTime() - startTime)>delay;

      //if atz does not work, type to send cntrlZ and retry, in case a message was stuck
      if (timeOut && (step==1)) {
          step=-1;
          mySerial.send(""+cntrlZ);
      }

      //read incoming string
      String result=  mySerial.getIncommingString() ;
      System.out.println("result5 is "+result.toString());

//    log ("<- "+result+"\n--------");
      int expectedResult=-1;

      try{
        //log ("Step:"+step);
    	  System.out.println("port status is "+ mySerial.isOpen());
       
    	  System.out.println("in step " + step);
    	  switch (step){
          case 0:

            mySerial.send("atz");
            delay=LONG;
            startTime=(new Date()).getTime();
            
            break;

            
           
          case 1:
            delay=STANDARD;
            mySerial.send("ath0");
            startTime=(new Date()).getTime();
            
            break;
          case 2:
            expectedResult=result.indexOf("OK");

            System.out.println("expected result is "+expectedResult);
            //log ("received ok ="+expectedResult);
            if (expectedResult>-1){
              mySerial.send("at+cmgf=1");
              startTime=(new Date()).getTime();
            }else{
                step=step-1;
            }
            
            break;
          case 3:
            expectedResult=result.indexOf("OK");

           // log ("received ok ="+expectedResult);
            if (expectedResult>-1){
              mySerial.send("at+csca=\""+csca+"\"");
              startTime=(new Date()).getTime();
            }else{
              step=step-1;
            }
            
            break;
          case 4:
            expectedResult=result.indexOf("OK");

           // log ("received ok ="+expectedResult);
            if (expectedResult>-1){
            	mySerial.send("at+cmgs=\""+recipient+"\"");
          //   mySerial.send("at+cmgr=5");
              startTime=(new Date()).getTime();
            }else{
              step=step-1;
            }

            break;
          case 5:
        	  System.out.println("result is "+result.toString()); 
        	  expectedResult=result.indexOf(">");

           // log ("received ok ="+expectedResult);
            if (expectedResult>-1){
              mySerial.send(message+cntrlZ);
              startTime=(new Date()).getTime();
            }else{
              step=step-1;
            }
            delay=VERYLONG;//waitning for message ack

            break;

          case 6:
            expectedResult=result.indexOf("OK");
            //read message number
            if (expectedResult>-1){
              int n=result.indexOf("CMGS:");
              result=result.substring(n+5);
              n=result.indexOf("\n");
              status=0;
              messageNo=Long.parseLong(result.substring(0,n).trim() );

              log ("sent message no:"+messageNo);


            }else{
              step=step-1;
            }

          break;
        }
        
    	  step=step+1;
        aThread.sleep(100);

      }catch (Exception e){
          e.printStackTrace();
      }
    }

    mySerial.closeConnection() ;

    //if timed out set status

    if (timeOut ) {
        status=-2;
        log("*** time out at step "+step+"***");
    }
  }
/**
 * logging function, includes date and class name
 */
  private void log(String s){
    System.out.println (new java.util.Date()+":"+this.getClass().getName()+":"+s);
  }
}