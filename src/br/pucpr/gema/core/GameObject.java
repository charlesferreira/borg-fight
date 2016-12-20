package br.pucpr.gema.core;

import br.pucpr.gema.core.components.MeshRenderer;
import br.pucpr.gema.core.components.Transform;
import br.pucpr.gema.graphics.IDrawable;
import org.joml.Matrix4f;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GameObject implements IDrawable {
    public final MeshRenderer renderer;
    public final Transform transform;
    private List<GameComponent> components = new ArrayList<>();
    private List<GameObject> children = new ArrayList<>();
    private GameObject parent;

    // todo: remover o parâmetro boolean (renderer estava tentando criar material antes do init() da cena)
    protected GameObject(boolean withRenderer) {
        renderer = withRenderer
                ? (MeshRenderer) AddComponent(MeshRenderer.class)
                : null;
        transform = (Transform) AddComponent(Transform.class);
    }

    public GameComponent AddComponent(Class<? extends GameComponent> componentClass) {
        try {
            GameComponent component = componentClass.getDeclaredConstructor(GameObject.class).newInstance(this);
            components.add(component);
            return component;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    void update() {
        components.forEach(GameComponent::update);
        children.forEach(GameObject::update);
    }

    @Override
    public final void draw(Matrix4f world) {
        world.translate(transform.getLocalPosition());
        world.scale(transform.getLocalScale());
        world.rotate(transform.getLocalRotation());
        onDraw(world);
        children.forEach(child -> child.draw(new Matrix4f(world)));
    }

    protected void onDraw(Matrix4f world) {
        if (renderer == null) return;
        renderer.draw(world);
    }

    public GameObject addChild(GameObject child) {
        children.add(child);
        child.moveToParent(this);
        return child;
    }

    private GameObject removeChild(GameObject child) {
        children.remove(child);
        return this;
    }

    public GameObject moveToParent(GameObject parent) {
        removeFromParent();
        this.parent = parent;
        return this;
    }

    public GameObject removeFromParent() {
        if (parent != null) {
            parent.removeChild(this);
            moveToParent(null);
        }

        return this;
    }

    public GameObject getParent() {
        return parent;
    }
}
