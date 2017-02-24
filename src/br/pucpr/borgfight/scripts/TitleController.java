package br.pucpr.borgfight.scripts;

import br.pucpr.borgfight.scene.MainScene;
import br.pucpr.gema.core.*;
import org.lwjgl.glfw.GLFW;

public class TitleController extends GameComponent {

    private enum State {TITLE, INSTRUCTIONS}

    private float rotationSpeed = -0.05f;

    private GameObject title;
    private GameObject clickToStart;
    private GameObject instructions;
    private GameObject credits;

    private State currentState = State.TITLE;

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
    }

    @Override
    public void fixedUpdate() {
        // câmera gira, gira...
        transform.rotateLocal(rotationSpeed * Time.fixedDeltaTime, transform.up());
    }

    private void nextState() {
        switch (currentState) {
            case TITLE:
                title.setActive(false);
                credits.setActive(false);
                clickToStart.transform.setLocalPosition(0, -6.15f, 0);
                instructions.setActive(true);
                currentState = State.INSTRUCTIONS;
                break;
            case INSTRUCTIONS:
                SceneManager.loadScene(MainScene.class);
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

    public void setCredits(GameObject credits) {
        this.credits = credits;
    }
}
