package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.net.URL;

public class ImageConvertor implements TextGraphicsConverter {
    private int maxWidth = Integer.MAX_VALUE;
    private int maxHeight = Integer.MAX_VALUE;
    private double maxRatio = Double.MAX_VALUE;
    private TextColorSchema schema = new TextColorSchemaImpl();


    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        checkAspectRatio(img);
        img = checkSizeColor(img);
        return imageToString(img);
    }

    @Override
    public void setMaxWidth(int width) {
        maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }

    public void checkAspectRatio(BufferedImage img) throws BadImageSizeException {
        double width = img.getWidth();
        double height = img.getHeight();
        double aspectRatio = width > height ? width / height : height / width;
        if (aspectRatio > maxRatio) throw new BadImageSizeException(aspectRatio, maxRatio);
    }

    public BufferedImage checkSizeColor(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        if (width > maxWidth) {
            double delim = (double) maxWidth / (double) width;
            height = (int) (delim * height);
            width = (int) (delim * width);
        }
        if (height > maxHeight) {
            double delim = (double) maxHeight / (double) height;
            height = (int) (delim * height);
            width = (int) (delim * width);
        }
        Image scaledImage = img.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        return bwImg;
    }

    public String imageToString(BufferedImage img) {
        WritableRaster bwRaster = img.getRaster();
        int height = img.getHeight();
        int width = img.getWidth();
        StringBuilder builder = new StringBuilder();
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                builder.append(c).append(c);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public double getMaxRatio() {
        return maxRatio;
    }

    public TextColorSchema getSchema() {
        return schema;
    }
}
