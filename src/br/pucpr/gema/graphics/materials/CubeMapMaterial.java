package br.pucpr.gema.graphics.materials;

import br.pucpr.gema.graphics.CubeMapTexture;
import br.pucpr.mage.Material;
import br.pucpr.mage.Shader;

public class CubeMapMaterial implements Material {
    private CubeMapTexture texture;
    private Shader shader;

    public CubeMapMaterial() {
        this.shader = Shader.loadProgram("/br/pucpr/gema/resource/shaders/cubemap");
    }

    public CubeMapMaterial setTexture(CubeMapTexture texture) {
        this.texture = texture;
        return this;
    }

    @Override
    public void apply() {
        texture.bind();
    }

    @Override
    public void setShader(Shader shader) {
        if (shader == null) throw new IllegalArgumentException("Shader cannot be null!");
        this.shader = shader;
    }

    @Override
    public Shader getShader() {
        return shader;
    }
}
