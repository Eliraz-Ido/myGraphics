package Testing;
;
import org.junit.Test;
import Primitives.*;
import Geometries.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestTriangleIntersections {
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle pl = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to triangle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }
    @Test
    public void testFindIntersectionsRay() {
        Triangle tr = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        Plane pl = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        Ray ray;
        // ============ Equivalence Partitions Tests ==============
        // TC01: Inside triangle
        ray = new Ray(new Point3D(0.5,0.5,0.5), new Vector(-1, -1, -1));
        List<Point3D> list1 =  new ArrayList<Point3D>();
        list1.add( new Point3D(1d / 3, 1d / 3, 1d / 3));
        assertEquals("Bad intersection", list1, tr.findIntersections(ray));

        // TC02: Against edge
        ray = new Ray(new Point3D(0, 0, -1), new Vector(1, 1, 0));
        List<Point3D> list2 =  new ArrayList<Point3D>();
        list2.add(new Point3D(1, 1, -1));
        assertEquals("Wrong intersection with plane", list2, pl.findIntersections(ray));
        assertNull("Bad intersection", tr.findIntersections(ray));

        // TC03: Against vertex
        ray = new Ray(new Point3D(0, 0, 2), new Vector(-1, -1, 0));
        List<Point3D> list3 =  new ArrayList<Point3D>();
        list3.add(new Point3D(-0.5, -0.5, 2));
        assertEquals("Wrong intersection with plane", list3, pl.findIntersections(ray));
        assertNull("Bad intersection", tr.findIntersections(ray));

        // =============== Boundary Values Tests ==================
        // TC11: In vertex
        ray = new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0));
        List<Point3D> list4 =  new ArrayList<Point3D>();
        list4.add(new Point3D(0, 1, 0));
        assertEquals("Wrong intersection with plane", list4, pl.findIntersections(ray));
        assertNull("Bad intersection", tr.findIntersections(ray));

        // TC12: On edge
        ray = new Ray(new Point3D(-1, -1, 0), new Vector(1, 1, 0));
        List<Point3D> list5 =  new ArrayList<Point3D>();
        list5.add(new Point3D(0.5, 0.5, 0));
        assertEquals("Wrong intersection with plane",list5 , pl.findIntersections(ray));
        assertNull("Bad intersection", tr.findIntersections(ray));

        // TC13: On edge continuation
        ray = new Ray(new Point3D(-2, 0, 0), new Vector(1, 1, 0));
        List<Point3D> list6 =  new ArrayList<Point3D>();
        list6.add(new Point3D(-0.5, 1.5, 0));
        assertEquals("Wrong intersection with plane", list6 , pl.findIntersections(ray));
        assertNull("Bad intersection", tr.findIntersections(ray));
    }
}
