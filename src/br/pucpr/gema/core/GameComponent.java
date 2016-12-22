package br.pucpr.gema.core;

abstract public class GameComponent {
    protected GameObject gameObject;

    public GameComponent(GameObject gameObject) {
        LifeCycleManager.getInstance().notifyCreation(this);
        this.gameObject = gameObject;
    }

    // métodos de ciclo de vida

    public void awake() {
    }

    public void start() {
    }

    public void update() {
    }

    public void lateUpdate() {
    }
}
