package br.pucpr.borgfight.prefabs;

import br.pucpr.borgfight.scripts.ShipMovement;
import br.pucpr.gema.core.GameObject;

public class Ship extends GameObject {
    public Ship() {
        super(true);
        AddComponent(ShipMovement.class);
    }
}
