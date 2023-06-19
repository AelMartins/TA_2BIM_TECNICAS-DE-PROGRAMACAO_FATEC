package br.com.dsm.projeto;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.springframework.stereotype.Component;

@Component
public class StickersGerator {
    public void generateStickers(String url, String message, String fileName) {
        try {
            InputStream inputStream = new URL(url).openStream();
            BufferedImage originalImage = ImageIO.read(inputStream);
            
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            int newHeight = height + 200;
            BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D graphics = newImage.createGraphics();
            graphics.drawImage(originalImage, 0, 0, null);
            
            Font font = new Font(Font.SANS_SERIF, Font.BOLD, width / 10);
            graphics.setFont(font);
            graphics.setColor(Color.YELLOW);

            FontMetrics fontMetrics = graphics.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(message);
            int marginLeft = (width - textWidth) / 2;
            int marginTop = height + (200 - (width / 10)) / 2;
            
            graphics.drawString(message, marginLeft, marginTop );
            
            ImageIO.write(newImage, "png", new File("exit/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
