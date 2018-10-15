/**
 * Do not modify this file without permission from your TA.
 **/

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {

	private Model model;
	private View view;
	private int go = 1;
	private Direction d;
	private int jump = 0;
	private int fire = 0;

	public Controller(){

		KeyListener input = new KeyListener(){
			@Override
			public void keyPressed(KeyEvent event){
				if(event.getKeyCode() == KeyEvent.VK_J){
					jump = 1;
					System.out.println("jumped");
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
		view.addKeyListener(input);
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
			//System.out.println(model.getGo());
			//System.out.println(go);
			view.update(model.getX(), model.getY(), model.getDirect(),model.getGo());
		}
	}
}
