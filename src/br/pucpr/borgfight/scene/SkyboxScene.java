package br.pucpr.borgfight.scene;

import br.pucpr.borgfight.prefabs.MySkybox;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.core.objects.Skybox;
import br.pucpr.gema.graphics.Side;
import br.pucpr.gema.graphics.materials.CubeMapMaterial;
import br.pucpr.gema.util.scripts.DemoController;
import br.pucpr.gema.graphics.CubeMapTexture;
import br.pucpr.mage.Image;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

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
        GameObject skybox = GameObject.instantiate(MySkybox.class);
    }
}
