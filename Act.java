
public enum Act {
	
	DIE("die",7),
	FIRE("fire", 4),
	FORWARD("forward", 10),
	IDLE("idle", 16),
	JUMP("jump", 8);
	
	private String name=null;
	private int numPics = 0;
	private Act(String s, int n) {
		name = s;
		numPics = n;
	}
	public String getName() {
		return name;
	}
	public int getNumPics() {
		return numPics;
	}
}
