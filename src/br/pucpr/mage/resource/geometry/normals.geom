#version 330

layout (triangles) in;
layout (line_strip, max_vertices = 6) out;

in vec3 vNormal[];

const float SIZE = 0.6f;

void drawNormal(int index) {
    gl_Position = gl_in[index].gl_Position;
    EmitVertex();

    gl_Position = gl_in[index].gl_Position + vec4(vNormal[index], 0.0f) * SIZE;
    EmitVertex();
    EndPrimitive();
}

void main() {
    drawNormal(0);
    drawNormal(1);
    drawNormal(2);
}
