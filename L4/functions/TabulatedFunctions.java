package functions;

import java.io.*;

public abstract class TabulatedFunctions {
    public static TabulatedFunction tabulate(Function function, double leftX, double rightX, int pointsCount) throws IllegalArgumentException{
        if (leftX < function.getLeftDomainBorder() || rightX > function.getRightDomainBorder())
            throw new IllegalArgumentException();
        FunctionPoint[] points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX)/ (pointsCount - 1);
        for (int i = 0; i < pointsCount; ++i)
            points[i] = new FunctionPoint(leftX + i*step, function.getFunctionValue(leftX + i*step));
        return new ArrayTabulatedFunction(points);
    }
    public static TabulatedFunction tabulateLinkedList(Function function, double leftX, double rightX, int pointsCount) throws IllegalArgumentException{
        if (leftX < function.getLeftDomainBorder() || rightX > function.getRightDomainBorder())
            throw new IllegalArgumentException();
        FunctionPoint[] points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX)/ (pointsCount - 1);
        for (int i = 0; i < pointsCount; ++i)
            points[i] = new FunctionPoint(leftX + i*step, function.getFunctionValue(leftX + i*step));
        return new LinkedListTabulatedFunction(points);
    }
    public static void outputTabulatedFunction(TabulatedFunction function, OutputStream out) throws IOException {
        DataOutputStream dataOut = new DataOutputStream(out);
        int pointsCount = function.getPointsCount();
        dataOut.writeInt(pointsCount);
        for(int i = 0; i < pointsCount; ++i){
            dataOut.writeDouble(function.getPointX(i));
            dataOut.writeDouble(function.getPointY(i));
        }
        dataOut.flush();
    }

    public static TabulatedFunction inputTabulatedFunction(InputStream in) throws IOException {
        DataInputStream dataInput = new DataInputStream(in);

        int pointCount =  dataInput.readInt();
        FunctionPoint[] points = new FunctionPoint[pointCount];
        for (int i = 0; i < pointCount; ++i){
            double x = dataInput.readDouble();
            double y = dataInput.readDouble();
            points[i] = new FunctionPoint(x, y);
        }
        dataInput.close();
        return new ArrayTabulatedFunction(points);
    }
    public static void writeTabulatedFunction(TabulatedFunction function, Writer out) throws IOException {
        int pointCount = function.getPointsCount();
        out.write(pointCount + " ");
        for(int i = 0; i < pointCount; ++i){
            out.write(function.getPointX(i) + " " + function.getPointY(i) + " ");
        }
    }
    public static TabulatedFunction readTabulatedFunction(Reader in) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.nextToken();
        FunctionPoint[] points = new FunctionPoint[(int)tokenizer.nval+1];
        for(int i = 0; i < points.length; ++i){
            tokenizer.nextToken();
            double x = tokenizer.nval;
            tokenizer.nextToken();
            points[i] = new FunctionPoint(x, tokenizer.nval);
        }
        return new ArrayTabulatedFunction(points);
    }

}
