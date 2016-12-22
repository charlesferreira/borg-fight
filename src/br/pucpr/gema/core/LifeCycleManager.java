package br.pucpr.gema.core;

import java.util.ArrayList;
import java.util.List;

class LifeCycleManager {
    private static LifeCycleManager instance = new LifeCycleManager();
    private List<GameComponent> asleep = new ArrayList<>();
    private List<GameComponent> notStarted = new ArrayList<>();

    public static LifeCycleManager getInstance() {
        return instance;
    }

    void notifyCreation(GameComponent component) {
        asleep.add(component);
        notStarted.add(component);
    }

    void start() {
        notStarted.forEach(GameComponent::start);
        notStarted.clear();
    }
}
