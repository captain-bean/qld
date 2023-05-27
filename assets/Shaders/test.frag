#ifdef GL_ES 
precision mediump float;
#endif

varying vec4 v_color;
varying vec3 v_normal;
varying vec3 v_fragPos;

uniform vec3 u_lightPos; 
uniform vec3 u_lightColor;
uniform float u_lightPower;
uniform vec3 u_viewPos;
uniform float u_shininess;
uniform vec3 u_ambient;

const vec3 specColor = vec3(1.0, 1.0, 1.0);

const float screenGamma = 2.2; // Assume the monitor is calibrated to the sRGB color space


void main() {



vec3 normal = normalize(v_normal); 
vec3 lightDir = u_lightPos - v_fragPos;
float distance = length(lightDir);
float attenuation = 1 / (distance * distance) ;
lightDir = normalize(lightDir);


float lambertian = max(dot(lightDir, normal), 0.0);
float specular = 0.0; 


//Specular Highlight
if (lambertian > 0.0) {

vec3 viewDir = normalize(u_viewPos - v_fragPos);
vec3 halfDir = normalize(lightDir + viewDir);
float specAngle = max(dot(halfDir, normal), 0.0);
specular = pow(specAngle, u_shininess);  
}



vec3 colorLinear = u_ambient * v_color +
    v_color * lambertian * u_lightColor * u_lightPower * attenuation +
    specColor * specular * u_lightColor * u_lightPower * attenuation;


// apply gamma correction (assume u_ambient, v_color and specColor
// have been linearized, i.e. have no gamma correction in them)
vec3 colorGammaCorrected = pow(colorLinear, vec3(screenGamma));
// use the gamma corrected color in the fragment

//gl_FragColor = vec4(colorLinear,1.0);
gl_FragColor = vec4(colorGammaCorrected, 1.0);

//gl_FragColor = v_color;
}

