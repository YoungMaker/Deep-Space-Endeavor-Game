package mech.Exception;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;

import main.Game;
import main.GameStates;
import mech.GameSys;


//TODO: add a Swing GUI for slick exceptions
@SuppressWarnings("serial")
public class SwingExceptionManager extends JFrame  
							implements ActionListener {
	JTextArea textArea = new JTextArea();
	JLabel error = new JLabel("Error Details:", JLabel.CENTER);
	JLabel svd = new JLabel("", JLabel.LEFT);
	JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
	JButton save = new JButton("Save");
	JFileChooser fc;
	private static JFrame win = null;
	
	public static void throwNewException(Exception e) {
		 SwingExceptionManager.throwNewException(e, "");
	}
	
	public static void throwNewException(Exception e, String errorName) {
		GameStates.killGameWindow();
		win = new SwingExceptionManager(e, errorName);
	}
	
	public SwingExceptionManager(Exception e, String ErrorName) {
		//TITLE:
		super("DSE Error Catcher");
        //TEXT AREA 
        textArea.setSize(e.getStackTrace().length+4,50);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
        textArea.setText(GameSys.getVersionInformation() + '\n');
		if(!ErrorName.equals("")) {
			textArea.append(e.toString() + '\n' + ErrorName + '\n');
		}
		else {
			textArea.append(e.toString());
		}
		for(int i = 0; i < e.getStackTrace().length; i++) {
			StackTraceElement[] stackTrace = e.getStackTrace();
			textArea.append(stackTrace[i].toString());
			textArea.append("" + '\n' + '\t');
		}
		//BUTTON PANEL
		save.addActionListener(this);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(save);
		buttonPanel.add(svd);
		//FILE CHOOSER:
		fc = new JFileChooser(System.getProperty("user.dir"));
		
		
		//FRAME WORK
		Container content = this.getContentPane();
        content.setLayout(new BorderLayout());
        content.add(error,  BorderLayout.PAGE_START);
        content.add(buttonPanel, BorderLayout.PAGE_END);
        content.add(scrollPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
     //   this.addWindowListener(new WindowEventManager());
        this.setVisible(true);
		
		//ERROR DIALOG
//		JOptionPane.showMessageDialog(this,
//		    "DSE Has encountered an error " + ErrorName + '\n' + e.getMessage(),
//		    "DSE ERROR",
//		    JOptionPane.ERROR_MESSAGE);
		//dump string
		Game.dumpString();
	}
	
	public static void destroyFrame() {
		win.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == save) {
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if(svd.getText().equalsIgnoreCase("")) {
				if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
						try {
							SaveData(fc.getSelectedFile().getAbsolutePath());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
				}
			}
			else  {
				//svd.setText("Already saved.");
			}
		}
		
	}

	private void SaveData(String absolutePath) throws IOException {
		//TODO: SAVE ERROR TO THE SELECTED FILE LOCATION!
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
		Calendar cal = Calendar.getInstance();
		File f = new File(absolutePath + System.getProperty("file.separator") +  "Err_" + dateFormat.format(cal.getTime()) + ".txt");
		if(!f.exists()) {
			f.createNewFile();
		}
		else {
			//svd.setText("Already saved.");
			return;
		}
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(textArea.getText().replaceAll("" +'\n', "" + System.getProperty("line.separator")));
			output.close();
			svd.setText("Saved.");
			save.setEnabled(false);
	}

}

//class WindowEventManager extends WindowAdapter {
//	  public void windowClosing(WindowEvent evt) {
//		  SwingExceptionManager.destroyFrame();
//	  }
//	}
