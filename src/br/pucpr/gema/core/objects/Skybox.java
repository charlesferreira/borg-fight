package br.pucpr.gema.core.objects;

import br.pucpr.cg.MeshFactory;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import br.pucpr.mage.Texture;

public class Skybox extends GameObject {
    public Skybox(String imagePath) {
        super(true);
        renderer.mesh = MeshFactory.createCubeInverse();
        ((DefaultMaterial)renderer.material).setTexture(
                new Texture(imagePath));
    }
}
