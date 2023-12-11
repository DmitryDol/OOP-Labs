import functions.*;
import functions.basic.*;
import functions.meta.*;

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

    public static void main(String[] args) throws InappropriateFunctionPointException, IOException, ClassNotFoundException {
        Function sin = new Sin();
        Function cos = new Cos();
        for (int i = 0; i < 20 * Math.PI; ++i){
            System.out.println("sin" + (double)i/10 + ": " + sin.getFunctionValue((double)i / 10));
            System.out.println("cos" + (double)i/10 + ": " + cos.getFunctionValue((double)i / 10));
        }

        TabulatedFunction tabulatedSin = TabulatedFunctions.tabulate(sin, 0, 2*Math.PI, 10);
        TabulatedFunction tabulatedCos = TabulatedFunctions.tabulate(cos, 0, 2*Math.PI, 10);
        printTabFunc(tabulatedSin);
        printTabFunc(tabulatedCos);

        System.out.println();
        Function func = Functions.sum(Functions.power(tabulatedSin, 2), Functions.power(tabulatedCos, 2));

        for(int i = 0; i < 20*Math.PI; ++i)
            System.out.println(func.getFunctionValue((double)i / 10));

        TabulatedFunction tabulatedExp = TabulatedFunctions.tabulate(new Exp(), 0, 10, 11);

        String tabExpFilename = "tabulatedExp.txt";

        FileWriter writer = new FileWriter(tabExpFilename);
        TabulatedFunctions.writeTabulatedFunction(tabulatedExp, writer);
        writer.close();

        FileReader reader = new FileReader(tabExpFilename);
        TabulatedFunction tabulatedExpFromFile = TabulatedFunctions.readTabulatedFunction(reader);
        reader.close();

        printTabFunc(tabulatedExp);
        printTabFunc(tabulatedExpFromFile);

        TabulatedFunction tabulatedLogE = TabulatedFunctions.tabulate(new Log(Math.E), 0, 10, 11);

        String tabLogEFilename = "tabulatedLogE.txt";

        FileOutputStream outputLog = new FileOutputStream(tabLogEFilename);
        TabulatedFunctions.outputTabulatedFunction(tabulatedLogE, outputLog);
        outputLog.close();

        FileInputStream inputLog = new FileInputStream(tabLogEFilename);
        TabulatedFunction tabulatedLogEFromFile = TabulatedFunctions.inputTabulatedFunction(inputLog);
        inputLog.close();

        printTabFunc(tabulatedLogE);
        printTabFunc(tabulatedLogEFromFile);


        // Task 9. Serializable, Externalizable
        TabulatedFunction serializedTabulatedLogE = new ArrayTabulatedFunction(
                (ArrayTabulatedFunction) tabulatedLogE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("serializedLogE.txt"));
        objectOutputStream.writeObject(serializedTabulatedLogE);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("serializedLogE.txt"));
        TabulatedFunction deserializedTabulatedLogE = (ArrayTabulatedFunction)objectInputStream.readObject();
        objectInputStream.close();


        TabulatedFunction externalizedTabulatedLogE = TabulatedFunctions.tabulateLinkedList(new Log(Math.E), 0, 10, 11);
        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(
                new FileOutputStream("ExternalizedLogE.txt"));
        objectOutputStream1.writeObject(externalizedTabulatedLogE);
        objectOutputStream1.close();

        ObjectInputStream objectInputStream1 = new ObjectInputStream(
                new FileInputStream("ExternalizedLogE.txt"));
        TabulatedFunction deexternalizedTabulatedLogE = (LinkedListTabulatedFunction) objectInputStream1.readObject();
        objectInputStream1.close();

        System.out.println();
        printTabFunc(serializedTabulatedLogE);
        printTabFunc(deserializedTabulatedLogE);

        System.out.println();
        printTabFunc(externalizedTabulatedLogE);
        printTabFunc(deexternalizedTabulatedLogE);

    }
}