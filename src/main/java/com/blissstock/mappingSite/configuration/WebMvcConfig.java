package com.blissstock.mappingSite.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ログイン画面のコントローラ部分の役割はWebSecurityConfigが担っているので、
 * ViewNameと画面の対応づけを行うために実装
 * @author aoi
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	/**
	 * 「/login」というURLからN00001_login.htmlを呼び出す
	 */
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/N00001_Login").setViewName("N00001_Login");

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/images/**").addResourceLocations("file:uploads/profiles/");
		
	}
	
	

}
