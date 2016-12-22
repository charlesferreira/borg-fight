package br.pucpr.gema.core;

import java.util.ArrayList;
import java.util.List;

class LifeCycleManager {
    private static LifeCycleManager instance = new LifeCycleManager();
    private List<GameComponent> created = new ArrayList<>();

    public static LifeCycleManager getInstance() {
        return instance;
    }

    void notifyCreation(GameComponent component) {
        created.add(component);
    }

    void start() {
        created.forEach(GameComponent::start);
        created.clear();
    }
}
