package br.pucpr.borgfight.prefabs;

import br.pucpr.cg.MeshFactory;
import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.graphics.materials.SpriteMaterial;
import br.pucpr.mage.Texture;

public class Instructions extends GameComponent {

    @Override
    public void start() {
        transform.scale(5.2f, 6.6f, 1);

        gameObject.renderer.mesh = MeshFactory.createCanvas();
        gameObject.renderer.material = new SpriteMaterial()
                .setTexture(new Texture("br/pucpr/borgfight/assets/textures/instructions.png"));
    }
}
