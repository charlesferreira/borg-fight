package br.pucpr.gema.core;

abstract public class GameComponent {
    protected GameObject gameObject;

    public GameComponent(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public void update() {
    }
}
