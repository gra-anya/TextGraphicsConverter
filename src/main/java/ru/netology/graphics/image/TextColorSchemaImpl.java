package ru.netology.graphics.image;

public class TextColorSchemaImpl implements TextColorSchema {
    private final char[] charArray = {'#', '$', '@', '%', '*', '+', '-', '\''};

    @Override
    public char convert(int color) {
        int charNumber = (int) (charArray.length * ((double) color / 255));
        return charArray[charNumber];
    }
}
