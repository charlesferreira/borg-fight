package br.pucpr.gema.graphics;

import br.pucpr.cg.Camera;
import br.pucpr.mage.Light;

public class RenderContext {
    public final Camera camera;
    public final Light light;

    public RenderContext(Camera camera, Light light) {
        this.camera = camera;
        this.light = light;
    }
}
