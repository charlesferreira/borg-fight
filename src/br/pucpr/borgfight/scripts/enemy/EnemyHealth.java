package br.pucpr.borgfight.scripts.enemy;

import br.pucpr.borgfight.scripts.GameManager;
import br.pucpr.gema.core.GameComponent;

public class EnemyHealth extends GameComponent {

    @Override
    public void onDestroy() {
        GameManager.getInstance().currentEnemies -= 1;
    }
}
