// // package wifi;

public class Obstacle {
	private int xo;
	private int yo;
	private int l,h;
	public Obstacle(int xo, int yo) {
		super();
		this.xo = xo;
		this.yo = yo;
		this.l = 10;
		this.h = 50;
	}
	
	//getters and setters

	public int getXo() {
		return xo;
	}
	public void setXo(int xo) {
		this.xo = xo;
	}
	public int getYo() {
		return yo;
	}
	public void setYo(int yo) {
		this.yo = yo;
	}
	public int getL() {
		return l;
	}
	public void setL(int l) {
		this.l = l;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	
	

}
