package Elements;

import Primitives.Point3D;
import Primitives.Vector;
import java.awt.*;

public class DirectionalLight extends Light{
    Vector _direction;

    //Constructors
    public DirectionalLight(Vector v) {
        setDirection(v);
    }
    public DirectionalLight(Color c, Vector v){
        setDirection(v);
        _intensity = c;
    }

    //Getter
    public Vector getDirection() { return _direction; }

    //Setter
    public void setDirection(Vector _direction) { this._direction = _direction; }

    //Override methods
    @Override
    public Color getIntensity(Point3D p) {
        return _intensity;
    }

    @Override
    public Vector getL(Point3D p) {
//        return _direction.getHead().subtract(p);
        return p.subtract(_direction.getHead());
    }
}
