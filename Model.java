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
  private int go = 1;


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

  public void updateLocationAndDirection(int go){
    this.go = go;
    System.out.println(this.go);
    if(go == 1){
      //System.out.println("x:" + xloc);
      //System.out.println("y:" + yloc);
      if(xIncr>0 && yIncr>0){
        if(d.getName() == "southwest"){
          xloc-=xIncr;
          yloc+=yIncr;
          if(xloc < 0){
            d = Direction.SOUTHEAST;
          }
          else if(yloc > frameHeight){
            d = Direction.NORTHWEST;
          }
        }
        else if(d.getName() == "northwest"){
          xloc-=xIncr;
          yloc-=yIncr;
          if(xloc < 0){
            d = Direction.NORTHEAST;
          }
          else if(yloc < 0){
            d = Direction.SOUTHWEST;
          }
        }
        else if(d.getName() == "southeast"){
          xloc+=xIncr;
          yloc+=yIncr;
          if(xloc > frameWidth){
            d = Direction.SOUTHWEST;
          }
          else if(yloc > frameHeight){
            d = Direction.NORTHEAST;
          }
        }
        else if(d.getName() == "northeast"){
          xloc+=xIncr;
          yloc-=yIncr;
          if(xloc > frameWidth){
            d = Direction.NORTHWEST;
          }
          else if(yloc < 0){
            d = Direction.SOUTHEAST;
          }
        }
      }
      else if(xIncr == 0 && yIncr>0){
        if(yloc <= 0){
          d = Direction.SOUTH;
          xloc+=xIncr;
          yloc+=yIncr;
        }
        else if(yloc > frameHeight){
          d = Direction.NORTH;
          xloc+=xIncr;
          yloc-=yIncr;
        }
      }
      else if(yIncr == 0 && xIncr>0){
        if(xloc <= 0){
          d = Direction.EAST;
          xloc+=xIncr;
          yloc+=yIncr;
        }
        else if(xloc > frameWidth){
          d = Direction.WEST;
          xloc-=xIncr;
          yloc+=yIncr;
        }
      }
    }
  }
}
