package MiddletonLibrary.Vision;

import org.opencv.core.Mat;

/**
 * Created by Keval Kataria on 3/15/2021
 */

public abstract class CVColorFilter {
    public abstract void process(Mat input, Mat mask);
}