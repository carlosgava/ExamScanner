package br.com.gservices.examscanner.activities;

import android.support.v7.app.AppCompatActivity;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener {
    private CameraView cameraView;
    private Mat edgesMat;
    private final Scalar greenScalar = new Scalar(0,255,0);
    private int resolutionIndex = 0;
    private MatVideoWriter matVideoWriter = new MatVideoWriter();
}
