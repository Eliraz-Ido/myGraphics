package Testing;
import Geometries.Sphere;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestSphereIntersections {
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Sphere sph = new Sphere(1.0, new Point3D(0, 0, 1));
        assertEquals(new Vector(0, 0, 1), sph.getNormal(new Point3D(0, 0, 2)), "Bad normal to sphere");
    }

    @Test
    public void testFindIntersectionsRay() {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));
        List<Point3D> temp = new ArrayList<>();

        // ============ Equivalence Partitions Tests ==============
        Point3D gp1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D gp2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> exp = new ArrayList<>();
        exp.add(gp1);
        exp.add(gp2);
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull("Ray's line out of sphere",
                sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));
        // TC02: Ray starts before and crosses the sphere (2 points)
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
        assertEquals( 2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX()) {
            temp.add(result.get(1));
            temp.add(result.get(0));
            result = temp;
        }
        assertEquals(exp, result, "Ray crosses sphere");
        // TC03: Ray starts inside the sphere (1 point)
        List<Point3D> temp2 = new ArrayList<>();
        temp2.add(gp2);
        assertEquals(temp2,
                sphere.findIntersections(new Ray(new Point3D(0.5, 0.5, 0), new Vector(3, 1, 0))),
                "Ray from inside sphere");
        // TC04: Ray starts after the sphere (0 points)
        assertNull("Sphere behind Ray",
                sphere.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(3, 1, 0))));
        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        List<Point3D> temp3 = new ArrayList<>();
        temp3.add(new Point3D(2, 0, 0));
        assertEquals( temp3,
                sphere.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(1, 1, 0))),
                "Ray from sphere inside");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull("Ray from sphere outside",
                sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 1, 0))));

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, -2, 0), new Vector(0, 1, 0)));
        assertEquals( 2, result.size(), "Wrong number of points");
        List<Point3D> temp4 = new ArrayList<>();
        if (result.get(0).getY() > result.get(1).getY()) {
            temp4.add(result.get(1));
            temp4.add(result.get(0));
            result = temp4;
        }
        List<Point3D> temp5 = new ArrayList<>();
        temp5.add(new Point3D(1, -1, 0));
        temp5.add(new Point3D(1, 1, 0));
        assertEquals( temp5 , result,
                "Line through O, ray crosses sphere");
        // TC14: Ray starts at sphere and goes inside (1 points)
        List<Point3D> temp6 = new ArrayList<>();
        temp6.add(new Point3D(1, 1, 0));
        assertEquals(temp6,
                sphere.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(0, 1, 0))),
                "Line through O, ray from and crosses sphere");
        // TC15: Ray starts inside (1 points)
        List<Point3D> temp7 = new ArrayList<>();
        temp7.add(new Point3D(1, 1, 0));
        assertEquals( temp7,
                sphere.findIntersections(new Ray(new Point3D(1, 0.5, 0), new Vector(0, 1, 0))),
                "Line through O, ray from inside sphere");
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull("Line through O, ray from sphere outside",
                sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, 1, 0))));
        // TC18: Ray starts after sphere (0 points)
        assertNull("Line through O, ray outside sphere",
                sphere.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(0, 1, 0))));

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull("Tangent line, ray before sphere",
                sphere.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(1, 0, 0))));
        // TC20: Ray starts at the tangent point
        assertNull("Tangent line, ray at sphere",
                sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 0))));
        // TC21: Ray starts after the tangent point
        assertNull("Tangent line, ray after sphere",
                sphere.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(1, 0, 0))));

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's
        // center line
        assertNull("Ray orthogonal to ray head -> O line",
                sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(0, 0, 1))));

    }
}
