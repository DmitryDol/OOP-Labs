import functions.*;
import functions.basic.Exp;
import functions.basic.Log;
import threads.*;
import java.lang.Thread;
import java.util.concurrent.Semaphore;


public class Main {
    public static void nonThread(){
        Task task = new Task();
        task.taskAmount = 100;
        for (int i = 0; i < task.taskAmount; ++i){
            task.functionToIntegrate = new Log(1 + Math.random() * 9);
            task.leftX = Math.random() * 100;
            task.rightX = 100 + Math.random() * 100;
            task.deltaX = Math.random();
            Functions.integrate(task.functionToIntegrate, task.leftX, task.rightX, task.deltaX);
            System.out.println("Source " + task.leftX + " " +
                    task.rightX + " " + task.deltaX);

        }
    }

    public static void simpleThreads(){
        System.out.println("Task3");
        Task task = new Task();
        task.taskAmount = 100;
        task.functionToIntegrate = new Log(Math.E);
        task.deltaX = 0.1;
        task.leftX = 10;
        task.rightX = 150;
        Thread generatorThread = new Thread(new SimpleGenerator(task));
        Thread integratorThread = new Thread(new SimpleIntegrator(task));
//        generatorThread.setPriority(1);
//        integratorThread.setPriority(10);
        generatorThread.start();
        integratorThread.start();

    }

    public static void complicatedThreads() throws InterruptedException {
        System.out.println("Task4");
        Task task = new Task();
        task.taskAmount = 1000;
        task.functionToIntegrate = new Log(Math.E);
        task.deltaX = 0.1;
        task.leftX = 10;
        task.rightX = 150;
        Sem sem = new Sem();
        Thread generatorThread = new Generator(task, sem);
        Thread integratorThread = new Integrator(task, sem);
        generatorThread.start();
        integratorThread.start();

        Thread.sleep(50);

        generatorThread.interrupt();
        integratorThread.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        Function exp = new Exp();
        System.out.println(Functions.integrate(exp, 0, 1, 0.01));
        nonThread();
        complicatedThreads();

    }
}