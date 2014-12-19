/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author cyberuser
 */
public class ResimIsleme {

    BufferedImage resim;
    int secim;

    public ResimIsleme(BufferedImage resim, int secim) {
        this.resim = resim;
        this.secim = secim;
    }

    public BufferedImage getResim() {
        int width = resim.getWidth();
        int height = resim.getHeight();
        BufferedImage donenResim = new BufferedImage(width, height, resim.getType());

        switch (secim) {
            case 1://siyah beyaz
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        Color c = new Color(resim.getRGB(j, i));
                        int red = (int) (c.getRed() * 0.299);
                        int green = (int) (c.getGreen() * 0.587);
                        int blue = (int) (c.getBlue() * 0.114);
                        Color newColor = new Color(red + green + blue, red + green + blue, red + green + blue);
                        donenResim.setRGB(j, i, newColor.getRGB());
                    }
                }
                break;
            case 2://kırmızı
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        Color c = new Color(resim.getRGB(j, i));
                        int red = (int) (c.getRed() * 0.299);
                        int green = (int) (c.getGreen() * 0.587);
                        int blue = (int) (c.getBlue() * 0.114);
                        Color newColor = new Color(red + red + red, 0, 0);
                        donenResim.setRGB(j, i, newColor.getRGB());
                    }
                }
                break;
            case 3://yeşil
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        Color c = new Color(resim.getRGB(j, i));
                        int red = (int) (c.getRed() * 0.299);
                        int green = (int) (c.getGreen() * 0.587);
                        int blue = (int) (c.getBlue() * 0.114);
                        Color newColor = new Color(0, green ,0);
                        donenResim.setRGB(j, i, newColor.getRGB());
                    }
                }
                break;
            case 4://mavi
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        Color c = new Color(resim.getRGB(j, i));
                        int red = (int) (c.getRed() * 0.299);
                        int green = (int) (c.getGreen() * 0.587);
                        int blue = (int) (c.getBlue() * 0.114);
                        Color newColor = new Color(0,0,blue);
                        donenResim.setRGB(j, i, newColor.getRGB());
                    }
                }
                break;
            case 5://kırmızı+yeşil (sarı)
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        Color c = new Color(resim.getRGB(j, i));
                        int red = (int) (c.getRed() * 0.299);
                        int green = (int) (c.getGreen() * 0.587);
                        int blue = (int) (c.getBlue() * 0.114);
                        Color newColor = new Color(red + red + red, green, 0);
                        donenResim.setRGB(j, i, newColor.getRGB());
                    }
                }
                break;
            case 6://yeşil+mavi (cyan)
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        Color c = new Color(resim.getRGB(j, i));
                        int red = (int) (c.getRed() * 0.299);
                        int green = (int) (c.getGreen() * 0.587);
                        int blue = (int) (c.getBlue() * 0.114);
                        Color newColor = new Color(0,green , blue + blue);
                        donenResim.setRGB(j, i, newColor.getRGB());
                    }
                }
                break;
            case 7://kırmızı+mavi
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        Color c = new Color(resim.getRGB(j, i));
                        int red = (int) (c.getRed() * 0.299);
                        int green = (int) (c.getGreen() * 0.587);
                        int blue = (int) (c.getBlue() * 0.114);
                        Color newColor = new Color(red + red + red, 0, blue + blue + blue);
                        donenResim.setRGB(j, i, newColor.getRGB());
                    }
                }
                break;
            case 8://negatif
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        Color c = new Color(resim.getRGB(j, i));
                        int red = (int) (255-c.getRed());
                        int green = (int) (255-c.getGreen());
                        int blue = (int) (255-c.getBlue());
                        Color newColor = new Color(red ,  green,blue);
                        donenResim.setRGB(j, i, newColor.getRGB());
                    }
                }
                break;

        }
        return donenResim;
    }
}
