package com.clonecoding.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * packageName    : com.clonecoding.dev.config
 * fileName       : WebConfig
 * author         : hagjoon
 * date           : 2023-11-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-25        hagjoon       최초 생성
 */
public class WebConfig implements WebMvcConfigurer {

    @Bean
    MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }
}
