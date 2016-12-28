package br.pucpr.gema.core.objects;

import br.pucpr.cg.MeshFactory;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import org.joml.Vector3f;

public class Skybox extends GameObject {

    @Override
    protected void init() {
        renderer.mesh = MeshFactory.createCubeInverse();
    }
}
