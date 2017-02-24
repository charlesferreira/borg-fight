package br.pucpr.borgfight.scripts;

import br.pucpr.cg.MeshFactory;
import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.components.Transform;
import br.pucpr.gema.graphics.materials.SpriteMaterial;
import br.pucpr.mage.Texture;

public class HUD extends GameComponent {
    GameObject timeBar;
    GameObject enemiesBar;

    float barsWidth = 3.333f;

    GameObject victory;
    GameObject defeat;
    GameObject quit;


    @Override
    public void awake() {
        GameManager.getInstance().setHud(this);

        transform.translate(0, 0, -13);

        createTimeLabel();
        timeBar = createTimeBar();

        createEnemiesLabel();
        enemiesBar = createEnemiesBar();

        victory = createVictoryMessage();
        defeat = createDefeatMessage();
        quit = createQuitMessage();
    }

    @Override
    public void update() {
        GameManager game = GameManager.getInstance();
        float timeBarScale = Math.max(0, barsWidth * game.relativeTimeLeft());
        timeBar.transform.setLocalScale(timeBarScale, 1, 1);

        float enemiesBarScale = Math.max(0, barsWidth * game.relativeEnemiesLeft());
        enemiesBar.transform.setLocalScale(enemiesBarScale, 1, 1);
    }

    private Transform createSprite(String textureName) {
        GameObject sprite = GameObject.instantiate().setParent(gameObject);
        sprite.renderer.setMesh(MeshFactory.createCanvas())
                .setMaterial(new SpriteMaterial()
                        .setTexture(new Texture("br/pucpr/borgfight/assets/textures/" + textureName + ".png")));

        return sprite.transform;
    }

    private void createTimeLabel() {
        createSprite("time-text")
                .scale(1, .2f, 1)
                .translate(-11.75f, 7, 0);
    }

    private GameObject createTimeBar() {
        float xPosition = -13.5f;
        GameObject bar = GameObject.instantiate().setParent(gameObject);
        bar.transform.translate(xPosition + barsWidth, 7, 0)
                .scale(barsWidth, 1, 1);
        createSprite("time-bar")
                .translate(barsWidth, 0, 0)
                .scale(barsWidth, 0.15f, 1)
                .gameObject.setParent(bar);

        return bar;
    }

    private void createEnemiesLabel() {
        createSprite("enemies-text")
                .scale(1, .2f, 1)
                .translate(-11.75f, 6.375f, 0);
    }

    private GameObject createEnemiesBar() {
        float xPosition = -13.5f;
        GameObject bar = GameObject.instantiate().setParent(gameObject);
        bar.transform.translate(xPosition + barsWidth, 6.375f, 0)
                .scale(barsWidth, 1, 1);
        createSprite("enemies-bar")
                .translate(barsWidth, 0, 0)
                .scale(barsWidth, 0.15f, 1)
                .gameObject.setParent(bar);

        return bar;
    }

    private GameObject createVictoryMessage() {
        return createSprite("victory")
                .scale(6.8f, .8f, 1)
                .translate(0, 1, 0)
                .gameObject.setActive(false);
    }

    private GameObject createDefeatMessage() {
        return createSprite("defeat")
                .scale(6.8f, .8f, 1)
                .translate(0, 1, 0)
                .gameObject
                .setActive(false);
    }

    private GameObject createQuitMessage() {
        return createSprite("quit")
                .scale(3.6f, .3f, 1)
                .translate(0, -1, 0)
                .gameObject.addComponent(SpriteAlphaBlink.class)
                .setup(.5f, 1f, .75f)
                .gameObject.setActive(false);
    }
}
