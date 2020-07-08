/*
 * Swash Tech Ltd.
 *
 * MartianRobotsException.java
 *
 * © 2018 Swash Tech Ltd. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.apadigal.martianrobots.exception;
// ---- Import Statements -----------------------------------------------------

public class MartianRobotsException extends  RuntimeException{
    public MartianRobotsException() {
        super();
    }

    public MartianRobotsException(String message) {
        super(message);
    }

    public MartianRobotsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MartianRobotsException(Throwable cause) {
        super(cause);
    }

    protected MartianRobotsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}