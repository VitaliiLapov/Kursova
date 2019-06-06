package server.rmi;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to RMI server, have a good day! Press ENTER to shutdown.");

		try (ComputeEngine server = new ComputeEngine(args)) {
			server.run();
			System.in.read();
		}
		
		System.out.println("The server was shut down.");
	}
}
