package com.itliangzi.ssl_socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLServerSocket;


/**
 * @author GuoYuLiang
 *
 * 2019年6月1日
 */
public class ServerApp {

	/**
	 * 2019年6月1日
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		SSLServerSocket serverSocket;
		try {
			serverSocket = (SSLServerSocket) MySSLServerSoketFactory.sslServerSocketFactory.createServerSocket(6993);
			serverSocket.setNeedClientAuth(true);
			serverSocket.setEnabledProtocols(new String[] {"TLSv1", "TLSv1.1", "TLSv1.2"});
			while(true) {
				System.out.println("start to accept:");
				Socket sokcet = serverSocket.accept();
				System.out.println("a socket accepted");
				new Thread() {
					public void run() {
						try {
							OutputStream outputStream = sokcet.getOutputStream();
							for(;;) {
								String out = "timestamp from server:"+System.currentTimeMillis()+"\r\n";
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
				
				InputStream inputStream = sokcet.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				for(;;) {
					System.out.println(bufferedReader.readLine());
				}
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
