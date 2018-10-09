/**
 * Do not modify this file without permission from your TA.
 **/
public class Controller {

	private Model model;
	private View view;
	private int go = 1;

	public Controller(){
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
	}

        //run the simulation
	public void start(){
		for(int i = 0; i < 5000; i++)
		{
			model.updateLocationAndDirection(go);
			System.out.println(model.getGo());
			//System.out.println(go);
			go = view.update(model.getX(), model.getY(), model.getDirect(),model.getGo());
		}
	}
}
