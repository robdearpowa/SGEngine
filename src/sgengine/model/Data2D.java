/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.model;

/**
 *
 * @author pi
 */
public class Data2D {

    public static Data2D ONE = new Data2D(1, 1);
    public static Data2D ZERO = new Data2D(0, 0);

    private int x;
    private int y;

    public Data2D() {
        x = 0;
        y = 0;
    }

    public Data2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void plus(Data2D other) {
        this.x += other.x;
        this.y += other.y;
    }

    public void plus(int other) {
        this.x += other;
        this.y += other;
    }

    public void multiply(Data2D other) {
        this.x *= other.x;
        this.y *= other.y;
    }

    public void multiply(int other) {
        this.x *= other;
        this.y *= other;
    }

    public Data2D negative() {
        return new Data2D(-this.x, -this.y);
    }
}
