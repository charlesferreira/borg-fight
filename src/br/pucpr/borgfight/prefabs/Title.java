package br.pucpr.borgfight.prefabs;

import br.pucpr.cg.MeshFactory;
import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.graphics.materials.SpriteMaterial;
import br.pucpr.mage.Texture;

public class Title extends GameComponent {
    @Override
    public void start() {
        transform.scale(9.4f, 4.3f, 1);

        gameObject.renderer.mesh = MeshFactory.createCanvas();
        gameObject.renderer.material = new SpriteMaterial()
                .setTexture(new Texture("br/pucpr/borgfight/assets/textures/title.png"));
    }
}
