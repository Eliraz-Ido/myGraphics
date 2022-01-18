package Elements;

import Primitives.Point3D;
import Primitives.Vector;

import java.awt.*;

public abstract class Light {
    Color _intensity;

    //Methods to implement in inherited classes
    public abstract Color getIntensity(Point3D p);
    public abstract Vector getL(Point3D p);

}
