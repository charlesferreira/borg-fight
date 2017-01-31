package br.pucpr.borgfight.scripts.enemy;

import br.pucpr.gema.core.components.Transform;

abstract class EnemyBehaviour {
    EnemyMovement movement;
    Transform player;

    EnemyBehaviour(EnemyMovement movement, Transform player) {
        this.movement = movement;
        this.player = player;
    }

    abstract public void onStateEnter();
    abstract public void update();
}
