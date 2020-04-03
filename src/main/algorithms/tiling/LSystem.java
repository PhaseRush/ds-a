package tiling;

import processing.core.PApplet;

public class LSystem extends PApplet {
    int steps = 0;

    protected String axiom;
    protected String rule;
    protected String production;

    protected float startLength;
    protected float drawLength;
    protected float theta;

    protected int generations;

    protected LSystem() {
        axiom = "F";
        rule = "F+F-F";
        startLength = 190.0f;
        theta = radians(120.0f);
    }

    protected LSystem(String axiom, String rule, float startLength, float theta) {
        this.axiom = axiom;
        this.rule = rule;
        this.startLength = startLength;
        this.theta = theta;
        reset();
    }


    void reset() {
        production = axiom;
        drawLength = startLength;
        generations = 0;
    }

    int getAge() {
        return generations;
    }

    void render() {
        translate(width / 2, height / 2);
        steps += 5;
        if (steps > production.length()) {
            steps = production.length();
        }
        for (int i = 0; i < steps; i++) {
            char step = production.charAt(i);
            if (step == 'F') {
                rect(0, 0, -drawLength, -drawLength);
                noFill();
                translate(0, -drawLength);
            } else if (step == '+') {
                rotate(theta);
            } else if (step == '-') {
                rotate(-theta);
            } else if (step == '[') {
                pushMatrix();
            } else if (step == ']') {
                popMatrix();
            }
        }
    }

    protected void simulate(int gen) {
        while (getAge() < gen) {
            production = iterate(production, rule);
        }
    }

    String iterate(String prod_, String rule_) {
        drawLength = drawLength * 0.6f;
        generations++;
        String newProduction = prod_;
        newProduction = newProduction.replaceAll("F", rule_);
        return newProduction;
    }
}
