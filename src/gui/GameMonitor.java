package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.CardHand;
import client.CardString;
import client.ClientHandler;
import client.ClientSender;

public class GameMonitor {
	protected JLabel message;

	public GameMonitor(ClientHandler handler, CardHand cardHand, Socket socket) {
		JFrame frame = new JFrame("Bubblan");
		frame.setName("Bubblan");
		frame.setBounds(500, 100, 450, 300);
		String name = JOptionPane.showInputDialog("Select username");
		if (name != null) {
			handler.setPlayerName(name);
			ClientSender sender = new ClientSender(socket);
			message = new JLabel("yes");
			ButtonAndMessagePanel bottomPanel = new ButtonAndMessagePanel(this, sender);
			UpperPanel upperPanel = new UpperPanel(this, sender);
			CardString[][] field = new CardString[1][5];
			CardGrid middlePanel = new CardGrid(1, 5, field);
			frame.setLayout(new BorderLayout());
			frame.add(upperPanel, BorderLayout.NORTH);
			frame.add(bottomPanel, BorderLayout.SOUTH);
			frame.add(middlePanel, BorderLayout.CENTER);

			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(true);
			frame.setVisible(true);
		}
	}

}
