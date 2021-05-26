package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SvrSocket {

	// Lista para armazenar os clientes conectados
	private List<Socket> listaSockets = new ArrayList<Socket>();
	
	public void execute() {
		try {
			
			ServerSocket server = new ServerSocket(12345);
			
			while(true) {
				
				System.out.println("Aguardando conexão...");
				Socket client = server.accept();
				
				System.out.println("Conectou");
								
				// adiciona a conexão na lista
				this.listaSockets.add(client);
				
				// Cria a thread para lidar com o envio das mensagens entre clientes
				new Thread(new MessageThread(client, this)).start();
				
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendAll(Socket client, String message) {
	
		for(Socket clients: listaSockets) {
			try {
				
				if(clients != client) {
					PrintStream output = new PrintStream(clients.getOutputStream());
					output.println(message);
				}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
