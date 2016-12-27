package br.pucpr.borgfight.scene;

import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.core.objects.Skybox;
import br.pucpr.gema.graphics.materials.CubeMapMaterial;
import br.pucpr.gema.util.scripts.DemoController;
import br.pucpr.gema.graphics.CubeMapTexture;
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
        GameObject skybox = GameObject.instantiate(Skybox.class);
        skybox.renderer.material = new CubeMapMaterial().setTexture(
//                new CubeMapTexture("/Users/rafaelforbeck/Documents/img/opengl/skyboxes/ame_ash/ashcanyon"));
                new CubeMapTexture("/Users/rafaelforbeck/Documents/img/skybox.png"));

    }
}
