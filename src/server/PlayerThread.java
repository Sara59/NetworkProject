package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import se.lth.cs.ptdc.cardGames.Card;
import cards.CardPile;
import cards.CardPiles;

public class PlayerThread implements Runnable {
	private Socket connection;
	private boolean connectedToGame;
	private CardPiles piles;
	private CardPile takePile;
	private CardPile throwPile;
	private GameParticipants players;
	private String playerName;
	private boolean gameStarted;
	private boolean completeHand;
	private int playerIndex;

	public PlayerThread(Socket connection, CardPiles piles,
			GameParticipants players) {
		this.connection = connection;
		this.piles = piles;
		this.players = players;
	}

	@Override
	public void run() {
		try {
			System.out.println("out");
			ObjectOutputStream out = new ObjectOutputStream(
					connection.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			System.out.println("in");
			System.out.println("wait");
			System.out.println(in.readLine());
			System.out.println("hej");
			playerName = in.readLine();
			System.out.println(playerName);
			connectedToGame = players.add(playerName);
			while (connectedToGame) {
				
				int playerIndex = players.getIndex(playerName);
				takePile = piles.getPile(playerIndex);
				if (piles.length() < playerIndex) {
					throwPile = piles.getPile(playerIndex + 1);
				} else {
					throwPile = piles.getPile(0);
				}
				String inputStart = in.readLine();
				System.out.println(inputStart);
				while (inputStart != "StartGame") {
					inputStart = in.readLine();

				}
				gameStarted = true;
				Card[] cards = new Card[4];
				for (int i = 0; i < 4; i++) {
					cards[i] = piles.getStartCards();
				}
				out.writeObject(cards);

				while (gameStarted && players.size() > 3) {
					String input = in.readLine();
					if (input.equals("cardDrawn")) {
						if (takePile.size() > 0) {
							Card card = takePile.drawCard();
							out.writeObject(card);
						} else {
							out.writeObject(null);
						}
					} else if (input.equals("throwCard")) {
						// throwPile.addCard();

					} else if (input.equals("leaveGame")) {
						gameStarted = false;
						connectedToGame = false;
						players.removePlayer(playerName);

					} else if (input.equals("gotBubblan")) {
						// L�gg till en ny knapp
						// L�gg till en ruta med en counter
						// ++ p� sagda counter

					}

				}

			}
//			// connection.close();

		} catch (IOException e) {

//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} finally {
			try {
				// connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
