#version 330 core

in vec3 a_position;
in vec3 a_normal;
in vec2 a_texCoord0;

uniform mat4 worldMatrix;
uniform mat4 projectionMatrix;

//to fragment shader
out vec3 v_normal;
out vec3 v_fragPos;
out vec4 v_color;
out vec2 texCoords;

void main() {
	gl_Position = projectionMatrix * worldMatrix * vec4(a_position, 1.0);
	v_fragPos = vec3(worldMatrix * vec4(a_position, 1.0));
	v_normal = a_normal;
    texCoords = a_texCoord0;
}