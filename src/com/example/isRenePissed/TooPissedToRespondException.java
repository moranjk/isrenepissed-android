package com.example.isRenePissed;

/**
 * Created with IntelliJ IDEA.
 * User: jmoran
 * Date: 5/31/13
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class TooPissedToRespondException extends Exception {
    public TooPissedToRespondException(String message)
    {
        super(message);
    }
}
