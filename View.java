/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
 import java.awt.Dimension;
 import java.awt.EventQueue;
 import java.awt.event.ActionEvent;
 import java.awt.Color;
 import java.awt.Graphics;
 import java.awt.image.BufferedImage;
 import java.io.File;
 import java.io.IOException;
 import javax.imageio.ImageIO;
 import javax.swing.JFrame;
 import javax.swing.JPanel;
 import javax.swing.AbstractAction;
 import javax.swing.Action;
 import javax.swing.Timer;

import org.omg.PortableInterceptor.ACTIVE;

import javax.swing.JButton;
 import javax.swing.ImageIcon;
 import java.awt.event.ActionListener;
 import java.awt.GridLayout;


 public class View extends JFrame{
   private final int frameCount = Act.FORWARD.getNumPics();
   private int picNum = 0;
   final static int frameWidth = 500;
   final static int frameHeight = 300;
   final static int imgWidth = 165;
   final static int imgHeight = 165;
   BufferedImage[] forwardPics;
   BufferedImage[] jumpPics;
   BufferedImage[] firePics;
   private BufferedImage[] picArray = forwardPics;
   ImageIcon stopicon = createImageIcon("images/icons/stop.png");
   ImageIcon diricon = createImageIcon("images/icons/diricon.png");
   GridLayout layout = new GridLayout(1,2);

   private int xloc = 0;
   private int yloc = 0;
   private Direction d;
   private Act action = Act.FORWARD;
   private int go = 1;

   public View(){
     d = d.SOUTHEAST;
     JPanel drawPanel = new JPanel();
     /*
     drawPanel.getContentPane().add(this);
     drawPanel.setBackground(Color.gray);
     drawPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     drawPanel.setSize(frameWidth, frameHeight);
     drawPanel.pack();
     drawPanel.setVisible(true);
     */
     JFrame frame = new JFrame("Animation Controls");
     JButton stop = new JButton(stopicon);
     JButton change = new JButton(diricon);

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

     change.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e){
         d = d.SOUTHEAST;
       }
     });
     frame.setLayout(layout);
     layout.addLayoutComponent("stop",stop);
     layout.addLayoutComponent("changedir",change);
     frame.add(stop);
     frame.add(change);
     frame.setSize(400,400);
     frame.setVisible(true);
     add(drawPanel);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     drawPanel.setSize(800, 800);
     setVisible(true);
     pack();


     int numpics = 8; //number of images
     forwardPics = new BufferedImage[numpics*Act.FORWARD.getNumPics()]; //modified array to fit all pictures
     for(int i = 0; i<numpics; i++){
       BufferedImage img = createImage(i, Act.FORWARD.getName());
       for(int j = 0; j < Act.FORWARD.getNumPics(); j++){
         forwardPics[(i*Act.FORWARD.getNumPics())+j] = img.getSubimage(imgWidth*j, 0, imgWidth, imgHeight);
       }
     }
     jumpPics = new BufferedImage[numpics*Act.JUMP.getNumPics()]; //modified array to fit all pictures
     for(int i = 0; i<numpics; i++){
       BufferedImage img = createImage(i, Act.JUMP.getName());
       for(int j = 0; j < Act.JUMP.getNumPics(); j++){
         jumpPics[(i*Act.JUMP.getNumPics())+j] = img.getSubimage(imgWidth*j, 0, imgWidth, imgHeight);
       }
     }
     firePics = new BufferedImage[numpics*Act.FIRE.getNumPics()]; //modified array to fit all pictures
     for(int i = 0; i<numpics; i++){
       BufferedImage img = createImage(i, Act.FIRE.getName());
       for(int j = 0; j < Act.FIRE.getNumPics(); j++){
         firePics[(i*Act.FIRE.getNumPics())+j] = img.getSubimage(imgWidth*j, 0, imgWidth, imgHeight);
       }
     }
   }
