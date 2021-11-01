package Primitives;

public class Coordinate {
    private double _coordinate;

    public Coordinate(){
        this._coordinate = 0;
    }
    public Coordinate(double coordinate){
        this._coordinate = coordinate;
    }
    public Coordinate(Coordinate toCopy){
        this._coordinate = toCopy._coordinate;
    }

    public double getCoordinate(){ return this._coordinate; }
    public void set(double coordinate) { this._coordinate = coordinate; }
    public boolean equals(Coordinate coordinate2){
        return this.getCoordinate()== coordinate2.getCoordinate();
    }
    public double add(Coordinate toAdd){ return this._coordinate + toAdd._coordinate;}

    @Override
    public String toString() {
        return _coordinate + "";
    }


}

