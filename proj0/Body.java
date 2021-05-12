/** Project 0: NBody
The Body Class and Its Constructor
@author Chen Qiu

 */

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
        return b;

    }
    


    
 }