package br.pucpr.borgfight.prefabs;

import br.pucpr.borgfight.scripts.ShipInput;
import br.pucpr.borgfight.scripts.ShipMovement;
import br.pucpr.gema.core.GameObject;

public class Ship extends GameObject {
    @Override
    protected void init() {
        AddComponent(ShipInput.class);
        AddComponent(ShipMovement.class);
    }
}
