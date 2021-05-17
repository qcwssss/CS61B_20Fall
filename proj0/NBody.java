/**
 Project 0: NBody
 @author Chen Qiu
 */


public class NBody {
    // the radius of the universe.
    public static double readRadius(String file_name) {
        In data = new In(file_name);
        int num = data.readInt();
        double radius = data.readDouble();

        System.out.println(radius);
        return radius;
    }
    // read bodys correspondings
    public static Body [] readBodies(String file_name) {
        In data = new In(file_name);

        int num = data.readInt();
        double radius = data.readDouble();
        Body[] fivePlanets = new Body[num];

        /**keep looking the file until it reaches the comment */
        for(int i =0; i < num; i++){
            double xxPos = data.readDouble();
            double yyPos = data.readDouble();
            double xxVel = data.readDouble();
            double yyVel = data.readDouble();
            double mass = data.readDouble();
            String imgFileName= data.readString();
            // construct new planet
            Body planet = new Body(xxPos, yyPos, xxVel, 
                                yyVel, mass, imgFileName);
            fivePlanets[i] = planet;
        }
        return fivePlanets;
    }

}