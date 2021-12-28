package Primitives;

import java.util.Objects;

public class Material {
    private double _kd = 0d;
    private double _ks = 0d;
    private int _nShininess = 1;

    //Constructors
    public Material(Material material) {
        _kd = material.getKd();
        _ks = material.getKs();
        _nShininess = material.getShininess();
    }

    public Material() {
        _kd = 0d;
        _ks = 0d;
    }

    public Material(double kd, double ks, int nS) {
        _kd = kd;
        _ks = ks;
        _nShininess = nS;
    }

    /**
     * Diffusion attenuation factor getter/setter
     */
    public double getKd() {
        return _kd;
    }

    public void setKd(double kd) {
        _kd = kd;
    }

    /**
     * Specular attenuation factor getter/setter
     */
    public double getKs() {
        return _ks;
    }

    public void setKs(double ks) {
        _ks = ks;
    }

    /**
     * Shininess level getter/setter
     */
    public int getShininess() {
        return _nShininess;
    }

    public void setShininess(int nShininess) {
        _nShininess = nShininess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Double.compare(material._kd, _kd) == 0 && Double.compare(material._ks, _ks) == 0 && _nShininess == material._nShininess;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_kd, _ks, _nShininess);
    }
}
