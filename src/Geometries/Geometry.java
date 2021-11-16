package Geometries;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.List;

public abstract class Geometry {
    public abstract List<Point3D> findIntersections(Ray ray);
    public abstract Vector getNormal(Point3D point);
}
