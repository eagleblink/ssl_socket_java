package com.itliangzi.ssl_socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocket;

import groovy.util.logging.Log;

/**
 * @author GuoYuLiang
 *
 * 2019年6月1日
 */
public class ClientApp {

	/**
	 * 2019年6月1日
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		SSLSocket sslSocket = (SSLSocket) MySSLClientSoketFactory.sslSocketFactory.createSocket("192.168.50.99", 6993);
		sslSocket.setEnabledProtocols(new String[] {"TLSv1"});
		new Thread() {
			public void run() {
				try {
					OutputStream outputStream = sslSocket.getOutputStream();
					for(;;) {
						String out = "timestamp from client:"+System.currentTimeMillis()+"\r\n";
						outputStream.write(out.getBytes());
						TimeUnit.SECONDS.sleep(1);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			};
		}.start();
		
		InputStream inputStream = sslSocket.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		for(;;) {
			String line = bufferedReader.readLine();
			System.out.println(line);
		}
	}
	}
