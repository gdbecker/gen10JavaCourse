package gdb.shapesperimetersexercise;

/**
 * @date Tuesday December 10, 2019
 * @author Garrett Becker
 */

public class Circle extends Shape {
    protected final double PI = 3.14;
    protected double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double getPerimeter() {
        return 2 * PI * radius;
    }

    @Override
    public double getArea() {
        return PI * radius * radius;
    }
    
}
