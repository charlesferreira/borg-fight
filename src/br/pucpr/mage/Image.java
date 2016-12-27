package br.pucpr.mage;

import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.lwjgl.stb.STBImage.stbi_load_from_memory;

public class Image {
    private ByteBuffer pixels;

    private int width;
    private int height;
    private int channels;

    private ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }

    private ByteBuffer loadResourceToBuffer(String resource) throws IOException {
        ByteBuffer buffer;

        Path path = Paths.get(resource);
        if (Files.isReadable(path)) {
            try (SeekableByteChannel fc = Files.newByteChannel(path)) {
                buffer = BufferUtils.createByteBuffer((int) fc.size() + 1);
                while (fc.read(buffer) != -1) {
                    //Just keep reading
                }
            }
        } else {
            try (InputStream source = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
                 ReadableByteChannel rbc = Channels.newChannel(source)) {
                buffer = BufferUtils.createByteBuffer(8 * 1024);

                while (true) {
                    int bytes = rbc.read(buffer);
                    if (bytes == -1) {
                        break;
                    }
                    if (buffer.remaining() == 0) {
                        buffer = resizeBuffer(buffer, buffer.capacity() * 2);
                    }
                }
            }
        }

        buffer.flip();
        return buffer;
    }

    public Image(String path) {
        try {
            ByteBuffer buffer = loadResourceToBuffer(path);
            IntBuffer w = BufferUtils.createIntBuffer(1);
            IntBuffer h = BufferUtils.createIntBuffer(1);
            IntBuffer c = BufferUtils.createIntBuffer(1);

            pixels = stbi_load_from_memory(buffer, w, h, c, 0);
            if (pixels == null) {
                throw new RuntimeException("Failed to load image: " + path);
            }

            width = w.get();
            height = h.get();
            channels = c.get();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image: " + path, e);
        }
    }

    public Image(int width, int height, int channels, ByteBuffer pixels) {
        this.width = width;
        this.height = height;
        this.channels = channels;
        this.pixels = pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getChannels() {
        return channels;
    }

    public ByteBuffer getPixels() {
        return pixels.asReadOnlyBuffer();
    }

    public Image cropRelative(float left, float top, float right, float bottom) {
        int x0 = (int) (getWidth() * left);
        int x1 = (int) (getWidth() * right);
        int y0 = (int) (getHeight() * top);
        int y1 = (int) (getHeight() * bottom);
        return crop(x0, y0, x1, y1);
    }

    private Image crop(int x0, int y0, int x1, int y1) {
        int w = x1 - x0;
        int h = y1 - y0;
        int capacity = w * h * channels;
        ByteBuffer croppedPixels = BufferUtils.createByteBuffer(capacity);
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                for (int c = 0; c < channels; c++) {
                    int j = (y0 + y) * width;
                    int i = j + x0 + x;
                    int index = j + i + c;
                    croppedPixels.put(pixels.get(index));
                }
            }
        }

        return new Image(w, h, channels, croppedPixels);
    }
}
