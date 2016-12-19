package br.pucpr.mage.phong;

import br.pucpr.mage.FrameBuffer;

public class WaterMaterial extends SimpleMaterial {
    public WaterMaterial() {
        super("/br/pucpr/mage/resource/phong/water");
    }

    @Override
    public void apply() {
        super.apply();
    }

    public void setRefraction(FrameBuffer refractionFB) {
        setTexture("uRefraction", refractionFB.getTexture());
    }

    public void setReflection(FrameBuffer reflectionFB) {
        setTexture("uReflection", reflectionFB.getTexture());
    }
}
