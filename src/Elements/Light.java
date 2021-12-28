package Elements;

import Primitives.Point3D;
import Primitives.Vector;

import java.awt.*;

public abstract class Light {
    Color _intensity;

    //Methods to implement in inherited classes
    public abstract Color getIntensity(Point3D p);
    public abstract Vector getL(Point3D p);

    //Other Methods
    public static Color colorMultiplyDouble(Color c, double scale) {
        if (0 < scale)
            return new Color(
                    (int) (Math.min(c.getRed() * scale, 255)),
                    (int) (Math.min(c.getGreen() * scale, 255)),
                    (int) (Math.min(c.getBlue() * scale, 255))
            );
        else
            return new Color(0, 0, 0);
    }

    public static Color colorPlusColor(Color c1, Color c2) {
        return new Color(
                Math.min(c1.getRed() + c2.getRed(), 255),
                Math.min(c1.getGreen() + c2.getGreen(), 255),
                Math.min(c1.getBlue() + c2.getBlue(), 255)
        );
    }
}
