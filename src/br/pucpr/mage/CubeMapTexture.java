package br.pucpr.mage;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL13.GL_TEXTURE_CUBE_MAP;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class CubeMapTexture {
    private int id;
    private TextureParameters parameters;

    public CubeMapTexture(Image image, TextureParameters parameters) {
        //Valida os parâmetros
        if (image.getChannels() < 3) throw new IllegalArgumentException("Image must be RGB or RGBA!");
        if (parameters == null) throw new IllegalArgumentException("Parameters can't be null!");

        int format = image.getChannels() == 3 ? GL_RGB : GL_RGBA;

        //Copia os dados
        id = glGenTextures();
        glBindTexture(GL_TEXTURE_CUBE_MAP, id);

        // -------------------------------------------------------------
        id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, id);
        glTexImage2D(GL_TEXTURE_2D, 0, format,
                image.getWidth(), image.getHeight(), 0,
                format, GL_UNSIGNED_BYTE, image.getPixels());

        //Ajuste dos parametros
        this.parameters = parameters;
        parameters.apply(GL_TEXTURE_2D);
        if (parameters.isMipMapped()) {
            glGenerateMipmap(GL_TEXTURE_2D);
        }

        //Limpeza
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public CubeMapTexture(Image image) {
        this(image, new TextureParameters(GL_LINEAR, GL_CLAMP_TO_EDGE));
    }

    public CubeMapTexture(String imagePath) {
        this(new Image(imagePath));
    }

    public CubeMapTexture(String imagePath, TextureParameters params) {
        this(new Image(imagePath), params);
    }

    public int getId() {
        return id;
    }

    public TextureParameters getParameters() {
        return parameters;
    }

    public CubeMapTexture bind() {
        glBindTexture(GL_TEXTURE_2D, id);
        return this;
    }

    public CubeMapTexture unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
        return this;
    }
}
