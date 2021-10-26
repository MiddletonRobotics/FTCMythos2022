package MiddletonLibrary.Vision;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.*;

import static MiddletonLibrary.Resources.Utils.getHardwareMap;
import static java.util.Locale.US;
import static org.openftc.easyopencv.OpenCvCameraRotation.UPRIGHT;

/**
 * Created by Keval Kataria on 6/1/2020
 */

public class Camera {
    private OpenCvWebcam camera;
    public CVDetector detector;
    private boolean streaming = false;

    public Camera(CVDetector detector) {
        this.detector = detector;
        int cameraMonitorViewId = getHardwareMap().appContext.getResources().getIdentifier("cameraMonitorViewId", "id", getHardwareMap().appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(getHardwareMap().get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        camera.setPipeline(detector);
    }

    public void start(int width, int height, OpenCvCameraRotation rotation) {
        detector.setResolution(width, height);
        camera.openCameraDevice();
        camera.startStreaming(width, height, rotation);
        streaming = true;
    }

    public void start(OpenCvCameraRotation rotation) {
        start(1280, 960, rotation);
    }

    public void start() {start(UPRIGHT);}

    public void stop() {
        camera.stopStreaming();
        camera.closeCameraDevice();
        streaming = false;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(US, "Webcam:\nStreaming: %s", streaming ? "Yes" :"No");
    }
}