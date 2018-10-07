

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.*;


public class Animation4Thread extends JFrame{

		final int frameCount = 10;
    BufferedImage[] pics;
    int xloc = 100;
    int yloc = 100;
		int go = 1;
    final int xIncr = 1;
    final int yIncr = 1;
    final int picSize = 165;
    final int frameStartSize = 800;
    final int drawDelay = 30; //msec
		ImageIcon stopicon = createImageIcon("images/icons/stop.png");

    DrawPanel drawPanel = new DrawPanel();
		JFrame frame = new JFrame("Animation Controls");

    Action drawAction;

    public Animation4Thread() {
			JButton stop = new JButton(stopicon);
    	drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e){
					if(go == 1){
						drawPanel.repaint();
					}
    		}
    	};
			stop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					if(go == 1){
						go = 0;
					}
					else{
						go = 1;
					}
				}
			});
			frame.add(stop);
			frame.setSize(400,400);
			frame.setVisible(true);
    	add(drawPanel);
    	BufferedImage img = createImage();
    	pics = new BufferedImage[frameCount];//get this dynamically
    	for(int i = 0; i < frameCount; i++)
    		pics[i] = img.getSubimage(picSize*i, 0, picSize, picSize);

    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(frameStartSize, frameStartSize);
    	setVisible(true);
    	pack();
    }

    @SuppressWarnings("serial")
	private class DrawPanel extends JPanel {
    	int picNum = 0;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.gray);
	    	picNum = (picNum + 1) % frameCount;
	    	g.drawImage(pics[picNum], xloc+=xIncr, yloc+=yIncr, Color.gray, this);
		}

		public Dimension getPreferredSize() {
			return new Dimension(frameStartSize, frameStartSize);
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Animation4Thread a = new Animation4Thread();
				Timer t = new Timer(a.drawDelay, a.drawAction);
				t.start();
			}
		});
	}

    //Read image from file and return
    private BufferedImage createImage(){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_southeast.png"));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }

		private static ImageIcon createImageIcon(String path) {
			 java.net.URL imgURL = Animation4Thread.class.getResource(path);
			 if (imgURL != null) {
					return new ImageIcon(imgURL);
			 } else {
					System.err.println("Couldn't find file: " + path);
					return null;
			 }
		}
}
