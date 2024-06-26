package mymod;

import game.ResourceLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModResourceLoader implements ResourceLoader {

        @Override
    public BufferedImage loadImage(String path) throws IOException {
        // Implement custom logic for loading mod resources
        // For example, loading from a different directory or URL
        return ImageIO.read(Objects.requireNonNull(this.getClass().getResource(path)));
    }

    @Override
    public List<String> loadTextFile(String path) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream(path))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
