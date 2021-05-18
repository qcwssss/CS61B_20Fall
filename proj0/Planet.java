/**
 Project 0: NBody
 @author Chen Qiu
 */
 /**
 Project 0: NBody
 @author Chen Qiu
 */

public class Planet {
    /** the radius of the universe. */ 
    public static double readRadius(String file_name) {
        In data = new In(file_name);
        int num = data.readInt();
        double radius = data.readDouble();

        //System.out.println(radius);
        return radius;
    }
    /** read bodys correspondings */
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
    /** the main function to draw the universe
     * 4 steps in all 
     */
    public static void main(String[] args) {
        /** Collecting All Needed Input */ 
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);

        String filename = args[2];

        double radius = readRadius(filename);
        Body [] fivePlanets = readBodies(filename);
        
        /**Drawing the Background */
        StdDraw.setScale(-radius, radius);
        String imageBackgd = "images/starfield.jpg";
        StdDraw.picture(0, 0, imageBackgd);

        /** draw each one of the bodies in the Bodys array you created */
        for (Body planet : fivePlanets){
            planet.draw();
        }
        /** Creating an Animation */
        StdDraw.enableDoubleBuffering();
        double time = 0;
        int num_body = fivePlanets.length;
        while (time < T) {
            double [] xForces = new double [num_body];
            double [] yForces = new double [num_body];
            // update planets 
            for (int i =0; i < num_body; i++) {
                xForces[i] = fivePlanets[i].calcNetForceExertedByX(fivePlanets);
                yForces[i] = fivePlanets[i].calcNetForceExertedByY(fivePlanets);
            }
            int cntr = 0;
            for (Body element : fivePlanets) {
                element.update(dt, xForces[cntr], yForces[cntr]);
                cntr ++;
            }
            StdDraw.picture(0, 0, imageBackgd); // draw background
            /** draw each one of the bodies in the Bodys array you created */
            for (Body planet : fivePlanets){
                planet.draw();
            }
		    /* Shows the drawing to the screen, and waits 10 milliseconds. */
            StdDraw.show();
            StdDraw.pause(10);
            // increase time/counter
            time += dt;
        }
        /** Printing the Universe */
        StdOut.printf("%d\n", num_body);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < num_body; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
               fivePlanets[i].xxPos,fivePlanets[i].yyPos,fivePlanets[i].xxVel,
               fivePlanets[i].yyVel,fivePlanets[i].mass,fivePlanets[i].imgFileName);   
}

    }


}