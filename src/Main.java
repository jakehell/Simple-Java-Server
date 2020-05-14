import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	// initialize socket and input stream
	private static Socket socket = null;
	private static ServerSocket server = null;
	private static DataInputStream in = null;

	// constructor with port
	private static class Server {
		public Server(InetSocketAddress address) {
			// starts server and waits for a connection
			try {
				server = new ServerSocket(address.getPort(), 5, address.getAddress());
				System.out.println("Server started");

				System.out.println("Waiting for a client ...");

				socket = server.accept();
				System.out.println("Client accepted");

				// takes input from the client socket
				in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

				String line = "";

				// reads message from client until "Over" is sent
				while (!line.equals("Over")) {
					try {
						line = in.readUTF();
						System.out.println(line);

					} catch (IOException i) {
						System.out.println(i);
					}
				}
				System.out.println("Closing connection");

				// close connection
				socket.close();
				in.close();
			} catch (IOException i) {
				System.out.println(i);
			}
		}
	}

	public static void main(String args[]) {
		Server server = new Server(new InetSocketAddress(<IP>, <PORT>));
	}
}
