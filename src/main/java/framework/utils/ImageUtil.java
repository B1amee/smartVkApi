package framework.utils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ImageUtil {

    private static final Logger log = LoggerFactory.getLogger(ImageUtil.class);

    public static boolean isEqualsImages(String url, String path, int deviation) throws IOException {
        log.info("Check photo by " + url);
        nu.pattern.OpenCV.loadLocally(); // loadShare
        Path vkPhoto = Files.createTempFile(Path.of("./target"), "temp", ".jpg");
        Files.copy(new URL(url).openStream(), vkPhoto, StandardCopyOption.REPLACE_EXISTING);
        String filePath = vkPhoto.toFile().getPath();
        Mat mat1 = Imgcodecs.imread(filePath, Imgcodecs.IMREAD_GRAYSCALE);
        Mat mat2 = Imgcodecs.imread(path, Imgcodecs.IMREAD_GRAYSCALE);
        Mat diff = new Mat();
        Core.absdiff(mat1, mat2, diff);
        int diffPercent = (int) (Core.countNonZero(diff) * 1.0 / (mat1.width() * mat1.height()) * 100);
        vkPhoto.toFile().deleteOnExit();
        return diffPercent < deviation;
    }

}
