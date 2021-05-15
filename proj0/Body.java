/** Project 0: NBody
The Body Class and Its Constructor
@author Chen Qiu

 */

 import java.lang.Math;

 public class Body {
    // 6 instance variables
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    // two constructors
    public Body(double xP, double yP, double xV,
            double yV, double m, String img){
                xxPos = xP;
                yyPos = yP;
                xxVel = xV;
                yyVel = yV;
                mass = m;
                imgFileName = img;

            }
    
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;

    }
    // instance methods
    public double calcDistance(Body c){
        double xxPos_c;
        double yyPos_c;
        xxPos_c = c.xxPos;
        yyPos_c = c.yyPos;
        
        double xxPos_this;
        double yyPos_this;
        xxPos_this = this.xxPos;
        yyPos_this = this.yyPos;

        double dx;
        double dy;
        dx = xxPos_this - xxPos_c;
        dy = yyPos_this - yyPos_c;

        double dist_square, dist;
        dist_square = dx*dx + dy*dy;
        dist = Math.sqrt(dist_square);

        return dist;

    }

    public double calcForceExertedBy(Body d){
        final double grav_G = 6.67e-11;
        
        double di, force, mass_this, mass_d;
        mass_this = this.mass;
        mass_d = d.mass;

        di = this.calcDistance(d);
        force = (grav_G*mass_d*mass_this)/(di*di);
        return force;


    }
    
 }