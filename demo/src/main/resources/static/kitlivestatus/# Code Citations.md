# Code Citations

## License: unknown
https://github.com/LKostrzewa/NTI/tree/bc45bbf7b3c66c77da10e7313822d009fe36584d/backend/src/main/java/pl/lodz/p/it/insta/config/WebConfig.java

```
context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
```


## License: unknown
https://github.com/Monesh1711/UPSTAC-Week3/tree/4c4b68750d21cf260985c7b9e493d74bda06b871/src/main/java/org/upgrad/upstac/config/security/WebConfig.java

```
org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry
```


## License: unknown
https://github.com/bgpark82/photoshop/tree/6152cd6c0841ba7fddf6ad37126d30cc9e86de80/api/src/main/java/com/bgpark/photoshop/config/WebConfig.java

```
.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.
```


## License: unknown
https://github.com/krzychu124/ScoreArchiveManager/tree/9195938bd856ecce5f392ea805c0c02b6b940efc/src/main/java/pl/applicationserver/scorefilesmanager/config/WebConfig.java

```
.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*
```


## License: unknown
https://github.com/wumanyu1996/pul/tree/c9be7ee1f841c7c65f46a4556cd3f92cf50deb34/src/main/java/com/briup/apps/pul/config/WebConfig.java

```
.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT
```

