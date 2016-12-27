package br.pucpr.gema.graphics;

import br.pucpr.mage.Image;
import br.pucpr.mage.TextureParameters;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL13.GL_TEXTURE_CUBE_MAP;
import static org.lwjgl.opengl.GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X;

public class CubeMapTexture {
    private int id;
    private TextureParameters parameters;

    public CubeMapTexture(Map<Side, Image> images, TextureParameters parameters) {
        // valida os parâmetros
        if (parameters == null) throw new IllegalArgumentException("Parameters can't be null!");
        if (images == null) throw new IllegalArgumentException("Images can't be null!");
        if (images.size() != 6) throw new IllegalArgumentException("Cube map requires 6 images!");
        for (Map.Entry<Side, Image> entry : images.entrySet()) {
            if (entry.getValue().getChannels() < 3)
                throw new IllegalArgumentException("Image must be RGB or RGBA!");
        }

        // copia os dados
        id = glGenTextures();
        bind();
        for (int i = 0; i < images.size(); i++) {
            Image image = images.get(Side.valueOf(i));
            glTexImage2D(
                    GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0,
                    GL_RGB, image.getWidth(), image.getHeight(), 0,
                    GL_RGB, GL_UNSIGNED_BYTE, image.getPixels());
        }

        // ajuste dos parâmetros
        this.parameters = parameters;
        parameters.apply(GL_TEXTURE_CUBE_MAP);

        // limpeza
        unbind();
    }

    public CubeMapTexture(Map<Side, Image> images) {
        this(images, new TextureParameters(GL_LINEAR, GL_CLAMP_TO_EDGE));
    }

    public CubeMapTexture(String imagePath) {
        this(createImageSet(imagePath));
    }

    public CubeMapTexture(String imagePath, TextureParameters params) {
        this(createImageSet(imagePath), params);
    }

    // todo: flexibilizar este método quanto a extensão e sufixos
    private static Map<Side, Image> createImageSet(String imagePath) {
        Image image = new Image(imagePath);



        Map<Side, Image> images = new HashMap<>(6);
        images.put(Side.RIGHT, image.cropRelative(0, 1/3f, 1/2f, 2/3f));
        images.put(Side.LEFT, image.cropRelative(0, 1/3f, 1/2f, 2/3f));
        images.put(Side.TOP, image.cropRelative(0, 1/3f, 1/2f, 2/3f));
        images.put(Side.BOTTOM, image.cropRelative(0, 1/3f, 1/2f, 2/3f));
        images.put(Side.BACK, image.cropRelative(0, 1/3f, 1/2f, 2/3f));
        images.put(Side.FRONT, image.cropRelative(0, 1/3f, 1/2f, 2/3f));

        return images;
    }

    public int getId() {
        return id;
    }

    public TextureParameters getParameters() {
        return parameters;
    }

    public CubeMapTexture bind() {
        glBindTexture(GL_TEXTURE_CUBE_MAP, id);
        return this;
    }

    public CubeMapTexture unbind() {
        glBindTexture(GL_TEXTURE_CUBE_MAP, 0);
        return this;
    }
}
