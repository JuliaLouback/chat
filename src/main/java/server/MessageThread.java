package server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MessageThread implements Runnable {

	private SvrSocket srvSocket;
	private Socket client;

	public MessageThread(Socket client, SvrSocket svrSocket) {
		this.client = client;
		this.srvSocket = svrSocket;
	}

	public void run() {
		
		try {
			Scanner input = new Scanner(client.getInputStream());
			
			while(input.hasNextLine()) {
				this.srvSocket.sendAll(this.client, input.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
