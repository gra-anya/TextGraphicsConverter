package ru.netology.graphics;

import ru.netology.graphics.image.ImageConvertor;
import ru.netology.graphics.image.TextGraphicsConverter;

import ru.netology.graphics.server.GServer;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new ImageConvertor();

        GServer server = new GServer(converter);
        server.start();

//         Или то же, но с выводом в консоль:
//        String url = "https://i.ibb.co/6DYM05G/edu0.jpg";
//        String imgTxt = converter.convert(url);
//        System.out.println(imgTxt);
    }
}
