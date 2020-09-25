package com.myideas.Server;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

public class ServerGUI {

	public static int port = 8080;
	private JFrame framServer;
	private JTextField txtPort;
	private JLabel lblStatus;
	private static TextArea txtMessage;
	public static JLabel lblUserOnline;
	Server server;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI window = new ServerGUI();
					window.framServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ServerGUI() {
		initialize();
	}

	public static String getLabelUserOnline() {
		return lblUserOnline.getText();
	}

	public static void updateMessage(String msg) {
		txtMessage.append(msg + "\n");
	}

	public static void updateNumberClient() {
		int number = Integer.parseInt(lblUserOnline.getText());
		lblUserOnline.setText(Integer.toString(number + 1));
	}

	public static void decreaseNumberClient() {
		int number = Integer.parseInt(lblUserOnline.getText());
		lblUserOnline.setText(Integer.toString(number - 1));

	}

	private void initialize() {
		framServer = new JFrame();
		framServer.setResizable(false);
		framServer.setAlwaysOnTop(true);
		framServer.getContentPane().setBackground(Color.GRAY);
		framServer.setForeground(UIManager.getColor("RadioButtonMenuItem.foreground"));
		framServer.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 13));
		framServer.getContentPane()
				.setForeground(UIManager.getColor("RadioButtonMenuItem.acceleratorSelectionForeground"));
		framServer.setTitle("ServerChat");
		framServer.setBounds(200, 200, 437, 686);
		framServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framServer.setBackground(Color.ORANGE);
		JLabel lblNewLabel = new JLabel("PORT");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

		txtPort = new JTextField();
		txtPort.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtPort.setEditable(false);
		txtPort.setColumns(10);
		txtPort.setText("8080");

		JButton btnStart = new JButton("START");
		btnStart.setBackground(UIManager.getColor("RadioButtonMenuItem.selectionBackground"));
		btnStart.setFont(new Font("SansSerif", Font.BOLD, 14));
		

		JLabel lblNhom = new JLabel("Server Management");
		lblNhom.setBackground(Color.WHITE);
		lblNhom.setFont(new Font("Segoe UI Black", Font.BOLD, 18));

		txtMessage = new TextArea();
		txtMessage.setBackground(Color.BLACK);
		txtMessage.setForeground(Color.GREEN);
		txtMessage.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtMessage.setEditable(false);

		JButton btnStop = new JButton("STOP");
		btnStop.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				lblUserOnline.setText("0");
				try {
					
					ServerGUI.updateMessage("Stop Server!");
					lblStatus.setText("OFF");
					server.stopServer();
				} catch (Exception e) {
					e.printStackTrace();
					ServerGUI.updateMessage("Stop Server!");
					lblStatus.setText("OFF");
				}
			}
		});

		JLabel lblnew111 = new JLabel("STATUS");
		lblnew111.setFont(new Font("SansSerif", Font.BOLD, 14));

		lblStatus = new JLabel("New label");
		lblStatus.setForeground(Color.ORANGE);
		lblStatus.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		lblStatus.setText("OFF");

		JLabel lblRecord = new JLabel("LOG");
		lblRecord.setFont(new Font("SansSerif", Font.BOLD, 14));

		JLabel lbllabelUserOnline = new JLabel("USER ONLINE");
		lbllabelUserOnline.setFont(new Font("SansSerif", Font.BOLD, 14));

		lblUserOnline = new JLabel("0");
		lblUserOnline.setForeground(Color.BLUE);
		lblUserOnline.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		GroupLayout groupLayout = new GroupLayout(framServer.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(7).addComponent(txtMessage,
						GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblRecord,
						GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblnew111, GroupLayout.PREFERRED_SIZE, 111,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lbllabelUserOnline))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblUserOnline, GroupLayout.PREFERRED_SIZE, 20,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 113, Short.MAX_VALUE))
										.addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txtPort, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING,
								groupLayout.createSequentialGroup().addGap(7)
										.addComponent(lblNhom, GroupLayout.PREFERRED_SIZE, 209,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnStop, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addGap(78)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(7)
				.addComponent(lblNhom, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE).addGap(11)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
								.addComponent(txtPort, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGap(23)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblnew111)
								.addComponent(lblStatus))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lbllabelUserOnline)
								.addComponent(lblUserOnline))
						.addGap(55).addComponent(lblRecord))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnStop, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtMessage, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE).addGap(31)));
		framServer.getContentPane().setLayout(groupLayout);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {lblStatus.setText("ON");
					server = new Server(8080);
					ServerGUI.updateMessage("Start Server ...");
					System.out.println("HEllo");
					server.operate();
					
				} catch (Exception e) {
					ServerGUI.updateMessage("Start Error !!!!");
					e.printStackTrace();
				}
			}
		});
		framServer.getContentPane().add(btnStart);
	}
}
