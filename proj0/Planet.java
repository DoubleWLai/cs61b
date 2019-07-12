/** Project 0: NBody Simulation
 *
 *
 *  @author Wenwei Lai  09/07/2019
 */

public class Planet {

    public double xxPos;       // Its current x position
    public double yyPos;       // Its current y position
    public double xxVel;       // Its current velocity in the x direction
    public double yyVel;       // Its current velocity in the y direction
    public double mass;        // Its mass
    public String imgFileName; // The name of the file that corresponds to the image that depicts the planet
    private static final double G = 6.67e-11; //gravitational constant 

// Initialize a Planet object.
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

// Initialize an identical Planet object (i.e. a copy)
    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

//Calculates the distance between two Planets
    public double calcDistance (Planet p) {
    double dx = this.xxPos - p.xxPos;
    double dy = this.yyPos - p.yyPos;
    return Math.sqrt(dx*dx + dy*dy);
}
    
//Calculate the force exerted on this planet by the given planet
    public double calcForceExertedBy (Planet p) {
        double dist = this.calcDistance(p);
        return (G * this.mass * p.mass)/(dist*dist);
    }

//Two methods below describe the force exerted in the X and Y directions, respectively.
    public double calcForceExertedByX (Planet p) {
        double totalForce = this.calcForceExertedBy(p);
        return (totalForce * (p.xxPos - this.xxPos))/ this.calcDistance(p);
    }

    public double calcForceExertedByY (Planet p) {
        double totalForce = this.calcForceExertedBy(p);
        return (totalForce * (p.yyPos - this.yyPos))/ this.calcDistance(p);
    }

//calculate the net X and net Y force exerted by all planets in that array upon the current Planet
    public double calcNetForceExertedByX(Planet[] ps) {
        double a = 0;
        for (int i =0; i < ps.length; i++) {
            if (!this.equals(ps[i])) {
                a = this.calcForceExertedByX(ps[i]) + a;
            } 
        }
        return a;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
        double a = 0;
        for (int i =0; i < ps.length; i++) {
            if (!this.equals(ps[i])) {
                a = this.calcForceExertedByY(ps[i]) + a;
            } 
        }
        return a;
    }

    public void update(double dt, double fX, double fY) {
        xxVel += dt * fX / mass;
        yyVel += dt * fY / mass;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }


    public void draw() {
        String filename = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, filename);    
    }










}




