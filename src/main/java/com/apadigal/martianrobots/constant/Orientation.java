/*
 * Swash Tech Ltd.
 *
 * Oriantation.java
 *
 * © 2018 Swash Tech Ltd. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.apadigal.martianrobots.constant;
// ---- Import Statements -----------------------------------------------------

public enum Orientation {
    N,
    E,
    W,
    S;

    public Orientation turnLeft(){
        switch (this){
            case N:
                return W;
            case E:
                return N;
            case W:
                return S;
            case S:
                return E;
        }
        return this;
    }

    public Orientation turnRight(){
        switch (this){
            case N:
                return E;
            case E:
                return S;
            case W:
                return N;
            case S:
                return W;
        }
        return null;
    }

}