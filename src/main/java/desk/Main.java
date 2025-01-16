package main.java.desk;

import main.java.desk.control.OriginalUncaughtException;
import main.java.desk.control.Frame1Control;

/** エントリーポイントクラス
 @author none **/
public class Main implements Runnable {

    public static void main(String[] args) {
        var thread = new Thread(new Main());
        thread.setUncaughtExceptionHandler(new OriginalUncaughtException());
        thread.start();
    }

    @Override
    public void run() {
        var f = new Frame1Control();
        f.goEvent();
    }
}