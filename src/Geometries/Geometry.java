package Geometries;
import Primitives.Material;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.awt.Color;
import java.util.List;

public abstract class Geometry {
    Color _emission;
    Material _material;

    //Getter
    public Color getEmission() { return _emission; }
    public Material getMaterial() { return _material; }

    //Setter
    public void setEmission(Color emission) { this._emission = emission; }
    public void setMaterial(Material m) { this._material = m; }

    //Methods to implement in inherited classes
    public abstract List<GeoPoint> findIntersections(Ray ray);
    public abstract Vector getNormal(Point3D point);
}
