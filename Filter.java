import java.awt.Color;
import java.awt.image.BufferedImage;

public class Filter {

    public static BufferedImage escalaDeCinza(BufferedImage imagem) {

        int largura = imagem.getWidth();
        int altura = imagem.getHeight();
 
        int media = 0;

        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                
                int rgb = imagem.getRGB(i, j); 

                int r = (int)((rgb&0x00FF0000)>>>16);
                int g = (int)((rgb&0x0000FF00)>>>8);
                int b = (int)(rgb&0x000000FF);

                media = (r + g + b) / 3;
 
                Color cor = new Color(media, media, media);
                imagem.setRGB(i, j, cor.getRGB());

            }
        }

        return imagem;
    }
 
    public static BufferedImage negativo(BufferedImage imagem) {

        int largura = imagem.getWidth();
        int altura = imagem.getHeight();

        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {

                int rgb = imagem.getRGB(i, j);

                int r = 255 - (int)((rgb&0x00FF0000)>>>16);
                int g = 255 - (int)((rgb&0x0000FF00)>>>8);
                int b = 255 - (int)(rgb&0x000000FF);

                Color color = new Color(r, g, b);

                imagem.setRGB(i, j, color.getRGB());

            }
        }
        
        return imagem;
    }
 
    public static BufferedImage suavizacao(BufferedImage imagem) {

        int largura = imagem.getWidth();
        int altura = imagem.getHeight();

        for (int i = 1; i < largura - 1; i++) {
            for (int j = 1; j < altura - 1; j++) {

                int cor = calculoMediaDaVizinhaca(imagem, i, j);
                imagem.setRGB(i, j, cor);

            }
        }

        return imagem;

    }

    //matriz 3x3
    private static int calculoMediaDaVizinhaca(BufferedImage imagem, int x, int y) {

        int somaR = 0, somaG = 0, somaB = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {

                Color cor = new Color(imagem.getRGB(i, j));

                somaR += cor.getRed();
                somaG += cor.getGreen();
                somaB += cor.getBlue();

            }
        }

        int mediaR = somaR / 9;
        int mediaG = somaG / 9;
        int mediaB = somaB / 9;

        return new Color(mediaR, mediaG, mediaB).getRGB();

    }

}
