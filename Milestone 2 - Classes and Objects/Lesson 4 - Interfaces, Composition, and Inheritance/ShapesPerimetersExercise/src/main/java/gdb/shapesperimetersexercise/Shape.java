package gdb.shapesperimetersexercise;

/**
 * @date Tuesday December 10, 2019
 * @author Garertt Becker
 */
abstract class Shape {
    protected String color;

    //Getters and setters
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    //Methods to be inherited by other objects
    public double getArea() {
        return 0;
    }
    
    public double getPerimeter() {
        return 0;
    }
}