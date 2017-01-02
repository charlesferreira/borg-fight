package br.pucpr.gema.core;

import br.pucpr.gema.core.components.Transform;

abstract public class GameComponent {
    public Transform transform;
    public GameObject gameObject;

    final void init(GameObject gameObject) {
        LifeCycleManager.getInstance().notifyCreation(this);
        this.gameObject = gameObject;
        transform = gameObject.transform;
    }

    public <T extends GameComponent> T getComponent(Class<T> componentClass) {
        return gameObject.getComponent(componentClass);
    }

    public <T extends GameComponent> T FindObjectOfType(Class<T> componentClass) {
        return SceneManager.getActiveScene().FindObjectOfType(componentClass);
    }


    // métodos de ciclo de vida

    public void awake() {
    }

    public void start() {
    }

    public void fixedUpdate() {
    }

    public void update() {
    }

    public void lateUpdate() {
    }
}
