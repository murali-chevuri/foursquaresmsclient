package com.apps.foursquare.client.sms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class test {
	
	public void parseString(String str){

		System.out.println("parsing enteted");
		String returnstr = "";
		String mobile = "";
		StringTokenizer st = new StringTokenizer(str,"OK");
		while(st.hasMoreTokens()){
			String token1 = st.nextToken();

			if(token1.indexOf("REC READ")!=-1){
				System.out.println("token1 is " + token1);
				StringTokenizer st1 = new StringTokenizer(token1,"REC READ");
				while(st1.hasMoreTokens()){
					String token2 = st1.nextToken();
					if(token2.indexOf("@")!=-1){
						System.out.println("token2 is " + token2);
						returnstr = "";
						mobile = "";
						
						StringTokenizer st2 = new StringTokenizer(token2,"@");
						while(st2.hasMoreTokens()){
							String token3 = st2.nextToken();
							System.out.println("token3 is "+token3);
							if(token3.indexOf("+91")!=-1){
								mobile = token3.substring(token3.indexOf("+91"), token3.indexOf("+91")+13);
						//		System.out.println("mobile number is "+mobile);
							}
							if(token3.indexOf('$')!=-1){
								returnstr = token3.substring(0, token3.indexOf('$'));
							//	System.out.println("substring is "+returnstr);
							}

							if(returnstr.length()>0){
								System.out.println("mobile is "+mobile);
								System.out.println("sub string is "+returnstr);
							}
						}
					}
				}
			}







//			if(token.indexOf('$')!=-1){
//			returnstr = "";
//			mobile = "";
//			StringTokenizer sttoken = new StringTokenizer(token,"$");
//			while(sttoken.hasMoreTokens()){

//			String tok = sttoken.nextToken();
//			System.out.println("token is "+tok.trim());
//			if(tok.indexOf("+91")!=-1){
//			mobile = tok.substring(23, 36);
//			//	System.out.println("mobile number is "+mobile);
//			}

//			if(tok.indexOf('@')!=-1){
//			returnstr = tok.substring(0, tok.indexOf('@'));
//			//	System.out.println("substring is "+returnstr);
//			}

//			if(returnstr.length()>0){
//			System.out.println("mobile is "+mobile);
//			System.out.println("sub string is "+returnstr);
//			}

//			}
//			}
		}
	}
	public static void main(String[] str){
		String str1 = "PM2M6M5M35M125M1234M1M56";
		String str2 = str1.substring(1);
		
		StringTokenizer st = new StringTokenizer(str2.toUpperCase(),"M");
		
		while(st.hasMoreTokens()){
			System.out.println("M"+st.nextToken());
		}
		
		System.out.println(str1);
		
		
		
	}
	
}
