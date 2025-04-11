package com.example.lab10;

import java.io.*;

import jakarta.enterprise.context.SessionScoped;
import lombok.Data;
import jakarta.inject.Named;

import javax.xml.transform.Result;

import static java.lang.Math.*;


@Named
@Data
@SessionScoped

public class LogicBean implements Serializable {


    private double startValue;
    private double endValue;
    private double stepValue;
    private int numberOfSteps;
    private double biggestY;
    private double biggestX;
    private double smallestY;
    private double smallestX;
    private double sumOfElements;
    private double averageOfElements;

    public double tabulation(double x) {
        if (x > 3.4) return sin(x) * log10(x);
        else return pow(cos(x), 2);
    }

    public int calculateSteps(double x1, double x2, double step) {
        return (int) round((x2 - x1) / step) + 1;
    }

    public double[] xArrayCreate(double x1, double x2, double step) {
        return new double[calculateSteps(x1, x2, step)];
    }

    public double[] xArrayFill(double x1, double x2, double step) {
        double[] xArray = xArrayCreate(x1, x2, step);
        for (int i = 0; i < xArray.length; i++) {
            xArray[i] = x1 + i * step;
        }
        return xArray;
    }

    public double[] yArrayFill(double[] xArray) {
        double[] yArray = new double[xArray.length];
        for (int i = 0; i < yArray.length; i++) {
            yArray[i] = tabulation(xArray[i]);
        }
        return yArray;
    }

    public int getMinIndex(double[] yArray) {
        int minIndex = 0;
        for (int i = 1; i < yArray.length; i++) {
            if (yArray[i] < yArray[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public double getMinElement(double[] yArray) {
        return yArray[getMinIndex(yArray)];
    }

    public int getMaxIndex(double[] yArray) {
        int maxIndex = 0;
        for (int i = 1; i < yArray.length; i++) {
            if (yArray[i] > yArray[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public double getMaxElement(double[] yArray) {
        return yArray[getMaxIndex(yArray)];
    }

    public double getSum(double[] yArray) {
        double sum = 0;
        for (double v : yArray) {
            sum += v;
        }
        return sum;
    }

    public double getAverage(double[] yArray) {
        return getSum(yArray) / yArray.length;
    }

    public String forRun(){
        double[] xArray = xArrayFill(startValue, endValue, stepValue);
        double[] yArray = yArrayFill(xArray);

        numberOfSteps=calculateSteps(startValue, endValue, stepValue);
        biggestY=getMaxElement(yArray);
        biggestX=getMaxIndex(yArray);
        smallestY=getMinElement(yArray);
        smallestX=getMinIndex(yArray);
        sumOfElements=getSum(yArray);
        averageOfElements=getAverage(yArray);
        return "calculate-Page";
    }
    public String returnToMainPage(){
        return "main-Page.xhtml";
    }
}
