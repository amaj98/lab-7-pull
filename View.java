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
 import javax.swing.JButton;
 import javax.swing.ImageIcon;
 import java.awt.event.ActionListener;
 import java.awt.GridLayout;


 public class View extends JFrame{
   private final int frameCount = 10;
   private int picNum = 0;
   final static int frameWidth = 500;
   final static int frameHeight = 300;
   final static int imgWidth = 165;
   final static int imgHeight = 165;
   private BufferedImage[] pics;
   ImageIcon stopicon = createImageIcon("images/icons/stop.png");
   ImageIcon diricon = createImageIcon("images/icons/diricon.png");
   GridLayout layout = new GridLayout(1,2);

   private int xloc = 0;
   private int yloc = 0;
   private Direction d;
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
     pics = new BufferedImage[numpics*10]; //modified array to fit all pictures
     for(int i = 0; i<numpics; i++){
       BufferedImage img = createImage(i);
       for(int j = 0; j < frameCount; j++){
         pics[(i*10)+j] = img.getSubimage(imgWidth*j, 0, imgWidth, imgHeight);
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
   private BufferedImage createImage(int picnum){
  //function now takes an int which changes the picture it reads in
    BufferedImage bufferedImage;
    try {
    if(picnum == 0) {
        bufferedImage = ImageIO.read(new File("images/orc/orc_forward_southeast.png"));
        return bufferedImage;
    }
    else if(picnum == 1) {
        bufferedImage = ImageIO.read(new File("images/orc/orc_forward_northwest.png"));
        return bufferedImage;
    }
    else if(picnum == 2) {
        bufferedImage = ImageIO.read(new File("images/orc/orc_forward_northeast.png"));
        return bufferedImage;
    }
    else if(picnum == 3) {
        bufferedImage = ImageIO.read(new File("images/orc/orc_forward_southwest.png"));
        return bufferedImage;
    }
    else if(picnum == 4) {
        bufferedImage = ImageIO.read(new File("images/orc/orc_forward_east.png"));
        return bufferedImage;
    }
    else if(picnum == 5) {
        bufferedImage = ImageIO.read(new File("images/orc/orc_forward_south.png"));
        return bufferedImage;
    }
    else if(picnum == 6) {
        bufferedImage = ImageIO.read(new File("images/orc/orc_forward_west.png"));
        return bufferedImage;
    }
    else if(picnum == 7) {
        bufferedImage = ImageIO.read(new File("images/orc/orc_forward_north.png"));
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
      picNum = (picNum + 1) % frameCount;
      if(d.getName() == "southeast"){
        g.drawImage(pics[picNum+0], xloc, yloc, Color.gray, this);
      }
      else if(d.getName() == "northwest"){
        g.drawImage(pics[picNum+10], xloc, yloc, Color.gray, this);
      }
      else if(d.getName() == "northeast"){
        g.drawImage(pics[picNum+20], xloc, yloc, Color.gray, this);
      }
      else if(d.getName() == "southwest"){
        g.drawImage(pics[picNum+30], xloc, yloc, Color.gray, this);
      }
      else if(d.getName() == "east"){
        g.drawImage(pics[picNum+40], xloc, yloc, Color.gray, this);
      }
      else if(d.getName() == "west"){
        g.drawImage(pics[picNum+50], xloc, yloc, Color.gray, this);
      }
      else if(d.getName() == "south"){
        g.drawImage(pics[picNum+60], xloc, yloc, Color.gray, this);
      }
      else if(d.getName() == "north"){
        g.drawImage(pics[picNum+70], xloc, yloc, Color.gray, this);
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
