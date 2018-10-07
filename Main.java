/*
1. Dead code in model. Some conditions are never executed, which is why the orc does not behave correctly in the animation. (-1)
2. Lacks main method. (-1)
3. Images should be organized using enums. (-1)
*/
public class Main{
  public static void main(String[] args){
    Controller c = new Controller();
    c.start();
  }
}
