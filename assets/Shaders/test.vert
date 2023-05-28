attribute vec3 a_position;
attribute vec3 a_normal;
attribute vec2 a_texCoord0;

uniform mat4 worldMatrix;
uniform mat4 projectionMatrix;

//to fragment shader
varying vec3 v_normal;
varying vec3 v_fragPos;
varying vec4 v_color;

void main() {
	gl_Position = projectionMatrix * worldMatrix * vec4(a_position, 1.0);
	v_fragPos = vec3(worldMatrix * vec4(a_position, 1.0));
	v_normal = a_normal;
	v_color = vec4(0.0,0.0,0.0,0.0);
}