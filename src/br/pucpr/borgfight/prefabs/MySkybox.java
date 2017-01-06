package br.pucpr.borgfight.prefabs;

import br.pucpr.gema.core.objects.Skybox;
import br.pucpr.gema.graphics.CubeMapTexture;
import br.pucpr.gema.graphics.Side;
import br.pucpr.gema.graphics.materials.CubeMapMaterial;
import br.pucpr.mage.Image;

import java.util.HashMap;
import java.util.Map;

public class MySkybox extends Skybox {
    @Override
    protected void init() {
        super.init();

        Map<Side, Image> images = new HashMap<>(6);
        String path = "br/pucpr/borgfight/assets/textures/skybox/";
        images.put(Side.RIGHT, new Image(path + "rt.jpg"));
        images.put(Side.LEFT, new Image(path + "lf.jpg"));
        images.put(Side.TOP, new Image(path + "up.jpg"));
        images.put(Side.BOTTOM, new Image(path + "dn.jpg"));
        images.put(Side.BACK, new Image(path + "bk.jpg"));
        images.put(Side.FRONT, new Image(path + "ft.jpg"));
        renderer.material = new CubeMapMaterial().setTexture(
                new CubeMapTexture(images));
    }
}
