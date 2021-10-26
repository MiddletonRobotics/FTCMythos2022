package MiddletonLibrary.Math;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import java.util.*;

import MiddletonLibrary.Odometry.WayPoint;

import static MiddletonLibrary.Math.Polynomial.getArcLength;
import static MiddletonLibrary.Resources.Utils.negate;

/**
 * Created by Keval Kataria on 3/6/2021
 */

public class Spline {
    public static PolynomialSplineFunction getSpline(WayPoint... points) {
        PolynomialSplineFunction[] potentials = getPotentialSplines(points);
        double maxX = points[points.length - 1].getX();

        PolynomialSplineFunction function = potentials[0];
        for(PolynomialSplineFunction spline : potentials) if(getArcLength(spline, maxX) < getArcLength(function, maxX)) function = spline;

        return function;
    }

    public static PolynomialSplineFunction[] getPotentialSplines(WayPoint... points) {
        double[] x = new double[points.length];
        double[] y = new double[points.length];
        List<PolynomialSplineFunction> functions = new ArrayList<>();
        SplineInterpolator interpolator = new SplineInterpolator();

        x[0] = y[0] = 0;
        for(int i = 0; i< points.length; i++) x[i + 1] = points[i].getX();
        for(int i = 0; i< points.length; i++) y[i + 1] = points[i].getY();

        try{functions.add(interpolator.interpolate(x, y));} catch(Exception e) {e.printStackTrace();}
        try{functions.add(interpolator.interpolate(y, x));} catch(Exception e) {e.printStackTrace();}
        try{functions.add(interpolator.interpolate(negate(y), x));} catch(Exception e) {e.printStackTrace();}
        try{functions.add(interpolator.interpolate(y,negate(x)));} catch(Exception e) {e.printStackTrace();}
        try{functions.add(interpolator.interpolate(negate(y),negate(x)));} catch(Exception e) {e.printStackTrace();}

        return functions.toArray(new PolynomialSplineFunction[0]);
    }
}