package creational.factory.factorymethod;

enum CoordinateSystem {
    CARTESIAN,
    POLAR
}

class Point {
    private double x, y;

    private Point(double x, double y){
        this.x = x;
        this.y = y;
    }


     /*
    public Point(double a, double b, CoordinateSystem cs) {
        switch (cs) {
            case CARTESIAN:
                x = a;
                y = b;
                break;
            case POLAR:
                x = a * Math.cos(b);
                y = a * Math.sin(b);
                break;
        }
    }
     */
    /*
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    */

    // not allowed
    /*
    public Point(double rho, double theta){
        x = rho * Math.cos(theta);
        y = rho * Math.sin(theta);
    }*/

    public static class Factory {
        public static Point newCartesianPoint(double x, double y) {
            return new Point(x, y);
        }

        public static Point newPolarPoint(double rho, double theta) {
            return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
        }
    }
}

public class Demo {
    public static void main(String[] args) {
        Point point = Point.Factory.newPolarPoint(2, 3);
    }
}