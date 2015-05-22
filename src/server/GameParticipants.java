package server;

import java.net.InetAddress;
import java.util.ArrayList;

/* 
 * Lista med namn på deltagarna. Beroende på vart i listan namnet på en deltagare ligger får de ett index.
 */
public class GameParticipants {
	private ArrayList<Player> participants;

	public GameParticipants() {
		participants = new ArrayList<Player>();
	}

	public boolean add(Player name) {
		if (participants.size() < 4) {
			participants.add(name);
			return true;
		}
		return false;
	}

	public int getIndex(Player playerName) {
		return participants.indexOf(playerName);
	}

	public void removePlayer(Player playerName) {
		if (participants.contains(playerName)) {
			participants.remove(playerName);
		}
	}

	public int size() {
		return participants.size();
	}
}
