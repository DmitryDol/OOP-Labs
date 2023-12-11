import functions.*;

import java.io.*;

public class Main {

    /**
     * print coordinates of all function`s points
     */
    public static void printTabFunc(TabulatedFunction func){
        for(int i = 0; i < func.getPointsCount(); ++i)
            System.out.println(func.getPointX(i) + " " + func.getPointY(i));
        System.out.println();
    }

    public static void main(String[] args) throws InappropriateFunctionPointException, IOException,
            ClassNotFoundException, CloneNotSupportedException {

        TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(0, 10, 11);
        TabulatedFunction diffArrayTabulatedFunction = new ArrayTabulatedFunction(0, 11, 12);
        TabulatedFunction  linkedListTabulatedFunction = new LinkedListTabulatedFunction(0, 10, 11);

        // .toString() test
        System.out.println(arrayTabulatedFunction);
        System.out.println(linkedListTabulatedFunction);

        // .equals() test (2 true, 2 false)
        System.out.println(arrayTabulatedFunction.equals(arrayTabulatedFunction));
        System.out.println(arrayTabulatedFunction.equals(linkedListTabulatedFunction));

        System.out.println(arrayTabulatedFunction.equals(diffArrayTabulatedFunction));
        System.out.println(diffArrayTabulatedFunction.equals(linkedListTabulatedFunction));

        // .hashcode() test
        System.out.println(arrayTabulatedFunction.hashCode() + "\n" +
                           linkedListTabulatedFunction.hashCode() + "\n" +
                           diffArrayTabulatedFunction.hashCode());
        System.out.println();
        System.out.println(linkedListTabulatedFunction.hashCode());
        linkedListTabulatedFunction.setPoint(0, new FunctionPoint(0.001,0));
        System.out.println(linkedListTabulatedFunction.hashCode());

        // .clone() test
        ArrayTabulatedFunction cloned = (ArrayTabulatedFunction) arrayTabulatedFunction.clone();
        cloned.addPoint(new FunctionPoint(12, 10));
        System.out.println(arrayTabulatedFunction);
        System.out.println(cloned);
    }
}