package br.pucpr.borgfight.scripts;

import br.pucpr.borgfight.prefabs.PlayerShip;
import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.Time;

public class GameManager extends GameComponent {
    public float startingTime = 60;
    public int startingEnemies = 5;

    public float elapsedTime;
    public int currentEnemies;

    private static GameManager instance;
    private PlayerShip playerShip;
    private br.pucpr.borgfight.scripts.HUD hud;

    public static GameManager getInstance() {
        if (instance == null)
            instance = GameObject.instantiate().addComponent(GameManager.class);
        return instance;
    }

    @Override
    public void awake() {
        currentEnemies = startingEnemies;
    }

    @Override
    public void update() {
        elapsedTime += Time.deltaTime;
        if (relativeEnemiesLeft() <= 0) {
            gameOver(hud.victory);
            return;
        }
        if (relativeTimeLeft() <= 0) {
            gameOver(hud.defeat);
            return;
        }
    }

    public float relativeTimeLeft() {
        return 1 - elapsedTime / startingTime;
    }

    public float relativeEnemiesLeft() {
        return (float) currentEnemies / startingEnemies;
    }

    public void setPlayerShip(PlayerShip playerShip) {
        this.playerShip = playerShip;
    }

    public void setHud(HUD hud) {
        this.hud = hud;
    }

    private void gameOver(GameObject message) {
        message.setActive(true);
        hud.quit.setActive(true);
        playerShip.disableInputs();
        gameObject.setActive(false);
    }
}
