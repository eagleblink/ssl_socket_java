package com.itliangzi.ssl_socket;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

/**
 * @author GuoYuLiang
 *
 * 2019年6月1日
 */
public class MySSLServerSoketFactory {
	interface  ServerCertConfig{
		String PRIVATE_KEYSTORE_FILE = "/certs/server/server_private.jks";
		String TRUSTED_KEYSTORE_FILE = "/certs/server/server_trusted.jks";
		String PRIVATE_KEYSTORE_STOREPASSWORD = "server123";
		String TRUSTED_KEYSTORE_STOREPASSWORD = "tserver123";
		String CERT_STOREPASSWORD = "scert123";
	}
	private static SSLContext sslContext;
	public static SSLServerSocketFactory sslServerSocketFactory;
	static {
		init();
	}
	public static void init() {
		  try {
		   SSLContext ctx = SSLContext.getInstance("SSL");
		   KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		   TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
		   KeyStore ks = KeyStore.getInstance("JKS");
		   KeyStore tks = KeyStore.getInstance("JKS");
		   InputStream privateKeyInputstream = MySSLServerSoketFactory.class.getResourceAsStream(ServerCertConfig.PRIVATE_KEYSTORE_FILE);
		   InputStream trustedKeyInputstrem = MySSLServerSoketFactory.class.getResourceAsStream(ServerCertConfig.TRUSTED_KEYSTORE_FILE);
		   ks.load(privateKeyInputstream, ServerCertConfig.PRIVATE_KEYSTORE_STOREPASSWORD.toCharArray());
		   tks.load(trustedKeyInputstrem, ServerCertConfig.TRUSTED_KEYSTORE_STOREPASSWORD.toCharArray());
		   kmf.init(ks,ServerCertConfig.CERT_STOREPASSWORD.toCharArray());
		   tmf.init(tks);
		   ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		   sslContext = ctx;
		   sslServerSocketFactory = ctx.getServerSocketFactory();
		   String[] ciString = sslServerSocketFactory.getSupportedCipherSuites();
		   for(String ci:ciString) {
			   System.err.println(ci);
		   }
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }
	public SSLSocket listen(String host,int port) {
		return null;
	}
}
