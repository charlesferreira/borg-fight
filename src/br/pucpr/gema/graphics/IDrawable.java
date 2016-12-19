package br.pucpr.gema.graphics;

import org.joml.Matrix4f;

public interface IDrawable {
    void draw(RenderContext context, Matrix4f world);
}
