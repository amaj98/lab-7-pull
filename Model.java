/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/


public class Model{

  private final int xIncr = 8;
  private final int yIncr = 2;

  private int xloc = 0;
  private int yloc = 0;

  private int frameWidth;
  private int frameHeight;
  private int imgWidth;
  private int imgHeight;

  private Direction d;
  private Act action = Act.FORWARD;
  private int go;


  public Model(int width, int height, int imgWidth, int imgHeight){
    this.frameWidth = width;
    this.frameHeight = height;
    this.imgWidth = imgWidth;
    this.imgHeight = imgHeight;
    d = d.SOUTHEAST;
  }

  public int getX(){
    return this.xloc;
  }

  public int getY(){
    return this.yloc;
  }

  public Direction getDirect(){
    return this.d;
  }

  public int getGo(){
    return this.go;
  }
  public void setGo(int go){
    this.go = go;
  }

  public void setDirect(Direction d){
    this.d = d;
  }

  public void updateLocationAndDirection(){
    if((action==Act.FORWARD || action==Act.JUMP) && go == 1){
      //System.out.println("x:" + xloc);
      //System.out.println("y:" + yloc);
      if(xloc<0){
        if(d.getName() == "west"){
          d = Direction.EAST;
        }
        else if(d.getName() == "northwest"){
          d = Direction.NORTHEAST;
        }
        else if(d.getName() == "southwest"){
          d = Direction.SOUTHEAST;
        }
      }

      else if(xloc > frameWidth){
        if(d.getName() == "east"){
          d = Direction.WEST;
        }
        else if(d.getName() == "northeast"){
          d = Direction.NORTHWEST;
        }
        else if(d.getName() == "southeast"){
          d = Direction.SOUTHWEST;
        }
      }

      else if(yloc < 0){
        if(d.getName() == "north"){
          d = Direction.SOUTH;
          yloc+=yIncr;
        }
        else if(d.getName() == "northwest"){
          d = Direction.SOUTHWEST;
          xloc+=xIncr;
          yloc-=yIncr;
        }
        else if(d.getName() == "northeast"){
          d = Direction.SOUTHEAST;
        }
      }

      else if(yloc > frameHeight){
        if(d.getName() == "south"){
          d = Direction.NORTH;
        }
        else if(d.getName() == "southwest"){
          d = Direction.NORTHWEST;
        }
        else if(d.getName() == "southeast"){
          d = Direction.NORTHEAST;
        }
      }
      move();
    }
}
  void move(){
    if(d.getName() == "north"){
      yloc-=yIncr;
    }
    else if(d.getName() == "south"){
      yloc+=yIncr;
    }
    else if(d.getName() == "east"){
      xloc+=xIncr;
    }
    else if(d.getName() == "west"){
      xloc-=yIncr;
    }
    else if(d.getName() == "southeast"){
      xloc+=xIncr;
      yloc+=yIncr;
    }
    else if(d.getName() == "southwest"){
      xloc-=xIncr;
      yloc+=yIncr;
    }
    else if(d.getName() == "northeast"){
      xloc+=xIncr;
      yloc-=yIncr;
    }
    else if(d.getName() == "northwest"){
      xloc-=xIncr;
      yloc-=yIncr;
    }
  }
  void setAction(Act a) {
	  this.action = a;
  }
}
