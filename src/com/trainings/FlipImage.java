package com.trainings;

import acm.graphics.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Tigra
 * Date: 2/11/12
 * Time: 7:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class FlipImage {
    private GImage inputImage;
    private GImage outputImage;
    
    FlipImage(String fileName) {
        inputImage = new GImage(fileName);
    }

    public void flipHorizontal() {
        int[][] array = getInputImage().getPixelArray();
        int width = array[0].length;
        int height = array.length;
        for (int line = 0; line < height; line++) {
            for (int subArray = 0; subArray < width/2; subArray++) {
                int p2 = width - subArray - 1;
                int temp = array[line][subArray];
                array[line][subArray] = array[line][p2];
                array[line][p2] = temp;
            }
        }
        setOutputImage(new GImage(array));
    }

    public void saveOutputImage(){
        Image image = getOutputImage().getImage();
        BufferedImage bufferedImage = convertToBufferedImage(image);
        saveToFile(bufferedImage);
    }

    private void saveToFile(BufferedImage bufferedImage){
        try {
            File outputfile = new File("files/saved.jpg");
            ImageIO.write(bufferedImage, "jpg", outputfile);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private BufferedImage convertToBufferedImage(Image image) {
        int width = image.getWidth(null);  
        int height = image.getHeight(null);
        BufferedImage bufferedImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return bufferedImage;
    }

    public GImage getInputImage(){
        return inputImage;
    }
    public GImage getOutputImage(){
        return outputImage;
    }
    public void setOutputImage(GImage image){
        this.outputImage = image;
    }
    public static void main(String[] args) {
        FlipImage flipImage = new FlipImage("files/milk.jpg");
        flipImage.flipHorizontal();
        flipImage.saveOutputImage();
    }
}