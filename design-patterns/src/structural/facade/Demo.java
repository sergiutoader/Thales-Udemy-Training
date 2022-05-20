package structural.facade;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class Buffer {
    private char [] characters;
    private int lineWidth;

    public Buffer(int lineHeight, int lineWidth) {
        this.lineWidth = lineWidth;
        this.characters = new char[lineWidth * lineHeight];
    }

    public char charAt(int x, int y) {
        return characters[y * lineWidth + x];
    }
}

class ViewPort {

    private final Buffer buffer;
    private final int width;
    private final int height;
    private final int offsetX;
    private final int offsetY;


    public ViewPort(
            Buffer buffer,
            int width,
            int height,
            int offsetX,
            int offsetY
    ) {
        this.buffer = buffer;
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public char charAt(int x, int y) {
        return buffer.charAt(x + offsetX, y + offsetY);
    }
}

class Console {
    private List<ViewPort> viewPorts = new ArrayList<>();
    int width, height;

    public Console(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addViewPort(ViewPort viewPort) {
        viewPorts.add(viewPort);
    }

    public void render() {
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for(ViewPort vp : viewPorts) {
                    System.out.print(vp.charAt(x, y));
                }
                System.out.println();
            }
        }
    }

    public static Console newConsole(int width, int height) {
        final Buffer buffer = new Buffer(height, width);
        final ViewPort viewPort = new ViewPort(buffer, width, height, 0, 0);
        final Console console = new Console(width, height);
        console.addViewPort(viewPort);

        return console;
    }
}

public class Demo {
    public static void main(String[] args) {
        final Buffer buffer = new Buffer(20, 30);
        final ViewPort viewPort = new ViewPort(buffer, 30, 20, 0, 0);
        final Console console = new Console(30, 20);
        console.addViewPort(viewPort);
        console.render();

        // Facade
        final Console newConsole = Console.newConsole(30, 20);
        newConsole.render();

    }
}
