package Elements;

import Primitives.Point3D;
import Primitives.Vector;
import java.awt.*;

public class PointLight extends Light {
    Point3D _position;
    double _kc;
    double _kl;
    double _kq;

    //Constrictors
    public PointLight(Color c, Point3D _position, double _kc, double _kl, double _kq) {
        this._intensity = c;
        this._position = _position;
        this._kc = _kc;
        this._kl = _kl;
        this._kq = _kq;
    }

    //Getters
    public Point3D getPosition() { return _position; }
    public double getKc() { return _kc; }
    public double getKl() { return _kl; }
    public double getKq() { return _kq; }

    //Setters
    public void setPosition(Point3D _position) { this._position = _position; }
    public void setKc(double _kc) { this._kc = _kc; }
    public void setKl(double _kl) { this._kl = _kl; }
    public void setKq(double _kq) { this._kq = _kq; }

    //Override methods
    @Override
    public Color getIntensity(Point3D p) {
        double d = getPosition().distance(p);
        double coefficient = 1 / (_kc + _kl * d + _kq * Math.pow(d, 2));
        return colorMultiplyDouble(_intensity, coefficient);
    }

    @Override
    public Vector getL(Point3D p) {
        return _position.subtract(p);
    }
}
