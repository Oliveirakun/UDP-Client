package br.com.nocompany.main;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import br.com.nocompany.beans.UDPClient;
import br.com.nocompany.interfaces.Client;
import br.com.nocompany.interfaces.Server;

public class ClientMain {

	public static void main(String[] args){
		
		try {
			
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket messagePacket = new DatagramPacket(new byte[256],256);
			DatagramPacket receivePacket = new DatagramPacket(new byte[256],256);
			String textMessage = "hello";
			
			Client client = new UDPClient(socket, messagePacket, textMessage, receivePacket);
			Server remoteServer = client.findServer();
			
			if(remoteServer != null)
				System.out.println("Remote server found:"+remoteServer.getName()+" remote server ip:"+remoteServer.getIp());
			else
				System.out.println("No one server found in the network");
			
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
}
