package utils;

import org.apache.commons.io.FileUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class OpenCvUtil {

    private static final Logger log = LoggerFactory.getLogger(OpenCvUtil.class);

    public static boolean checkPhoto(String url, String path) throws IOException {
        log.info("Check photo by " + url);
        nu.pattern.OpenCV.loadShared();
        File vkPhoto = Files.createTempFile(Path.of("./target"), "temp", ".jpg").toFile();
        FileUtils.copyURLToFile(new URL(url), vkPhoto);
        String filePath = vkPhoto.getPath();
        Mat mat1 = Imgcodecs.imread(filePath, Imgcodecs.IMREAD_GRAYSCALE);
        Mat mat2 = Imgcodecs.imread(path, Imgcodecs.IMREAD_GRAYSCALE);
        Mat diff = new Mat();
        Core.absdiff(mat1, mat2, diff);
        int diffPercent = (int) (Core.countNonZero(diff) * 1.0 / (mat1.width() * mat1.height()) * 100);
        vkPhoto.deleteOnExit();
        return diffPercent < 20;
    }

}
