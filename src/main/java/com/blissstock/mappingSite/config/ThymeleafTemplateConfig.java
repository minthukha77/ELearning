// package com.blissstock.mappingSite.config;

// import java.nio.charset.StandardCharsets;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.thymeleaf.spring5.SpringTemplateEngine;
// import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
// import org.thymeleaf.templatemode.TemplateMode;
// import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
// import org.thymeleaf.templateresolver.ITemplateResolver;

// @Configuration
// public class ThymeleafTemplateConfig {
//     @Bean
//     public SpringTemplateEngine springTemplateEngine() {
//         SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//         templateEngine.addTemplateResolver(htmlTemplateResolver());
//         templateEngine.addTemplateResolver(templateResolver());
//         return templateEngine;
//     }

//     private ITemplateResolver templateResolver() {
//         SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//         //Ensure the path is right or you work without the logic using the ClassLoaderTemplateResolver
//         templateResolver.setPrefix("classpath:/templates/");
//         templateResolver.setSuffix(".html");
//         templateResolver.setTemplateMode(TemplateMode.HTML);
//         templateResolver.setCharacterEncoding("UTF-8");
//         templateResolver.setCacheable(false);
//         return templateResolver;
//     }


//     @Bean
//     public SpringResourceTemplateResolver htmlTemplateResolver(){
//         SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
//         // emailTemplateResolver.setPrefix("/templates/");
//         emailTemplateResolver.setPrefix("classpath:/templates/");
//         emailTemplateResolver.setSuffix(".html");
//         emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
//         emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
//         return emailTemplateResolver;
//     }
// }
