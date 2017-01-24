package br.pucpr.borgfight.scene;

import br.pucpr.borgfight.prefabs.MySkybox;
import br.pucpr.borgfight.scripts.TitleController;
import br.pucpr.borgfight.scripts.SpriteAlphaBlink;
import br.pucpr.cg.MeshFactory;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.graphics.materials.SpriteMaterial;
import br.pucpr.mage.Texture;
import org.joml.Vector3f;

public class TitleScene extends GameScene {

    private GameObject gui;
    private GameObject title;
    private GameObject clickToStart;
    private GameObject instructions;

    @Override
    protected Vector3f getStartingCameraPos() {
        return new Vector3f(0, 0, 0);
    }

    @Override
    protected void onSceneLoad() {
        GameObject.instantiate(MySkybox.class);

        createGUI();
        createTitle();
        createClickToStart();
        createInstructions();

        GameObject controller = GameObject.instantiate();
        controller.addComponent(TitleController.class)
                .setTitle(title)
                .setClickToStart(clickToStart)
                .setInstructions(instructions);
    }

    private void createGUI() {
        gui = GameObject.instantiate();
        gui.transform.translate(0, 0, -13f);
        gui.setParent(camera);
    }

    private void createTitle() {
        title = GameObject.instantiate();
        title.setParent(gui)
                .transform.scale(9.4f, .6f, 1)
                .translate(0, 3.5f, 0);

        title.renderer
                .setMesh(MeshFactory.createCanvas())
                .setMaterial(new SpriteMaterial()
                        .setTexture(new Texture("br/pucpr/borgfight/assets/textures/title.png")));
    }

    private void createClickToStart() {
        clickToStart = GameObject.instantiate();
        clickToStart.addComponent(SpriteAlphaBlink.class)
                .setup(.5f, 1f, .5f);

        clickToStart.setParent(gui)
                .transform.scale(3.5f, .35f, 1)
                .translate(0, -3.5f, 0);

        clickToStart.renderer
                .setMesh(MeshFactory.createCanvas())
                .setMaterial(new SpriteMaterial()
                        .setTexture(new Texture("br/pucpr/borgfight/assets/textures/click-to-start.png")));
    }

    private void createInstructions() {
        instructions = GameObject.instantiate();
        instructions.setParent(gui)
                .transform.scale(5.2f, 5.7f, 1)
                .translate(0, 1f, 0);

        instructions.renderer
                .setMesh(MeshFactory.createCanvas())
                .setMaterial(new SpriteMaterial()
                        .setTexture(new Texture("br/pucpr/borgfight/assets/textures/instructions.png")));
    }
}
