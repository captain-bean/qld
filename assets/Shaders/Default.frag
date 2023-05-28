#ifdef GL_ES 
precision mediump float;
#endif

struct Material {
    vec3 ambient;
    vec4 diffuse;
    vec3 specular;
    float shininess;
}; 

struct PointLight {
    vec3 position;
  
    float constant;
    float linear;
    float quadratic; 
    float intensity;

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
    
};



varying vec3 v_normal;
varying vec3 v_fragPos;
uniform vec3 viewPos;

uniform Material material;

#define NR_POINT_LIGHTS 99
uniform PointLight pointLights[NR_POINT_LIGHTS]; 
uniform int pointLightsSize;

const float screenGamma = 2.2; // Assume the monitor is calibrated to the sRGB color space  



vec3 CalcPointLight(PointLight light, vec3 normal, vec3 fragPos, vec3 viewDir); 

void main()
{    
   
    vec3 norm = normalize(v_normal);
    vec3 viewDir = normalize(viewPos - v_fragPos);

    vec3 result = vec3(0,0,0);
    for(int i = 0; i < pointLightsSize; i++){
     result += CalcPointLight(pointLights[i], norm, v_fragPos, viewDir);
    }
    //gl_FragColor = vec4(CalcPointLight(pointLights[0],norm, v_fragPos, viewDir),1);
    gl_FragColor = vec4(result, 1.0);
}

vec3 CalcPointLight(PointLight light, vec3 normal, vec3 fragPos, vec3 viewDir){

    vec3 lightDir = normalize(light.position - fragPos);
    // diffuse shading
    float diff = max(dot(normal, lightDir), 0.0);
    // specular shading
    vec3 reflectDir = reflect(-lightDir, normal);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    // attenuation
    float distance    = length(light.position - fragPos);

    float attenuation = 1.0 / (light.constant + light.linear * distance + light.quadratic * (distance * distance));    
    // combine results
    vec3 ambient  = light.ambient  * material.ambient;
    vec3 diffuse  = light.diffuse  * diff * material.diffuse;
    vec3 specular = light.specular * spec * material.specular;
    ambient  = ambient * attenuation;
    diffuse  = diffuse * attenuation;
    specular = specular * attenuation;
    return (ambient + diffuse + specular);

}