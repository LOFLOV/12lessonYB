package geekbrains;

public class MainApp {

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    static class MyThread extends Thread {
        @Override
        public void run() {
            solveSecondMethod();
        }
    }
    public static void main(String[] args) {
        new MyThread().start();
        solveFirstMethod();


    }

    private static void solveFirstMethod() {
        float[] arr = new float[SIZE];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for(int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));;
        }
        System.out.println("Главый поток цикл расчета: " + (System.currentTimeMillis() - a));
    }

    private static void solveSecondMethod() {
        float[] arr = new float[SIZE];
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long x = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);
        long a = System.currentTimeMillis();
        System.out.println("Поток №2 время разбивки массива: " + (a - x));
        for(int i = 0; i < a1.length; i++) {
            a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long b = System.currentTimeMillis();
        System.out.println("Поток №2 просчет масива а1: " + (System.currentTimeMillis() - a));
        for(int i = 0; i < a2.length; i++) {
            a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long d = System.currentTimeMillis();
        System.out.println("Поток №2 просчет масива а2: " + (System.currentTimeMillis() - b));

        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(a2, 0, arr, HALF, HALF);
        long c = System.currentTimeMillis();
        System.out.println("Поток №2 склейка масивов: " + (System.currentTimeMillis() - b));
    }
}