/*
   int getWidth(){
     return this.frameWidth;
   }
   int getHeight(){
     return this.frameHeight;
   }
*/

   int getImageWidth(){
     return this.imgWidth;
   }
   int getImageHeight(){
     return this.imgHeight;
   }
   Direction getDirect(){
     return this.d;
   }
   void setXloc(int x){
     this.xloc = x;
   }
   void setYloc(int y){
     this.yloc = y;
   }
   void setD(Direction d){
     this.d = d;
   }
   void setGo(int go){
     this.go = go;
   }
   int getGo(){
     return this.go;
   }
   Act getAction() {
	   return action;
   }
   void setAction(Act a) {
	   this.action = a;
   }
   private BufferedImage createImage(int picnum, String a){
  //function now takes an int which changes the picture it reads in
    BufferedImage bufferedImage;
    try {
    if(picnum == 0) {
        bufferedImage = ImageIO.read(new File("images/orc/orc_"+a+"_southeast.png"));
        return bufferedImage;
    }
    else if(picnum == 1) {
        bufferedImage = ImageIO.read(new File("images/orc/orc_"+a+"_northwest.png"));
        return bufferedImage;
    }
    else if(picnum == 2) {
        bufferedImage = ImageIO.read(new File("images/orc/orc_"+a+"_northeast.png"));
        return bufferedImage;
    }
    else if(picnum == 3) {
        bufferedImage = ImageIO.read(new File("images/orc/orc_"+a+"_southwest.png"));
        return bufferedImage;
    }
    else if(picnum == 4) {
        bufferedImage = ImageIO.read(new File("images/orc/orc_"+a+"_east.png"));
        return bufferedImage;
    }
    else if(picnum == 5) {
        bufferedImage = ImageIO.read(new File("images/orc/orc_"+a+"_south.png"));
        return bufferedImage;
    }
    else if(picnum == 6) {
        bufferedImage = ImageIO.read(new File("images/orc/orc_"+a+"_west.png"));
        return bufferedImage;
    }
    else if(picnum == 7) {
        bufferedImage = ImageIO.read(new File("images/orc/orc_"+a+"_north.png"));
        return bufferedImage;
    }
    } catch (IOException e) {
    e.printStackTrace();
    }
    return null;

    // TODO: Change this method so you can load other orc animation bitmaps
  }

  void update(int x, int y, Direction d, int go){
    //System.out.println(x);

    setD(d);
    setGo(go);
    //if(go == 1){
      setXloc(x);
      setYloc(y);
      this.repaint();
      try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
      }
    //}
  }

  public void paint(Graphics g){
    if(go == 1){
      super.paint(g);
      int step = action.getNumPics();
      picNum = (picNum + 1) % step;
      if(action==Act.FORWARD) {
    	  picArray = forwardPics;
      }
      else if(action==Act.JUMP) {
    	  picArray = jumpPics;
      }
      else if(action==Act.FIRE) {
    	  picArray = firePics;
      }

      if(d.getName() == "southeast"){
        g.drawImage(picArray[picNum], xloc, yloc, Color.gray, this);
      }
      else if(d.getName() == "northwest"){
        g.drawImage(picArray[picNum+step], xloc, yloc, Color.gray, this);
      }
      else if(d.getName() == "northeast"){
        g.drawImage(picArray[picNum+2*step], xloc, yloc, Color.gray, this);
      }
      else if(d.getName() == "southwest"){
        g.drawImage(picArray[picNum+3*step], xloc, yloc, Color.gray, this);
      }
      else if(d.getName() == "east"){
        g.drawImage(picArray[picNum+4*step], xloc, yloc, Color.gray, this);
      }
      else if(d.getName() == "south"){
        g.drawImage(picArray[picNum+5*step], xloc, yloc, Color.gray, this);
      }
      else if(d.getName() == "west"){
        g.drawImage(picArray[picNum+6*step], xloc, yloc, Color.gray, this);
      }
      else if(d.getName() == "north"){
        g.drawImage(picArray[picNum+7*step], xloc, yloc, Color.gray, this);
      }

    }
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
