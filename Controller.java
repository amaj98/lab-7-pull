/**
 * Do not modify this file without permission from your TA.
 **/
public class Controller {

	private Model model;
	private View view;
	private int go = 1;
	private Direction d;

	public Controller(){
		view = new View();
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
