package main.java.desk.control;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

/** キャッチされなかった非検査例外(RuntimeExceptionを含む派生クラス)をキャッチするクラス
 */
public class OriginalUncaughtException extends LastException implements UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //このメソッド内で発生した例外はJava Virtual Machineにより無視されるので注意

        _LastExcepTitle = e.getClass().getName();
        _LastExcepPlace = Thread.currentThread().getStackTrace()[1].getMethodName();
        _LastExcepParam = "";
        _LastExcepMessage = e.getMessage();
        var sw = new StringWriter();
        try(var pw = new PrintWriter(sw);) {
            e.printStackTrace(pw);
            pw.flush();
            _LastExcepTrace = sw.toString();
        }

        logWrite();
    }
}