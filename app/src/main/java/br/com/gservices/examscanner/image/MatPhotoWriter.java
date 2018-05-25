package br.com.gservices.examscanner.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MatPhotoWriter {
    boolean recording;
    File dir;
    int imageIndex = 0;

    public void start(File dir){
        this.dir = dir;
        recording = true;
    }

    public void stop(){
        recording = false;

        try{
            File file = new File(dir, "video.mp4");
            SequenceEncoder encoder = new SequenceEncoder(file);

            List<File> files = Arrays.asList(dir.listFiles());
            Collections.sort(files, new Comparator<File>(){
                @Override
                public int compare(File lhs, File rhs) {
                    return lhs.getName().compareTo(rhs.getName());
                }
            });

            for(File f : files){
                Log.i("VideoTest", "Encoding image: " + f.getAbsolutePath());
                try{
                    Bitmap frame = BitmapFactory.decodeFile(f.getAbsolutePath());
                    encoder.encodeImage(frame);
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
            encoder.finish();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void write(Mat mat){

        //convert from BGR to RGB
        Mat rgbMat = new Mat();
        Imgproc.cvtColor(mat, rgbMat, Imgproc.COLOR_BGR2RGB);

        File file = new File(dir, "img" + imageIndex + ".png");

        String filename = file.toString();
        boolean success = Highgui.imwrite(filename, rgbMat);

        Log.i("VideoTest", "Success writing img" + imageIndex +".png: " + success);

        imageIndex++;
    }

    public boolean isRecording() {
        return recording;
    }
}
