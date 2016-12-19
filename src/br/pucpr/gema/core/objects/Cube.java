package br.pucpr.gema.core.objects;

import br.pucpr.cg.MeshFactory;
import br.pucpr.gema.core.GameObject;

public class Cube extends GameObject {
    public Cube() {
        super(true);
        renderer.mesh = MeshFactory.createCube();
    }
}
