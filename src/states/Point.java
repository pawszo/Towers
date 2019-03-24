package states;

import java.awt.geom.Point2D;

public class Point extends Point2D {

    public Point(double x, double y) {
        super();
        setLocation(x, y);

    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public void setLocation(double v, double v1) {

    }
}
