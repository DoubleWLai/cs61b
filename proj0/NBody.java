import java.util.List;
import java.util.ArrayList;


public class NBody {

 //  Read radius from file 
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		in.readInt();
		return in.readDouble();
	}

//Given a file name, it should return an array of Planets corresponding to the planets in the file
	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int num = in.readInt();
		in.readDouble();
		Planet[] ps = new Planet[num]; 
		for (int i = 0; i < num; i++) {
			double xp = in.readDouble();
			double yp = in.readDouble();
			double xv = in.readDouble();
			double yv = in.readDouble();
			double mas = in.readDouble();
			String ima = in.readString();
			ps[i] = new Planet(xp, yp, xv, yv, mas, ima);
		}
		return ps;
	}



	public static void main(String[] args) {
	//Collecting All Needed Input
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double uniRadius = readRadius(filename);
		Planet[] ps = readPlanets(filename);
	//Drawing the Background
		StdDraw.setScale(-uniRadius, uniRadius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
	//Drawing All of the Planets
		for (Planet p : ps) {
			p.draw();
		}
	//Creating an Animation
		StdDraw.enableDoubleBuffering();

		double time = 0;
		while (time <= T) {
			double[] xForces = new double[ps.length];
			double[] yForces = new double[ps.length];
		//Calculate the net x and y forces for each planet
			for (int i = 0; i < ps.length; i++) {
				xForces[i] = ps[i].calcNetForceExertedByX(ps);
				yForces[i] = ps[i].calcNetForceExertedByY(ps);
			}
		//Call update on each of the planets. This will update each planet’s position, velocity, and acceleration.
			for (int i = 0; i < ps.length; i++) {
				ps[i].update(dt, xForces[i], yForces[i]);
			}
		//Draw the background image.
			StdDraw.picture(0, 0, "images/starfield.jpg");
		//Draw all of the planets
			for (Planet p : ps) {
				p.draw();
			}
		//Show the offscreen buffer
			StdDraw.show();
		//Pause the animation for 10 milliseconds
			StdDraw.pause(10);
		//Increase time variable
			time += dt;

		}
	//Printing the Universe
		StdOut.printf("%d\n", ps.length);
		StdOut.printf("%.2e\n", uniRadius);
			for (int i = 0; i < ps.length; i++) {
   				StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
                ps[i].yyVel, ps[i].mass, ps[i].imgFileName);   
}











	}





}