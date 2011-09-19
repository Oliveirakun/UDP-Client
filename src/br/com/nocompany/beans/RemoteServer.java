package br.com.nocompany.beans;

import br.com.nocompany.interfaces.Server;

public class RemoteServer implements Server {

	private String name;
	private String ip;
	
	public RemoteServer(String name,String ip){
		
		this.name = name;
		this.ip = ip;
		
	}
	
	
	@Override
	public String getName() {		
		return name;
	}

	@Override
	public String getIp() {		
		return ip;
	}

}
