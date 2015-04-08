package window;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import mibBrowser.MibFileChooser;
import mibBrowser.MibOperations;
import net.percederberg.mibble.Mib;
import net.percederberg.mibble.MibLoaderException;

import java.awt.Color;



public class MainClass {
	private File mibFile=null;
	private JFrame frame=null;
	private Mib mibObject=null;
	HashMap oidsMap =null;
	private JTextField mibNameTextField;
	private JTextField ipTextField;
	private JTextField commTextField;
	private JTextField oidTextField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainClass window = new MainClass();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MainClass() throws IOException  {
		initialize();
	}

	
	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1330, 811);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 1316, 759);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTheMibsOids = new JLabel("The Mibs OIDS :");
		lblTheMibsOids.setBounds(12, 113, 129, 15);
		panel.add(lblTheMibsOids);
		
		final JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(12, 224, 735, 535);
		panel.add(textArea);
		JLabel lblOidDescryption = new JLabel("OID Descryption :");
		lblOidDescryption.setBounds(12, 187, 129, 15);
		panel.add(lblOidDescryption);
		
		JLabel MibTitleLabel = new JLabel("Selected Mib Name :");
		MibTitleLabel.setBounds(12, 37, 149, 15);
		panel.add(MibTitleLabel);
		
		mibNameTextField = new JTextField();
		mibNameTextField.setEditable(false);
		mibNameTextField.setBounds(12, 65, 735, 35);
		
		panel.add(mibNameTextField);
		mibNameTextField.setColumns(10);
		
		JLabel lblIpAdressOf = new JLabel("IP Adress Of the Target Machine :");
		lblIpAdressOf.setBounds(799, 37, 272, 15);
		panel.add(lblIpAdressOf);
		
		ipTextField = new JTextField();
		ipTextField.setText("192.168.1.1");
		ipTextField.setBounds(799, 65, 485, 35);
		panel.add(ipTextField);
		ipTextField.setColumns(10);
		
		JLabel lblCommun = new JLabel("community :");
		lblCommun.setBounds(799, 113, 242, 15);
		panel.add(lblCommun);
		
		commTextField = new JTextField();
		commTextField.setText("public");
		commTextField.setBounds(799, 140, 485, 35);
		panel.add(commTextField);
		commTextField.setColumns(10);
		
		JLabel lblOid = new JLabel("OID :");
		lblOid.setBounds(799, 187, 70, 15);
		panel.add(lblOid);
		
		oidTextField = new JTextField();
		oidTextField.setBounds(799, 222, 485, 35);
		panel.add(oidTextField);
		oidTextField.setColumns(10);
		
		final JTextArea textArea_1 = new JTextArea();
		textArea_1.setWrapStyleWord(true);
		textArea_1.setLineWrap(true);
		textArea_1.setBackground(new Color(102, 205, 170));
		textArea_1.setBounds(799, 315, 493, 406);
		panel.add(textArea_1);
		
		JLabel lblTheResponseFrom = new JLabel("The Response from the Server :");
		lblTheResponseFrom.setBounds(799, 284, 242, 15);
		panel.add(lblTheResponseFrom);
		
		JButton btnSendGetRequest = new JButton("SEND GET REQUEST");
		btnSendGetRequest.setBounds(799, 733, 233, 25);
		panel.add(btnSendGetRequest);
		
		final JComboBox comboBox = new JComboBox();
		
		comboBox.setBounds(12, 140, 735, 35);
		panel.add(comboBox);
		
		JButton btnSendGetnextRequest = new JButton("SEND GET-NEXT REQUEST");
		btnSendGetnextRequest.setBounds(1059, 733, 233, 25);
		panel.add(btnSendGetnextRequest);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		JMenuItem loadMibItem = new JMenuItem("load Mib File");
		menuFile.add(loadMibItem);
		
		JMenuItem mntmAPropos = new JMenuItem("about");
		menuFile.add(mntmAPropos);
		
		
		// The Input Listeners
		loadMibItem.addActionListener(new ActionListener() {
			@SuppressWarnings("rawtypes")
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					MibOperations operation = new MibOperations();
					mibFile =MibFileChooser.getFile();
					mibObject =null;
					try {
						mibObject=operation.loadMib(mibFile);
					} catch (MibLoaderException e) {
						textArea.setText(e.getLocalizedMessage()); // The exception will be printed whatever it is ;)
					} catch (IOException e) {
						textArea.setText(e.getLocalizedMessage()); // The exception will be printed whatever it is ;)
					}
					
					oidsMap= operation.extractOids(mibObject);
					mibNameTextField.setText(mibObject.getName());
					comboBox.setModel(new DefaultComboBoxModel(oidsMap.keySet().toArray()));
					
				} catch (Exception e) {
					textArea.setText(e.getLocalizedMessage()); // The exception will be printed whatever it is ;)
					
				}
			}
		});
		
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String selectedItemInComboBox = (String)comboBox.getSelectedItem();
					textArea.setText(mibObject.getSymbol(selectedItemInComboBox).toString());
					
					oidTextField.setText((String) oidsMap.get(selectedItemInComboBox).toString());
					
				} catch (Exception e) {
					textArea.setText(e.getLocalizedMessage()); // The exception will be printed whatever it is ;)
					
				}
			}
		});
		
		
		btnSendGetRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String go = Request.getRequest(ipTextField.getText().toString(),
							commTextField.getText().toString(), oidTextField
									.getText().toString());

					textArea_1.setText(go);
				} catch (Exception e) {
					textArea_1.setText(e.getLocalizedMessage()); // The exception will be printed whatever it is ;)
					
				}
			}
		});
		
		btnSendGetnextRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String go = Request.getNextRequest(ipTextField.getText().toString(),
							commTextField.getText().toString(), oidTextField
									.getText().toString());

					textArea_1.setText(go);
					oidTextField.setText(Request.oid);
				} catch (Exception e) {
					textArea_1.setText(e.getLocalizedMessage()); // The exception will be printed whatever it is ;)
					
				}
			}
		});
		
		mntmAPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg ="To load Mib Files please select a valid MIB file from the folder mibs.\n\n"
						+ "Project Technologies Internet By Mohammed Akram MECHERI & Thibaud DIARD\n\n"
						+"please visit mecheri.altervista.com     Creteil University 2015.";
				JOptionPane.showMessageDialog(null, msg);
			}
		});
		
	}
}
