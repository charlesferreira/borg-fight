package br.pucpr.gema.graphics.materials;

import br.pucpr.mage.Texture;
import br.pucpr.mage.phong.SimpleMaterial;

public class SpriteMaterial extends SimpleMaterial {

    public SpriteMaterial() {
        super("/br/pucpr/gema/resource/shaders/sprite");
    }

    public SpriteMaterial setTexture(Texture texture) {
        return (SpriteMaterial) setTexture("uTexture", texture);
    }

    @Override
    public void apply() {
        super.apply();
    }
}
