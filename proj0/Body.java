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

    // calculate the force exerted on x and y directions, **with signs**
    public double calcForceExertedByX(Body d){
        // calculate the difference in distance of the two bodies
        double xxDiff, distance;
        distance = this.calcDistance(d);
        xxDiff = d.xxPos - this.xxPos;
        
        double force_whole, force_xx;
        force_whole = this.calcForceExertedBy(d);
        force_xx = force_whole*xxDiff/distance;

        return force_xx;     
    }

    public double calcForceExertedByY(Body d){
        // calculate the difference in distance of the two bodies
        double yyDiff, distance;
        distance = this.calcDistance(d); // duplicate
        yyDiff = d.yyPos - this.yyPos;
        
        double force_whole, force_yy;
        force_whole = this.calcForceExertedBy(d); // duplicate
        force_yy = force_whole*yyDiff/distance;

        return force_yy;
    } 

    //calculate all net forces exerted on both x, y directions
    public double calcNetForceExertedByX(Body[] allBodys){
        double sumOfForce_xx = 0;
        for (Body object : allBodys){
            if (this.equals(object)){
                continue;
            }

            sumOfForce_xx += this.calcForceExertedByX(object);

        }
            return sumOfForce_xx;

    }

    public double calcNetForceExertedByY(Body[] allBodys){
        double sumOfForce_yy = 0;
        for (Body object : allBodys){
            if (this.equals(object)){
                continue;
            }

            sumOfForce_yy += this.calcForceExertedByY(object);

        }
            return sumOfForce_yy;
    }

    // update the body status
    public void update(double dt, double x_force, double y_force){
        // calculate net acceleration
        double aX_net, aY_net;
        aX_net = x_force/this.mass;
        aY_net = y_force/this.mass;
        // new velocity
        xxVel = this.xxVel + dt*aX_net;
        yyVel = this.yyVel + dt*aY_net;
        //new position
        xxPos = this.xxPos + dt*xxVel;
        yyPos = this.yyPos + dt*yyVel;

    }
    
 }