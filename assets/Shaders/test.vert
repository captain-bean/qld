attribute vec3 a_position;
attribute vec3 a_normal;
attribute vec2 a_texCoord0;

uniform vec4 u_color;
uniform mat4 u_worldTrans;
uniform mat4 u_projTrans;

//to fragment shader
varying vec4 v_color;
varying vec3 v_normal;
varying vec3 v_fragPos;  

void main() {
	v_color = u_color;
	gl_Position = u_projTrans * u_worldTrans * vec4(a_position, 1.0);
	v_fragPos = vec3(u_worldTrans * vec4(a_position, 1.0));
	v_normal = a_normal;
	
}