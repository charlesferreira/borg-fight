package br.pucpr.gema.graphics.materials;

import br.pucpr.mage.Texture;
import br.pucpr.mage.phong.SimpleMaterial;
import org.joml.Vector3f;

public class DefaultMaterial extends SimpleMaterial {
    private Vector3f ambientColor;
    private Vector3f diffuseColor;
    private Vector3f specularColor;
    private float specularPower;
    private boolean isSolidColor = true;

    public DefaultMaterial(Vector3f ambientColor, Vector3f diffuseColor, Vector3f specularColor, float specularPower) {
        super("/br/pucpr/gema/resource/shaders/standard");
        this.ambientColor = ambientColor;
        this.diffuseColor = diffuseColor;
        this.specularColor = specularColor;
        this.specularPower = specularPower;
    }

    public DefaultMaterial(Vector3f ambient, Vector3f diffuse) {
        this(ambient, diffuse, new Vector3f(), 0.0f);
    }

    public DefaultMaterial(Vector3f color) {
        this(color, color);
    }

    public DefaultMaterial() {
        this(new Vector3f(1.0f, 1.0f, 1.0f));
    }

    public DefaultMaterial setTexture(Texture texture) {
        isSolidColor = texture == null;
        return (DefaultMaterial) setTexture("uTexture", texture);
    }

    @Override
    public void apply() {
        getShader().setUniform("uAmbientMaterial", ambientColor);
        getShader().setUniform("uDiffuseMaterial", diffuseColor);
        getShader().setUniform("uSpecularMaterial", specularColor);
        getShader().setUniform("uSpecularPower", specularPower);
        getShader().setUniform("isSolidColor", isSolidColor);
        super.apply();
    }

    public DefaultMaterial setColor(Vector3f color) {
        setAmbientColor(color);
        setDiffuseColor(color);
        setSpecularColor(color);
        return this;
    }

    public DefaultMaterial setAmbientColor(Vector3f ambientColor) {
        this.ambientColor = ambientColor;
        return this;
    }

    public DefaultMaterial setDiffuseColor(Vector3f diffuseColor) {
        this.diffuseColor = diffuseColor;
        return this;
    }

    public DefaultMaterial setSpecularColor(Vector3f specularColor) {
        this.specularColor = specularColor;
        return this;
    }

    public DefaultMaterial setSpecularPower(float specularPower) {
        this.specularPower = specularPower;
        return this;
    }
}
