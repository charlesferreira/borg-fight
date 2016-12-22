package br.pucpr.gema.core.components;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.core.SceneManager;
import br.pucpr.gema.core.objects.Camera;
import br.pucpr.gema.graphics.IDrawable;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import br.pucpr.mage.Material;
import br.pucpr.mage.Mesh;
import br.pucpr.mage.Shader;
import org.joml.Matrix4f;

public class MeshRenderer extends GameComponent implements IDrawable {
    public Mesh mesh;
    public Material material;

    public MeshRenderer(GameObject gameObject) {
        super(gameObject);
        material = new DefaultMaterial();
    }

    @Override
    public void draw(Matrix4f world) {
        if (mesh == null) return;
        GameScene scene = SceneManager.getActiveScene();
        Camera camera = (Camera) scene.camera.GetComponent(Camera.class);
        Shader shader = material.getShader();
        shader.bind()
                .setUniform("uView", camera.getView())
                .setUniform("uProjection", camera.getProjection())
                .setUniform("uCameraPosition", camera.getPosition());

        scene.light.apply(shader);
        shader.unbind();

        mesh.setUniform("uWorld", world);
        mesh.draw(material);
    }
}
