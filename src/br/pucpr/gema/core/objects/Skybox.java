package br.pucpr.gema.core.objects;

import br.pucpr.cg.MeshFactory;
import br.pucpr.gema.core.GameObject;

public class Skybox extends GameObject {

    @Override
    protected void init() {
        renderer.mesh = MeshFactory.createCubeInverse();
    }
}
