package gdb.shapesperimetersexercise;

/**
 * @date Tuesday December 10, 2019
 * @author Garrett Becker
 */

public class Square extends Shape {
    protected double sideLength;

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }
    
    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return sideLength * 4;
    }
    
}
