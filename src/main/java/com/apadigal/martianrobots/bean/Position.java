/*
 * Swash Tech Ltd.
 *
 * Position.java
 *
 * © 2018 Swash Tech Ltd. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.apadigal.martianrobots.bean;
// ---- Import Statements -----------------------------------------------------

import java.util.Objects;

public class Position {
    private int positionX;
    private int positionY;

    public static PositionBuilder builder() {
        return new PositionBuilder();
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }


    public static final class PositionBuilder {
        private int positionX;
        private int positionY;

        private PositionBuilder() {
        }


        public PositionBuilder positionX(int positionX) {
            this.positionX = positionX;
            return this;
        }

        public PositionBuilder positionY(int positionY) {
            this.positionY = positionY;
            return this;
        }

        public Position build() {
            Position position = new Position();
            position.setPositionX(positionX);
            position.setPositionY(positionY);
            return position;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return positionX == position.positionX &&
                positionY == position.positionY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionX, positionY);
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }
}