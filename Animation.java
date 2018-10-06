//T Harvey
//based loosely on http://www.java2s.com/Code/JavaAPI/java.awt/GraphicsdrawImageImageimgintxintyImageObserverob.htm
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animation extends JPanel {
	//must adjust animation and frameCount variables for each
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	final String animation="forward";//type animation being used
    final int frameCount = 10;
    int imagecount=8;//number of images being used
    int picNum = 0;//subimage being used
    int imagenum = 0;//image being used
    
    BufferedImage[][] pics;
    int xloc = 0;
    int yloc = 0;
    final int xIncr = 8;
    final int yIncr = 2;
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    boolean Xpositive = true;
    boolean Ypositive = true;

    //Override this JPanel's paint method to cycle through picture array and draw images
    public void paint(Graphics g) {
    	picNum = (picNum + 1) % frameCount;
    	
    	//Determines whether the animation is traveling along the +X_axis.
    	if(xloc<0-imgWidth/4) { Xpositive = true; }
    	if(xloc>=frameWidth-imgWidth) { Xpositive = false; }
    	//Determines whether the animation is traveling along the +Y_axis
		if(yloc<0-imgWidth/4) { Ypositive = true; }
		if(yloc>=frameHeight-imgHeight) { Ypositive = false;}

		//Determines whether to increase or decrease the xloc of the animation
    	if(Xpositive) { xloc+=xIncr; }
    	else { xloc-=xIncr; }
		//Determines whether to increase or decrease the yloc of the animation
    	if(Ypositive) { yloc+=yIncr; }
    	else { yloc-=yIncr; }
    	//Changes the image such that the animation heads the correct direction
    	if(Xpositive && Ypositive) { imagenum=0; }
    	if(Xpositive && !Ypositive) { imagenum=6; }
    	if(!Xpositive && Ypositive) { imagenum=2; }
    	if(!Xpositive && !Ypositive) { imagenum=4; }
    	
    	g.drawImage(pics[imagenum][picNum], xloc, yloc, Color.gray, this);
    	
    	// TODO: Keep the orc from walking off-screen, turn around when bouncing off walls.
		//Be sure that animation picture direction matches what is happening on screen.
    }

    //Make frame, loop on repaint and wait
    public static void main(String[] args) {
    	JFrame frame = new JFrame();
    	////////////////NEW/CHANGED STUFF
    	Container pane = frame.getContentPane();
    	pane.setLayout(new BorderLayout());
    	pane.add(new Animation(), BorderLayout.CENTER);
    	pane.setBackground(Color.gray);
    	///////////////END NEW/CHANGED STUFF
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	///////////////////////////NEW STUFF
		JPanel panel = new JPanel();
		JButton b1 = new JButton("Start/Stop");
		b1.addActionListener(new ActionListener() {
			//Action Listeners will automatically call action Performed method when the button is pushed
			public void actionPerformed(ActionEvent e) {
				// ADD CODE FOR START/STOP BUTTON HERE
				System.out.println("Start/Stop Button Working");
			}
		});
		JButton b2 = new JButton("Change direction");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ADD CODE FOR CHANGE DIRECTION BUTTON HERE
				System.out.println("Change Direction Button Working");
			}
		});
		panel.add(b1);
		panel.add(b2);
		pane.add(panel, BorderLayout.PAGE_START);
		/////////////////////////END NEW STUFF
    	frame.setVisible(true);
    	for(int i = 0; i < 1000; i++){
    		frame.repaint();
    		try {
    			Thread.sleep(100);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }

    //Constructor: get image, segment and store in array
    public Animation(){
    	BufferedImage[] img = createImage();
    	pics = new BufferedImage[imagecount][frameCount];
    	for(int j=0; j<imagecount; j++) {
    		for(int i = 0; i < frameCount; i++) {
    			pics[j][i] = img[j].getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		}
    	}
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
    }  
    
    //Read image from file and return
    private BufferedImage[] createImage(){
    	BufferedImage[] bufferedImages=new BufferedImage[imagecount];
    	try {
    		int num=0;
    		for (Direction dir : Direction.values()) {
				File file = new File("lab_animation/images/orc/orc_"+animation+"_"+dir+".png");
    			if(file.exists()){
    				bufferedImages[num] = ImageIO.read(file);
    				//Used to see if all files are loaded
    				num+=1;
    			}
    		}
    		return bufferedImages;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    	// TODO: Change this method so you can load other orc animation bitmaps
    }
    //Holds the eight cardinal directions
  	public enum Direction {
  		SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST, NORTH, NORTHEAST, EAST
  	}
}