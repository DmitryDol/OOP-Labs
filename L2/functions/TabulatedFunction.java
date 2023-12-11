package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int end;

    /**
     * Constructor for function with all y`s equals to 0
     * @param leftX left domain border of function 
     * @param rightX right domain border of function
     * @param pointsCount amount of points where function is defined
     */
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        end = pointsCount - 1;
        double step = (rightX - leftX)/ (pointsCount - 1 + ((leftX == rightX) ? 1 : 0));
        int size = (int)Math.pow(2, ((int)(Math.log10(pointsCount)/
                Math.log10(2)) + 1));
        this.points = new FunctionPoint[size];
        for (int i = 0; i < pointsCount; ++i)
            this.points[i] = new FunctionPoint(leftX + i*step,0);

    }

    /**
     * 
     * @param leftX left domain border of function 
     * @param rightX right domain border of function
     * @param values array of Y values of function
     */
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        end = values.length - 1;
        double step = (rightX - leftX)/ (values.length - 1 + ((leftX == rightX) ? 1 : 0));
        int size = (int)Math.pow(2, ((int)(Math.log10(values.length)/
                Math.log10(2)) + 1));
        this.points = new FunctionPoint[size];
        for (int i = 0; i < values.length; ++i)
            this.points[i] = new FunctionPoint(leftX + i*step, values[i]);
    }

    public double getLeftDomainBorder() {
        return this.points[0].getX();
    }
    public double getRightDomainBorder() {
        return this.points[end].getX();
    }

    /**
     * in case where searchingX isn`t in this.points return index where need to enter point with searchingX coordinates
     * @return index of point with x equals to searchingX in this.points
     */
    private int binarySearch(double searchingX){
        int left = -1;
        int right = this.end + 1;
        while (left + 1 < right){
            int middle = (left + right) / 2;
            if (this.points[middle].getX() < searchingX)
                left = middle;
            else
                right = middle;
        }
        return right;
    }

    /**
     * find value of function in point x
     * @param x must be in domain
     * @return value of f(x)
     */
    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder())
            return Double.NaN;

        int i = binarySearch(x);
        FunctionPoint point1 = this.points[i-1];
        FunctionPoint point2 = this.points[i];
        return (point2.getY() - point1.getY()) * (x - point1.getX()) /
                (point2.getX() - point1.getX()) + point1.getY();
    }

    /**
     * @return amount of points
     */
    public  int getPointsCount() {
        return this.end;
    }

    /**
     * @param index from 0 to points count
     * @return class FunctionPoint
     */
    public FunctionPoint getPoint(int index){
        return this.points[index];
    }

    /**
     * @param index of point in this.points
     * @param point class Function point with new x and y values
     */
    public void setPoint(int index, FunctionPoint point){
        if ((point.getX() <= this.points[index-1].getX()) ||
                (point.getX() >= this.points[index+1].getX()))
            return;
        this.points[index].setPoint(point);
    }

    /**
     * @return x coordinate of this.points[index]
     */
    public double getPointX(int index){
        return this.points[index].getX();
    }

    /**
     * set x value for this.points[index]
     * @param x new x
     */
    public void setPointX(int index, double x){
        this.points[index].setX(x);
    }

    /**
     * @return y coordinate of this.points[index]
     */
    public double getPointY(int index){
        return this.points[index].getY();
    }
    /**
     * set y value for this.points[index]
     * @param y new y
     */
    public void setPointY(int index, double y){
        this.points[index].setY(y);
    }

    /**
     * delete point in specific index
     * @param index index of deleting point from this.points
     */
    public void deletePoint(int index){
        if (end <= this.points.length / 4){
            FunctionPoint[] old = this.points;
            this.points = new FunctionPoint[old.length/2];
            System.arraycopy(old, 0, this.points, 0,end + 1);
        }
        System.arraycopy(this.points, index+1, this.points, index, end - index + 1);
        end -= 1;
    }

    /**
     * add point to function
     * @param point object of FunctionPoint class with x and y coordinates
     */
    public void addPoint(FunctionPoint point){
        if (this.end == this.points.length - 1) {
            FunctionPoint[] old = this.points;
            this.points = new FunctionPoint[2*old.length];
            System.arraycopy(old,0, this.points,0, old.length);
        }
        int ind = binarySearch(point.getX());
        if (ind != this.end + 1) {
            if (this.points[ind].getX() == point.getX()){
                this.points[ind].setY(point.getY());
                this.end -= 1;
            }
            System.arraycopy(this.points, ind, this.points, ind+1, end - ind + 1);
        }
        this.points[ind] = new FunctionPoint(point);
        this.end += 1;
    }

}
