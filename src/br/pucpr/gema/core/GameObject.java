package br.pucpr.gema.core;

import br.pucpr.gema.core.components.MeshRenderer;
import br.pucpr.gema.core.components.Transform;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
    public MeshRenderer renderer;
    public Transform transform;
    private List<GameComponent> components = new ArrayList<>();
    private List<GameObject> children = new ArrayList<>();
    private List<GameObject> newborns = new ArrayList<>();
    private List<GameObject> defuncts = new ArrayList<>();
    private GameObject parent;

    private float fixedUpdateTime = 0f;

    protected GameObject() {
    }

    void preInit() {
        transform = addComponent(Transform.class);
        renderer = addComponent(MeshRenderer.class);
    }

    protected void init() {
    }

    final void fixedUpdate() {
        fixedUpdateTime += Time.deltaTime;
        while (fixedUpdateTime > Time.fixedDeltaTime) {
            fixedUpdateTime -= Time.fixedDeltaTime;
            components.forEach(GameComponent::fixedUpdate);
        }

        children.forEach(GameObject::fixedUpdate);
        updateNewborns();
        updateDefuncts();
    }

    public final void update() {
        components.forEach(GameComponent::update);
        children.forEach(GameObject::update);
        updateNewborns();
        updateDefuncts();
    }

    private void updateNewborns() {
        if (newborns.size() == 0) return;

        newborns.forEach(child -> children.add(child));
        newborns.clear();
    }

    private void updateDefuncts() {
        if (defuncts.size() == 0) return;

        defuncts.forEach(child -> children.remove(child));
        defuncts.clear();
    }

    final void lateUpdate() {
        components.forEach(GameComponent::lateUpdate);
        children.forEach(GameObject::lateUpdate);
        updateNewborns();
        updateDefuncts();
    }

    public final void draw() {
        if (renderer != null)
            renderer.draw(transform.getWorld());
        children.forEach(child -> child.draw());
    }

    private GameObject removeChild(GameObject child) {
        defuncts.add(child);
        child.parent = null;
        return this;
    }

    public GameObject setParent(GameObject parent) {
        if (parent == null) {
            SceneManager.getActiveScene().addChild(this);
        } else {
            removeFromParent();
            parent.newborns.add(this);
            this.parent = parent;
        }
        return this;
    }

    private GameObject removeFromParent() {
        if (parent != null)
            parent.removeChild(this);

        return this;
    }

    public GameObject getParent() {
        return parent;
    }

    public <T extends GameComponent> T addComponent(Class<T> componentClass) {
        try {
            GameComponent component = componentClass.newInstance();
            component.init(this);
            component.awake();
            components.add(component);
            return (T) component;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T extends GameComponent> T getComponent(Class<T> componentClass) {
        for (GameComponent component : components) {
            if (componentClass.isAssignableFrom(component.getClass()))
                return (T) component;
        }
        return null;
    }

    public static GameObject instantiate() {
        return prepareInstance(new GameObject());
    }

    public static <T extends GameObject> T instantiate(Class<T> prefabClass) {
        try {
            return prepareInstance(prefabClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T extends GameObject> T instantiate(Class<T> prefabClass, GameObject parent) {
        GameObject instance = instantiate(prefabClass);
        if (instance != null)
            instance.setParent(parent);
        return (T) instance;
    }

    private static <T extends GameObject> T prepareInstance(T instance) {
        instance.preInit();
        instance.init();
        SceneManager.getActiveScene().addChild(instance);
        return instance;
    }
}
