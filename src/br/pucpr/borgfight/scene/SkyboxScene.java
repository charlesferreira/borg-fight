package br.pucpr.borgfight.scene;

import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.core.objects.Skybox;
import br.pucpr.gema.graphics.materials.CubeMapMaterial;
import br.pucpr.gema.util.scripts.DemoController;
import br.pucpr.mage.Texture;
import org.joml.Vector3f;

public class SkyboxScene extends GameScene {

    @Override
    protected Vector3f getStartingCameraPos() {
        return new Vector3f();
    }

    @Override
    protected void onSceneLoad() {
        // permite controlar a câmera
        camera.addComponent(DemoController.class);

        // skybox
        GameObject cube = GameObject.instantiate(Skybox.class);
        cube.renderer.material = new CubeMapMaterial()
                .setTexture(new Texture("C:/temp/img/opengl/textures/bricks_t.jpg"));

    }
}
