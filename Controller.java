/**
 * Do not modify this file without permission from your TA.
 **/

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller {

	private Model model;
	private View view;
	private int go = 1;
	private Direction d;
	private int jump = 0;
	private int fire = 0;
	private int mX = 0;
	private int mY = 0;
	private int x;
	private int y;

	public Controller(){
		MouseListener mouseinput = new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent event){
				mX = event.getX()/view.getWidth();
				mY = event.getY()/view.getHeight();

				x = model.getX();
				y = model.getY();

				System.out.println("x="+x+" y="+y);
				System.out.println("mx="+mX+" my="+mY);
			}
			public void mousePressed(MouseEvent event){}
			public void mouseReleased(MouseEvent event){}
			public void mouseEntered(MouseEvent event){}
			public void mouseExited(MouseEvent event){}
		};
		KeyListener keyinput = new KeyListener(){
			@Override
			public void keyPressed(KeyEvent event){
				if(event.getKeyCode() == KeyEvent.VK_J){
					jump = 1;
					//System.out.println("jumped");
					view.setAction(Act.JUMP);
					model.setAction(Act.JUMP);
				}
				else if(event.getKeyCode() == KeyEvent.VK_F){
					fire = 1;
					view.setAction(Act.FIRE);
					model.setAction(Act.FIRE);
				}
			}
			@Override
			public void keyReleased(KeyEvent event){
				if(event.getKeyCode() == KeyEvent.VK_J){
					jump = 0;
					view.setAction(Act.FORWARD);
					model.setAction(Act.FORWARD);
				}
				else if(event.getKeyCode() == KeyEvent.VK_F){
					fire = 0;
					view.setAction(Act.FORWARD);
					model.setAction(Act.FORWARD);
				}
			}
			@Override
			public void keyTyped(KeyEvent event){

			}
		};
		view = new View();
		view.addKeyListener(keyinput);
		view.addMouseListener(mouseinput);
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
	}

        //run the simulation
	public void start(){
		d = d.SOUTHEAST;
		for(int i = 0; i < 5000; i++)
		{
			d = view.getDirect();
			go = view.getGo();
			model.setDirect(d);
			model.setGo(go);
			model.updateLocationAndDirection();
			view.update(model.getX(), model.getY(), model.getDirect(),model.getGo());
		}
	}
}
