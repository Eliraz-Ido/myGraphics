package Testing;

import Elements.AmbientLight;
import Elements.Camera;
import Elements.PointLight;
import Elements.SpotLight;
import Geometries.Plane;
import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.Material;
import Primitives.Point3D;
import Primitives.Vector;
import Renderer.ImageWriter;
import Renderer.Renderer;
import Scene.Scene;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class MyLightTest {

    @Test
    public void myLightTest() {

        Scene scene = new Scene("MyLightTest");
        scene.setBackground(new Color(0, 0, 0));
        scene.setCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setScreenDistance(300);
        scene.setAmbientLight(new AmbientLight(new Color(0, 0, 100), 0.1));

        Material m1 = new Material(1,1,4);

        Triangle triangle = new Triangle(new Point3D(0, -200, 260),
                new Point3D(-200, 100, 260),
                new Point3D(200, 100, 260),
                new Color (0, 0, 100));
        triangle.setMaterial(m1);
        scene.addGeometry(triangle);

        Triangle triangle2 = new Triangle(new Point3D(20, -100, 200),
                new Point3D(-80, 100, 200),
                new Point3D(120, 100, 200),
                new Color (193, 103, 203));
        triangle2.setMaterial(m1);
        scene.addGeometry(triangle2);

        Sphere s = new Sphere(40, new Point3D(70,70, 150), new Color(38, 204, 227));
        s.setMaterial(new Material(1,1,20));
        scene.addGeometry(s);

        Sphere s2 = new Sphere(250, new Point3D(-250,0, 700), new Color(74, 197, 153));
        s2.setMaterial(new Material(1,1,20));
        scene.addGeometry(s2);

        Plane plane = new Plane(new Point3D(-800,400,0), new Vector(3,-2, 0), new Color(136, 15, 50));
        plane.setMaterial(m1);
        scene.addGeometry(plane);


        scene.addLight(new PointLight(new Color(200, 100, 100), new Point3D(100, -100, 100),
                0.1, 0.00001, 0.000005));

//        scene.addLight(new SpotLight(new Color(0, 100, 100), new Point3D(100, -100, 100), new Vector(2,2,0),
//                0.1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("My Light Test", 1000, 1000, 1000, 1000);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();

    }
}
