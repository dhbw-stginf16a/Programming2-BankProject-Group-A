package de.dhbw.stginf16a.bankproject.groupa.data;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by leons on 5/23/17.
 */
public class BankDataStoreSerializationException extends Exception {
    private Exception occuredException = null;

    public BankDataStoreSerializationException(boolean deserializing, String message) {
        super(String.format(
                "An error occured while %sserializing the BankDataStore: \"%s\"",
                deserializing ? "de" : "",
                message
        ));
    }

    public static String getStackTraceAsString(Exception e) {
        StringWriter stackTraceStringWriter = new StringWriter();
        PrintWriter stackTracePrintWriter = new PrintWriter(stackTraceStringWriter);
        e.printStackTrace(stackTracePrintWriter);
        return stackTraceStringWriter.toString();
    }

    public BankDataStoreSerializationException(boolean deserializing, Exception occuredException) {
        super(String.format(
                "A \"%s\" occured while %sseralizing the BankDataStore:\n%s",
                occuredException.getClass().getName(),
                deserializing ? "de" : "",
                getStackTraceAsString(occuredException)
        ));

        this.occuredException = occuredException;
    }

    public Exception getOccuredException() {
        return occuredException;
    }
}
