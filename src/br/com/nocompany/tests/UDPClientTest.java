package br.com.nocompany.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import junit.framework.Assert;

import org.junit.Test;

import br.com.nocompany.beans.UDPClient;
import br.com.nocompany.interfaces.Client;
import br.com.nocompany.interfaces.Server;


public class UDPClientTest {

	@Test
	public void test() throws IOException {
		
		DatagramSocket socketMock = mock(DatagramSocket.class);
		DatagramPacket packet = new DatagramPacket(new byte[256],256);
		DatagramPacket responsePacket = new DatagramPacket(new byte[256],256);
		responsePacket.setAddress(InetAddress.getByName("127.0.0.1"));
		responsePacket.setData(new String("Teste").getBytes());
		
					
		Client client = new UDPClient(socketMock, packet, "hello",responsePacket);
		Server remoteServer = client.findServer();
		
		verify(socketMock).send(packet);
		verify(socketMock).receive(responsePacket);
		Assert.assertEquals("Teste", remoteServer.getName());
		Assert.assertEquals("127.0.0.1",remoteServer.getIp());
		
	}

}
