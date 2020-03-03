package org.mvnsearch.jfr;

import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

/**
 * My JFR Event
 *
 * @author linux_china
 */
@Name("com.oracle.foo.CoolThing")
@Label("Cool Thing")
public class MyJFREvent extends Event {
    @Label("Message")
    private String message;
    @Label("Value")
    private int value;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
