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
    private GameObject credits;

    @Override
    protected Vector3f getStartingCameraPos() {
        return new Vector3f(0, 0, 0);
    }

    @Override
    protected void onSceneLoad() {
        GameObject.instantiate(MySkybox.class);

        createGUI();

        GameObject controller = GameObject.instantiate();
        controller.addComponent(TitleController.class)
                .setTitle(createTitle())
                .setClickToStart(createClickToStart())
                .setInstructions(createInstructions())
                .setCredits(createCredits());
    }

    private void createGUI() {
        gui = GameObject.instantiate();
        gui.transform.translate(0, 0, -13);
        gui.setParent(camera);
    }

    private GameObject createTitle() {
        GameObject title = GameObject.instantiate();
        title.setParent(gui)
                .transform.scale(9.4f, .6f, 1)
                .translate(0, 4, 0);

        title.renderer
                .setMesh(MeshFactory.createCanvas())
                .setMaterial(new SpriteMaterial()
                        .setTexture(new Texture("br/pucpr/borgfight/assets/textures/title.png")));

        return title;
    }

    private GameObject createClickToStart() {
        GameObject clickToStart = GameObject.instantiate();
        clickToStart.addComponent(SpriteAlphaBlink.class)
                .setup(.5f, 1f, .5f);

        clickToStart.setParent(gui)
                .transform.scale(3.5f, .35f, 1)
                .translate(0, -1f, 0)
        ;

        clickToStart.renderer
                .setMesh(MeshFactory.createCanvas())
                .setMaterial(new SpriteMaterial()
                        .setTexture(new Texture("br/pucpr/borgfight/assets/textures/click-to-start.png")));

        return clickToStart;
    }

    private GameObject createInstructions() {
        GameObject instructions = GameObject.instantiate();
        instructions.setParent(gui)
                .transform.scale(5.2f, 5.5f, 1)
                .translate(0, 1f, 0);

        instructions.renderer
                .setMesh(MeshFactory.createCanvas())
                .setMaterial(new SpriteMaterial()
                        .setTexture(new Texture("br/pucpr/borgfight/assets/textures/instructions.png")));

        return instructions;
    }

    private GameObject createCredits() {
        GameObject credits = GameObject.instantiate();
        credits.setParent(gui)
                .transform.scale(2.4f, 0.6f, 1)
                .translate(0, -6f, 0);

        credits.renderer
                .setMesh(MeshFactory.createCanvas())
                .setMaterial(new SpriteMaterial()
                        .setTexture(new Texture("br/pucpr/borgfight/assets/textures/credits.png")));

        return credits;
    }
}
