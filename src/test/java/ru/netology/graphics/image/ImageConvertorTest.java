package ru.netology.graphics.image;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class ImageConvertorTest {

    @Test
    void checkAspectRatioShouldThrowBadImageSizeException(){
        ImageConvertor imageConvertor = new ImageConvertor();
        imageConvertor.setMaxRatio(1.5);
        BufferedImage image = Mockito.mock(BufferedImage.class);
        Mockito.when(image.getHeight()).thenReturn(10);
        Mockito.when(image.getHeight()).thenReturn(1000);

        Class<BadImageSizeException> expected = BadImageSizeException.class;

        assertThrows(expected,() -> imageConvertor.checkAspectRatio(image) );
    }

    @Test
    void newHeightShouldNotBiggerMax() {
        ImageConvertor imageConvertor = new ImageConvertor();
        imageConvertor.setMaxHeight(300);
        BufferedImage image = Mockito.mock(BufferedImage.class);
        Mockito.when(image.getHeight()).thenReturn(1000);
        Mockito.when(image.getWidth()).thenReturn(100);
        int expected = 300;
        int actual = imageConvertor.checkSizeColor(image).getHeight();
        assertTrue(actual <= expected);
    }
    @Test
    void newWidthShouldNotBiggerMax() {
        ImageConvertor imageConvertor = new ImageConvertor();
        imageConvertor.setMaxWidth(300);
        BufferedImage image = Mockito.mock(BufferedImage.class);
        Mockito.when(image.getHeight()).thenReturn(1000);
        Mockito.when(image.getWidth()).thenReturn(1000);
        int expected = 300;
        int actual = imageConvertor.checkSizeColor(image).getWidth();
        assertTrue(actual <= expected);
    }
    @Test
    void imageShouldBeBW() {
        ImageConvertor imageConvertor = new ImageConvertor();
        BufferedImage image = Mockito.mock(BufferedImage.class);
        Mockito.when(image.getHeight()).thenReturn(1000);
        Mockito.when(image.getWidth()).thenReturn(1000);
        Mockito.when(image.getType()).thenReturn(BufferedImage.TYPE_CUSTOM);

        int expected = BufferedImage.TYPE_BYTE_GRAY;
        int actual = imageConvertor.checkSizeColor(image).getType();
        assertTrue(actual <= expected);
    }


    @Test
    void setMaxWidth() {
        ImageConvertor imageConvertor = new ImageConvertor();
        int expected = 300;
        imageConvertor.setMaxWidth(expected);
        int actual = imageConvertor.getMaxWidth();
        Assertions.assertEquals(expected, actual );
    }

    @Test
    void setMaxHeight() {
        ImageConvertor imageConvertor = new ImageConvertor();
        int expected = 300;
        imageConvertor.setMaxHeight(expected);
        int actual = imageConvertor.getMaxHeight();
        Assertions.assertEquals(expected, actual );
    }

    @Test
    void setMaxRatio() {
        ImageConvertor imageConvertor = new ImageConvertor();
        double expected = 1.33;
        imageConvertor.setMaxRatio(expected);
        double actual = imageConvertor.getMaxRatio();
        Assertions.assertEquals(expected, actual );
    }
}