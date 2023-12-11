import functions.*;


public class Main {

    /**
     * print coordinates of all function`s points
     */
    public static void printTabFunc(TabulatedFunction func){
        for(int i = 0; i < func.getPointsCount() + 1; ++i)
            System.out.println(func.getPointX(i) + " " + func.getPointY(i));
        System.out.println();
    }

    public static void main(String[] args) {
        TabulatedFunction func = new TabulatedFunction(-7, 7, 15);
        printTabFunc(func);
        for (int i = 0; i < 12; ++i)
            func.deletePoint(0);
        printTabFunc(func);

        func = new TabulatedFunction(0,0,1);
        for (int i = 1; i < 30; ++i){
            func.addPoint(new FunctionPoint(i, i));
        }
        printTabFunc(func);

        System.out.println();
        double[] functionValues = {5, 4, 3, 2, 1};
        TabulatedFunction func2 = new TabulatedFunction(1, 5, functionValues);
        printTabFunc(func2);

    }
}