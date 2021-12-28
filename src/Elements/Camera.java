package Elements;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.Objects;

public class Camera {
    private Point3D _P0;
    private Vector _vUp;
    private Vector _vTo;
    private Vector _vRight;

    //Constructors
    public Camera() {
        this._P0 = new Point3D();
        this._vUp = new Vector(0, -1, 0);
        this._vTo = new Vector(0, 0, 1);
        this._vRight = new Vector(1, 0, 0);
    }

    public Camera(Point3D p0, Vector to, Vector up){
        this.setP0(p0);
        this.setVTo(to.normalize());
        this.setVUp(up.normalize());
        this.setVRight(to.crossProduct(up).normalize());
    }

    //Getters
    public Point3D getP0() { return _P0; }
    public Vector getVUp() { return _vUp; }
    public Vector getVRight() { return _vRight; }
    public Vector getVTo() { return _vTo; }

    //Setters
    public void setP0(Point3D _P0) { this._P0 = _P0; }
    public void setVUp(Vector _vUp) { this._vUp = _vUp; }
    public void setVRight(Vector _vRight) { this._vRight = _vRight; }
    public void setVTo(Vector _vTo) { this._vTo = _vTo; }

    //Other Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camera camera = (Camera) o;
        return Objects.equals(_P0, camera._P0) && Objects.equals(_vUp, camera._vUp) && Objects.equals(_vTo, camera._vTo) && Objects.equals(_vRight, camera._vRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_P0, _vUp, _vTo, _vRight);
    }

    @Override
    public String toString() {
        return "Camera" +
                " P0:" + _P0 +
                ", vUp:" + _vUp +
                ", vTo:" + _vTo +
                ", vRight:" + _vRight;
    }

    public Ray constructRayThroughPixel(int nX, int nY, int j, int i,
                                        double screenDistance, double screenWidth, double screenHeight){
        Point3D pCenter = new Point3D( this._P0.add( this._vTo.scale( screenDistance ) ) );
        double rY = screenHeight / nY;
        double rX = screenWidth / nX;
        double yI = (i - nY/2.0 + 0.5) * rY;
        double xJ = (j - nX/2.0 + 0.5) * rX;
        Point3D pIJ = pCenter;
        if(xJ != 0) pIJ = pIJ.add(this._vRight.scale(xJ));
        if(yI != 0) pIJ = pIJ.add(this._vUp.scale(-yI));
        Vector vIJ = new Vector(pIJ.subtract(this._P0));
        return new Ray(this._P0,vIJ.normalize());
    }
}
