package Primitives;

import java.util.Objects;

public class Coordinate {
    private double _coordinate;

    //Constructors
    public Coordinate(){
        this._coordinate = 0;
    }
    public Coordinate(double coordinate){
        this._coordinate = coordinate;
    }
    public Coordinate(Coordinate toCopy){
        this._coordinate = toCopy._coordinate;
    }

    //Getter
    public double getCoordinate(){ return this._coordinate; }

    //Setter
    public void set(double coordinate) { this._coordinate = coordinate; }

    //Other Methods
    public double add(Coordinate toAdd){ return this._coordinate + toAdd._coordinate;}

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate coordinate2 = (Coordinate) o;
        return  this.getCoordinate() - coordinate2.getCoordinate() < 0.00000000000005 &&
                coordinate2.getCoordinate() - this.getCoordinate() < 0.00000000000005;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_coordinate);
    }

    @Override
    public String toString() {
        return _coordinate + "";
    }


}


