package com.jrstep.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.condition.NameValueExpression;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class Swag extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    Docket swaggerDocV1() {
        return configureVersion("V1").pathMapping("/");
    }

    private Docket configureVersion(String version) {
        return new Docket(DocumentationType.SWAGGER_2).groupName(version).select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any()).build().apiInfo(apiInfo(version)).useDefaultResponseMessages(false);
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder().deepLinking(true).displayOperationId(false).defaultModelExpandDepth(1)
                .defaultModelExpandDepth(1).defaultModelRendering(ModelRendering.MODEL).displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE).filter(false).maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA).showExtensions(false).tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS).validatorUrl(null)
                .build();
    }

    private ApiInfo apiInfo(String version) {
        return new ApiInfoBuilder().title("Calculate Payment")
                .description("Calculate Payment API")
                .version(version)
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}
