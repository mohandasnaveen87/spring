package com.example.demo;

import java.sql.SQLException;
import java.util.Locale;

import javax.sql.DataSource;
//import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@ConfigurationProperties("oracle")
@EnableHypermediaSupport(type =HypermediaType.HAL_FORMS)
//@EnableSwagger2
public class OracleConfiguration {
   // @NotNull
    private String username;

   // @NotNull
    private String password;

   // @NotNull
    private String url;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Bean
    DataSource dataSource() throws SQLException {

        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setURL(url);
        dataSource.setImplicitCachingEnabled(true);
        dataSource.setFastConnectionFailoverEnabled(true);
        return dataSource;
    }
    @Bean 
    public LocaleResolver localeResolver() {
    	//SessionLocaleResolver localeResolver =new SessionLocaleResolver();
    	AcceptHeaderLocaleResolver localeResolver=new AcceptHeaderLocaleResolver();
    	localeResolver.setDefaultLocale(Locale.US);
    	return localeResolver;
    }
	/*
	 * @Bean public ResourceBundleMessageSource bundleMessageSource() {
	 * ResourceBundleMessageSource bundleMessageSource=new
	 * ResourceBundleMessageSource(); bundleMessageSource.setBasename("messages");
	 * return bundleMessageSource;
	 * 
	 * }
	 */
	/*
	 * @SuppressWarnings("deprecation")
	 * 
	 * @Bean public LinkDiscoverers discoverers() { List<LinkDiscoverer> plugins =
	 * new ArrayList<>(); plugins.add(new CollectionJsonLinkDiscoverer()); return
	 * new LinkDiscoverers(SimplePluginRegistry.create(plugins)); }
	 * 
	 * @Bean public Docket api(){ return new Docket(DocumentationType.SWAGGER_2) ; }
	 */
}