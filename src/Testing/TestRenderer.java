package Testing;
import java.awt.Color;

import org.junit.jupiter.api.Test;

import Elements.Camera;
import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.Point3D;
import Primitives.Vector;
import Renderer.ImageWriter;
import Renderer.Renderer;
import Scene.Scene;

public class TestRenderer {

    @Test
    public void basicRenderTwoColorTest() {

        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setScreenDistance(100) ;
        scene.setBackground(new Color(176, 79, 229, 255));

        scene.addGeometry(new Sphere(50, new Point3D(0, 0, 100),new Color(255,0,0)));

        scene.addGeometry(new Triangle(new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100),new Color(255,255,0)));
        scene.addGeometry(new Triangle(new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100),new Color(0,255,0)));
        scene.addGeometry(new Triangle(new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100),new Color(0,0,255)));
        scene.addGeometry(new Triangle(new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100),new Color(0,255,255)));

        ImageWriter imageWriter = new ImageWriter("two color test", 500, 500, 500, 500);
        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();
    }

}