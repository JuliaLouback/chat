package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import server.MultiConex;

public class ClientSocket {

	public void execute() {
				
		try {
			Socket client = new Socket("localhost", 12345);
			
			Scanner teclado = new Scanner(System.in);
			PrintStream output = new PrintStream(client.getOutputStream());
			
			System.out.println("Conexão efeutada!");
			
			Scanner input = new Scanner(client.getInputStream());
		
			// THREAD
			new Thread(new MultiConex(client)).start();	
			
			String mensagem = "";
			
			while(!mensagem.toUpperCase().equals("SAIR") && !mensagem.toUpperCase().equals("FECHAR")) {
				mensagem = teclado.nextLine();
				output.println(mensagem);				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
