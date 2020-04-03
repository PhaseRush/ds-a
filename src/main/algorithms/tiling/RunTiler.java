package tiling;

import processing.core.PApplet;
import tiling.subsystems.PenroseSystem;

public class RunTiler extends PApplet {

    LSystem ds;

    public void setup() {
        size(640, 360);
        ds = new PenroseSystem();
        ds.simulate(4);
    }

    public void draw() {
        background(0);
        ds.render();
    }

    public static void main(String[] args) {
        PApplet.main("main.algorithms.tiling.RunTiler");
    }

}
