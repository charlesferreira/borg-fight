#version 330

uniform sampler2D uTexture;
uniform float uAlpha;

in vec2 vTexCoord;

out vec4 outColor;

void main() {
    float alpha = clamp(uAlpha, 0.0, 1.0);
    outColor = texture(uTexture, vTexCoord);
    outColor.a *= alpha;
}