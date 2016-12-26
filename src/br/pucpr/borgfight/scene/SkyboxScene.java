package br.pucpr.borgfight.scene;

import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.core.objects.Cube;
import br.pucpr.gema.core.objects.Skybox;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import br.pucpr.gema.util.scripts.DemoController;
import br.pucpr.mage.Texture;
import org.joml.Vector3f;

public class SkyboxScene extends GameScene {

    @Override
    protected Vector3f getStartingCameraPos() {
        return new Vector3f(0, 0, 5);
    }

    @Override
    protected void onSceneLoad() {
        GameObject cube = GameObject.instantiate(Skybox.class);
        cube.addComponent(DemoController.class);

        DefaultMaterial material = new DefaultMaterial()
                .setTexture(new Texture("C:/temp/img/opengl/textures/bricks_t.jpg"));
        cube.renderer.material = material;

    }
}
