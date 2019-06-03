package com.itliangzi.ssl_socket;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import com.itliangzi.ssl_socket.MySSLServerSoketFactory.ServerCertConfig;

/**
 * @author GuoYuLiang
 *
 * 2019年6月1日
 */
public class MySSLClientSoketFactory {
	interface  CertConfig{
		String PRIVATE_KEYSTORE_FILE = "/certs/client/client_private.jks";
		String TRUSTED_KEYSTORE_FILE = "/certs/client/client_trusted.jks";
		String PRIVATE_KEYSTORE_STOREPASSWORD = "client123";
		String TRUSTED_KEYSTORE_STOREPASSWORD = "tclient123";
		String CERT_STOREPASSWORD = "ccert123";
	}
	private static SSLContext sslContext;
	public static SSLSocketFactory sslSocketFactory;
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
		   InputStream privateKeyInputstream = MySSLServerSoketFactory.class.getResourceAsStream(CertConfig.PRIVATE_KEYSTORE_FILE);
		   InputStream trustedKeyInputstrem = MySSLServerSoketFactory.class.getResourceAsStream(CertConfig.TRUSTED_KEYSTORE_FILE);		 
		   ks.load(privateKeyInputstream, CertConfig.PRIVATE_KEYSTORE_STOREPASSWORD.toCharArray());
		   tks.load(trustedKeyInputstrem, CertConfig.TRUSTED_KEYSTORE_STOREPASSWORD.toCharArray());
		   kmf.init(ks, CertConfig.CERT_STOREPASSWORD.toCharArray());
		   tmf.init(tks);
		   ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		   sslContext = ctx;
		   sslSocketFactory = ctx.getSocketFactory();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }
	public SSLSocket listen(String host,int port) {
		return null;
	}
}
