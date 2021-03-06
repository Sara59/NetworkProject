package client;

import gui.GameMonitor;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameClient {
	private static final String HOST_NAME = "localhost";

	public static void main(String[] args) {
		try {
			Socket socket = new Socket(HOST_NAME, 3000);
			ClientHandler handler = new ClientHandler();
			@SuppressWarnings("unused")
			GameMonitor gameMonitor = new GameMonitor(handler, socket);
			ReaderThread thread = new ReaderThread(gameMonitor);
			thread.run();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
