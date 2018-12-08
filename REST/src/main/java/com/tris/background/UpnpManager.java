package com.tris.background;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class UpnpManager extends Thread implements ServletContextListener {
	
	@Override
	public void run() {
		try {
			SearchListener search = new SearchListener();
			search.start();
			
			Announcer announcer = new Announcer();
			announcer.start();
			
			TimeUnit.SECONDS.sleep(120);
			System.out.println("Kicking off shutdown");
			announcer.shutdown();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		this.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		this.stop();
	}

}