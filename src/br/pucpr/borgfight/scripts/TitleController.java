package br.pucpr.borgfight.scripts;

import br.pucpr.borgfight.prefabs.Instructions;
import br.pucpr.borgfight.prefabs.Title;
import br.pucpr.borgfight.scene.ShipScene;
import br.pucpr.gema.core.*;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class TitleController extends GameComponent {

    private float rotationSpeed = -0.05f;

    private enum State {TITLE, INSTRUCTIONS}

    private GameObject title;
    private GameObject instructions;
    private State state = State.TITLE;

    @Override
    public void awake() {
        // titulo
        title = GameObject.instantiate();
        title.addComponent(Title.class);
        title.setParent(gameObject);

        // instrucoes
        instructions = GameObject.instantiate();
        instructions.addComponent(Instructions.class);
        instructions.setParent(gameObject);
        instructions.setActive(false);

        // camera
        SceneManager.getActiveScene().camera.setParent(gameObject);
    }

    @Override
    public void start() {
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
                instructions.setActive(true);
                state = State.INSTRUCTIONS;
                break;
            case INSTRUCTIONS:
                SceneManager.loadScene(ShipScene.class);
                break;
        }
    }
}
