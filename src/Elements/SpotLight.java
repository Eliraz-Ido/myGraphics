package Elements;

import Primitives.Point3D;
import Primitives.Vector;

import java.awt.*;

public class SpotLight extends PointLight{
    private Vector _direction;

    //Constructor
    public SpotLight(Color c, Point3D _position, Vector v, double _kc, double _kl, double _kq) {
        super(c, _position, _kc, _kl, _kq);
        this._direction = v;
    }

    //Getter
    public Vector getDirection() { return _direction; }

    //Setter
    public void setDirection(Vector _direction) { this._direction = _direction; }

    //Override methods
    @Override
    public Color getIntensity(Point3D p) {
        double d = getPosition().distance(p);
        double numerator = Math.max(0, _direction.normalize().dotProduct(getL(p).normalize()));
        double denominator = _kc + _kl * d + _kq * Math.pow(d,2);
        return colorMultiplyDouble(_intensity, (numerator / denominator) );
    }

}
