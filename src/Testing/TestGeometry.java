package Testing;

import Geometries.Plane;
import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.Point3D;
import Primitives.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestGeometry {
    @Test
    public void testPoint(){
        assertEquals(new Point3D(2,3,5), new Point3D(2,3,5), "hi");
    }

    @Test
    public void testTriangle(){
        Point3D a1 = new Point3D(2,3,5);
        Point3D b1 = new Point3D(2,6,5);
        Point3D c1 = new Point3D(2,2,4);
        assertEquals(new Triangle(a1,b1,c1), new Triangle(c1,b1,a1));
    }
    @Test
    public void testSphere(){
        assertEquals(new Sphere(2,3,2,6), new Sphere(2,3,2,6));
    }
    @Test
    public void testAddPoint(){
        assertEquals(new Point3D(1,2,3), new Point3D(3,3,3).add(new Point3D(-2,-1,0)));
    }
    @Test
    public void testAddVector(){
        assertEquals(new Point3D(1,2,3).add(new Vector(3,3,3)), new Point3D(4,5,6));
    }



    @Test
    public void testPlane(){
        Plane a = new Plane(1,2,3, 1,1,1);
        Plane b = new Plane(-2,4,4,2,2,2);
        assertEquals(a,b);
    }

    @Test
    public void testLength() {
        assertEquals( new Vector(0, 3, 4).length(), 0.00001, 5d,"length() wrong value");
    }

    @Test
    public void testNormalized() {
        Vector v = new Vector(0, 3, 4);
        Vector n = v.normalize();
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertFalse(v == n, "normalized() changes the vector itself");
        assertEquals(n.length(), 0.00001, 1d,"wrong normalized vector length");
    }

    @Test
    public void testAdd() {
        // TC01: Simple test
        assertEquals(new Vector(1, 1, 1),
                new Vector(2, 3, 4).add(new Vector(-1, -2, -3)),
                "Wrong vector add");
        // TC11: test adding v + (-v)
        try {
            new Vector(1, 2, 3).add(new Vector(-1, -2, -3));
            fail("Add v plus -v must throw exception");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testSubtract() {
        // TC01: Simple test
        assertEquals( new Vector(1, 1, 1),
                new Vector(2, 3, 4).subtract(new Vector(1, 2, 3)),
                "Wrong vector subtract");

        // TC11: test subtracting same vector
        try {
            new Vector(1, 2, 3).subtract(new Vector(1, 2, 3));
            fail("Subtract v from v must throw exception");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testScale() {
        // TC01: Simple test
        assertEquals( new Vector(2, 4, 6),
                new Vector(1, 2, 3).scale(2),
                "Wrong vector scale");

        // TC11: testmult by 0
        try {
            new Vector(1, 2, 3).scale(0);
            fail("Scale by 0 must throw exception");
        } catch (IllegalArgumentException e) {}
    }
    @Test
    public void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // TC01: Simple dotProduct test
        Vector v2 = new Vector(-2, -4, -6);
        assertEquals( -28, v1.dotProduct(v2), 0.00001, "dotProduct() wrong value");

        // TC11: dotProduct for orthogonal vectors
        Vector v3 = new Vector(0, 3, -2);
        assertEquals( 0, v1.dotProduct(v3), 0.00001, "dotProduct() for orthogonal vectors is not zero");
    }

    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals( v1.length() * v2.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertEquals(0, vr.dotProduct(v1), "crossProduct() result is not orthogonal to 1st operand");// isZero(vr.dotProduct(v1)));
        assertEquals(0, vr.dotProduct(v2), "crossProduct() result is not orthogonal to 2nd operand");// isZero(vr.dotProduct(v2)));

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        try {
            v1.crossProduct(v3);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) { e.printStackTrace();}
    }

    @Test
    public void testPointSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(new Vector(1, 1, 1),
                new Point3D(2, 3, 4).subtract(new Point3D(1, 2, 3)),
                "Wrong point subtract");

        // =============== Boundary Values Tests ==================
        // TC11: test subtracting same point
        try {
            new Point3D(1, 2, 3).subtract(new Point3D(1, 2, 3));
            fail("Subtract P from P must throw exception");
        } catch (IllegalArgumentException e) {}
    }


}
