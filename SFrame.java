package be.lioche.skype.bot;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SFrame extends JFrame{

	private static final long serialVersionUID = -1528154601247961790L;
	
	JLabel out = new JLabel("Appuyez sur le bouton pour lancer le bot");
	JButton start = new JButton("Lancer le bot");
	JButton stop = new JButton("Arrêter le bot");
	
	public SFrame(String title, int x, int z, boolean resizable, JPanel pane){
		setTitle(title);
		setSize(x, z);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(resizable);
		
		start.addActionListener(new AListeners());
		stop.addActionListener(new AListeners());
		
		pane.add(out);
		pane.add(start);
		setContentPane(pane);
	}
	
	public void showText(String msg){
		out.setText(msg);
	}
	
	public void showStop(){
		SBot.jp.remove(start);
		SBot.jp.add(stop);
		setContentPane(SBot.jp);
	}
	public void showStart(){
		SBot.jp.remove(stop);
		SBot.jp.add(start);
		setContentPane(SBot.jp);
	}
	
	
}
