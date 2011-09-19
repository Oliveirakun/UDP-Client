package br.com.nocompany.beans;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import br.com.nocompany.interfaces.Client;
import br.com.nocompany.interfaces.Server;

public class UDPClient implements Client {

	private DatagramSocket socket;
	private DatagramPacket messagePacket;
	private String textMessage;
	private DatagramPacket receivePacket;
	
	public UDPClient(DatagramSocket socket,DatagramPacket messagePacket,String textMessage,DatagramPacket receivePacket) {
		
		this.socket = socket;
		this.messagePacket = messagePacket;
		this.textMessage = textMessage;
		this.receivePacket = receivePacket;
	}
	
	@Override
	public Server findServer() {

		try {
			
			//Send message to the broadcast address
			InetAddress ipAddress = InetAddress.getByName("255.255.255.255");
			messagePacket.setAddress(ipAddress);
			messagePacket.setData(textMessage.getBytes());
			messagePacket.setLength(textMessage.length());
			messagePacket.setPort(5100);
			
			socket.send(messagePacket);
				
			socket.setSoTimeout(3000);
			
			socket.receive(receivePacket);
			String responseMessage = new String(receivePacket.getData()).trim();
														     
			
			Server remoteServer = new RemoteServer(responseMessage, receivePacket.getAddress()
																				 .toString()
																				 .substring(1));
			
			return remoteServer;
			
			
		}
		catch(SocketTimeoutException e) {
			System.out.println("Time expired");			
		}	
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	
}
