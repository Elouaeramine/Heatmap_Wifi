// package wifi;

import java.util.*;
import java.lang.Math;

public class Host {

	private static int id;
	private double xh;
	private double yh;
	private int ap;

	public Host(double x, double y) {
		super();
		this.xh = x;
		this.yh = y;
		id++;
	}

	/*
	 * public void asignPa(ArrayList<AccessPoint> arrPa) { double qmax = 0; int
	 * numPa = 0; for (AccessPoint a : arrPa) { double q = (a.getRc() - distance(a))
	 * / a.getRc(); if (q > qmax) { qmax = distance(a); numPa = arrPa.indexOf(a); }
	 * } setAp(numPa); }
	 */
	/*
	 * public void appartient(ArrayList<AccessPoint> arrPa) { double qmin = 1000000;
	 * int numPa = 0; for (AccessPoint a : arrPa) { double q = (a.getRc() -
	 * distance(a)); if (q < qmin) { qmin = q; numPa = arrPa.indexOf(a); } } if(qmin
	 * < 0) setAp(-1); else setAp(numPa); }
	 */
	public void appartient(ArrayList<AccessPoint> arrPa) {
		 double qmax = 0;
		 int numPa = -1;
		for (int i=0;i<arrPa.size();i++) {
			double d = distance(arrPa.get(i));
			double q=(arrPa.get(i).getRc() - d)/ arrPa.get(i).getRc();
			
			System.out.println(d);
			if (d < arrPa.get(i).getRc() && q > qmax) {
				qmax=q;
				
				numPa=i;
			}
		}
		setAp(numPa);
		
	}

	public String connexion() {
		 if(this.ap==-1)
		 {
			return ("le hote " + Host.id + " n'est pas connect�");
		} else {
			return ("le hote " + Host.id + " est connect� au point d'acc�s " + (ap+1));
		}
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Host.id = id;
	}

	public double distance(AccessPoint ap) {
		double apx = ap.getX();
		double APy = ap.getY();
		double Hx = this.xh;
		double Hy = this.yh;
		double dist = Math.sqrt((apx - Hx) * (apx - Hx) + (APy - Hy) * (APy - Hy));
		return dist;
	}

	public double getXh() {
		return xh;
	}

	public void setXh(double xh) {
		this.xh = xh;
	}

	public double getYh() {
		return yh;
	}

	public void setYh(double yh) {
		this.yh = yh;
	}

	public int getAp() {
		return ap;
	}

	public void setAp(int numPa) {
		this.ap = numPa;
	}

}
