/**
 * Config that enables Swagger2 Documentation
 */

package Group.groceryservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("Group.groceryservice"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    /**
     * Specifies data for docs
     */
    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "GroceryService",
                "Grocery Service API",
                "V1.0",
                "TERMS OF SERVICE URL",
                new Contact("NAME","URL","EMAIL"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}
