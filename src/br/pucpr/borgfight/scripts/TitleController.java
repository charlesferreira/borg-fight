package br.pucpr.borgfight.scripts;

import br.pucpr.borgfight.scene.ShipScene;
import br.pucpr.gema.core.*;
import org.lwjgl.glfw.GLFW;

public class TitleController extends GameComponent {

    private float rotationSpeed = -0.05f;

    private enum State {TITLE, INSTRUCTIONS}

    private GameObject title;
    private GameObject clickToStart;
    private GameObject instructions;
    private State state = State.TITLE;

    @Override
    public void start() {
        SceneManager.getActiveScene().camera.setParent(gameObject);
        instructions.setActive(false);
        transform.rotateLocal((float) (-Math.PI / 6), transform.left());
    }

    @Override
    public void update() {
        if (Input.getMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT))
            nextState();

        // gira, gira...
        transform.rotateLocal(rotationSpeed * Time.deltaTime, transform.up());
    }

    private void nextState() {
        switch (state) {
            case TITLE:
                title.setActive(false);
                clickToStart.transform.setLocalPosition(0, -6.15f, 0);
                instructions.setActive(true);
                state = State.INSTRUCTIONS;
                break;
            case INSTRUCTIONS:
                SceneManager.loadScene(ShipScene.class);
                break;
        }
    }

    public TitleController setTitle(GameObject title) {
        this.title = title;
        return this;
    }

    public TitleController setClickToStart(GameObject clickToStart) {
        this.clickToStart = clickToStart;
        return this;
    }

    public TitleController setInstructions(GameObject instructions) {
        this.instructions = instructions;
        return this;
    }
}
