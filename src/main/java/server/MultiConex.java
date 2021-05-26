package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class MultiConex implements Runnable {

	private Socket client;

	public MultiConex(Socket client) {
		this.client = client;
	}

	public void run() {
				
		try {
		
			Scanner input = new Scanner(client.getInputStream());
			
			PrintStream output = new PrintStream(client.getOutputStream());
			
			String recebido = " ";
			
			while(!recebido.toUpperCase().equals("Sair")) {
				recebido = input.nextLine();
				System.out.println(recebido);
			}
			
			client.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
