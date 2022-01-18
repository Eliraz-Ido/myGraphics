package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.awt.Color;
import Elements.*;
import Geometries.*;
import Primitives.*;
import Renderer.*;
import Scene.Scene;

class testRecursive {

    @Test
    public void recursiveTest(){

        Scene scene = new Scene("recursiveTest");
        scene.setBackground(new Color(0, 0, 0));
        scene.setCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setScreenDistance(300);

        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, 1000),new Color(0, 0, 100));
        Material m=new Material(1,1,0.5,0, 20);
        sphere.setMaterial(m);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(150, new Point3D(0.0, 0.0, 1000),new Color(100, 20, 20));
        Material m2=new Material(1,1,0,0,20);
        sphere2.setMaterial(m2);
        scene.addGeometry(sphere2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, 150),
                new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Recursive Test", 500, 500, 500, 500);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();

    }


    @Test
    public void recursiveTest2(){

        Scene scene = new Scene("recursiveTest2");
        scene.setBackground(new Color(0, 0, 0));
        scene.setCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setScreenDistance(300);

        Sphere sphere = new Sphere(300, new Point3D(-550, 500, 1000),new Color(0, 0, 100));
        Material m=new Material(1,1,0.5, 0,20);
        sphere.setMaterial(m);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(50, new Point3D(-550, 500, 1000),new Color(100, 20, 20));
        Material m2=new Material(1,1,0,0,20);
        sphere2.setMaterial(m2);
        scene.addGeometry(sphere2);

        Triangle triangle = new Triangle(new Point3D(  1500, 1500, 1500),
                new Point3D( -1500,  -1500, 1500),
                new Point3D(  200,  -200, 375),
                new Color(20, 20, 20));

        Triangle triangle2 = new Triangle(new Point3D(  1500, 1500, 1500),
                new Point3D( -1500,  -1500, 1500),
                new Point3D( -1500, 1500, 1500),
                new Color(20, 20, 20));

        Material m3=new Material(1,1,1,1);
        Material m4=new Material(1,1,0.5,1);
        triangle.setMaterial(m3);
        triangle2.setMaterial(m4);
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100),  new Point3D(200, -200, 150),
                new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Recursive Test 2", 500, 500, 500, 500);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();

    }

    @Test
    public void recursiveTest3(){

        Scene scene = new Scene("recursiveTest3");
        scene.setBackground(new Color(0, 0, 0));
        scene.setCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setScreenDistance(300);

        Sphere sphere = new Sphere(300, new Point3D(0, 0, 1000),new Color(0, 0, 100));
        Material m=new Material(1,1,0.5,0,20);
        sphere.setMaterial(m);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(50, new Point3D(0, 0, 1000),new Color(100, 20, 20));
        Material m1=new Material(1,1,0,0,20);
        sphere2.setMaterial(m1);
        scene.addGeometry(sphere2);

        Triangle triangle = new Triangle(new Point3D(  2000, 1000, 1500),
                new Point3D( -1000,  +2000, 1500),
                new Point3D(  700,  +700, 375),
                new Color(20, 20, 20));

        Triangle triangle2 = new Triangle(new Point3D(  2000, 1000, 1500),
                new Point3D( -1000,  -2000, 1500),
                new Point3D( -1000, 1000, 1500),
                new Color(20, 20, 20));

        Material m2=new Material(1,1,1,1);
        triangle.setMaterial(m2);

        Material m3=new Material(1,1,0.2,0);
        triangle2.setMaterial(m3);


        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100),  new Point3D(200, -200, 150),
                new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Recursive Test 3", 500, 500, 500, 500);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();

    }

}