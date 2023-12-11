package functions;

import java.io.Serializable;

public class FunctionPoint implements Serializable {
    private double x,y;
    public FunctionPoint(double x, double y){
        this.x = x;
        this.y = y;
    }
    public FunctionPoint(FunctionPoint point){
        this.x = point.x;
        this.y = point.y;
    }
    FunctionPoint(){
        this.x = 0;
        this.y = 0;
    }

    /**
     * @return x coordinate of point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return y coordinate of point
     */
    public double getY() {
        return this.y;
    }

    /**
     * set new coordinate for point using coordinates from another point
     * @param point point concluding new coordinate
     */
    public void setPoint(FunctionPoint point){
        this.x = point.getX();
        this.y = point.getY();
    }

    /**
     * set new x
     * @param x new x coordinate
     */
    public void setX(double x){
        this.x = x;
    }

    /**
     * set new y
     * @param y new y coordinate
     */
    public void setY(double y){
        this.y = y;
    }
}
