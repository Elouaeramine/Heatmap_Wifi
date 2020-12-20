// package wifi;


class AccessPoint {
	private double x, y;
	private double rc;
	static private int num = 0;

	public AccessPoint(double x, double y, String pui) {
		this.x = x;
		this.y = y;
		num++;
		this.rc =1.66*Integer.parseInt(pui)+83;
	}
	
	//getters and setters

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccessPoint other = (AccessPoint) obj;
		if (rc != other.rc)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccessPoint [x=" + x + ", y=" + y + ", rc=" + rc + "]";
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRc() {
		return rc;
	}

	public void setRc(double rc) {
		this.rc = rc;
	}

	public static int getNum() {
		return num;
	}

	public static void setNum(int num) {
		AccessPoint.num = num;
	}
	

}