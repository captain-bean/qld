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
  
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
    float power;
};



varying vec3 v_normal;
varying vec3 v_fragPos;
uniform vec3 viewPos;

uniform Material material;
uniform PointLight light;  

const float screenGamma = 2.2; // Assume the monitor is calibrated to the sRGB color space  



vec3 CalcPointLight(PointLight light, vec3 normal, vec3 fragPos, vec3 viewDir); 

void main()
{    
   
    vec3 output = CalcPointLight(light, v_normal,v_fragPos, viewPos);
    
    gl_FragColor = vec4(output, 1.0);
}

vec3 CalcPointLight(PointLight light, vec3 normal, vec3 fragPos, vec3 viewPos){


    // ambient
    vec3 ambient = (light.ambient) * material.ambient;
  	
    // diffuse 
    vec3 norm = normalize(normal);
    vec3 lightDir = normalize(light.position - fragPos);
    float diff = max(dot(norm, lightDir), 0.0);
    vec3 diffuse = (light.diffuse * light.power) * (diff * material.diffuse);
    
    // specular
    vec3 viewDir = normalize(viewPos - fragPos);
    vec3 reflectDir = reflect(-lightDir, norm);  
    vec3 halfwayDir = normalize(lightDir + viewDir);  
    float spec = pow(max(dot(normal, halfwayDir), 0.0), material.shininess);
    vec3 specular = (light.specular) * (spec * material.specular);  
    
    
    return (ambient + diffuse + specular);

}