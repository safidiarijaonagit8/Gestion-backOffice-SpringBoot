package articles.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SpringSecurity implements WebMvcConfigurer{
	
	 @Autowired
	    private UserDetailsService userDetailsService;

	    @Bean
	    public static PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	                .authorizeHttpRequests((authorize) ->
	                        authorize.requestMatchers("/register/**").permitAll()
	                        .requestMatchers("/articles/api/**").permitAll()
	                                .requestMatchers("/index").permitAll()
	                                .requestMatchers("/users").hasRole("ADMIN")
	                                .requestMatchers("/accueil").hasRole("ADMIN")/*ou ROLE_ADMIN??*/
									.requestMatchers("/images/**").permitAll()
	                                .requestMatchers("/formajoutarticle").hasRole("ADMIN")
	                                .requestMatchers("/savearticle").hasRole("ADMIN")
	                                .requestMatchers("/articles").hasRole("ADMIN")
	                                .requestMatchers("/detailarticle/**").hasRole("ADMIN")
	                                .requestMatchers("/editarticle/**").hasRole("ADMIN")
	                                .requestMatchers("/editarticlefunc").hasRole("ADMIN")
	                                .requestMatchers("/deletearticle/**").hasRole("ADMIN")
									.requestMatchers("/formajoutevenement").hasRole("ADMIN")
									.requestMatchers("/evenements").hasRole("ADMIN")
									.requestMatchers("/saveevenement").hasRole("ADMIN")
									).formLogin(
	                        form -> form
	                                .loginPage("/login")
	                                .loginProcessingUrl("/login")
	                                .defaultSuccessUrl("/accueil")
	                                .permitAll()
	                ).logout(
	                        logout -> logout
	                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                                .permitAll()
	                );
	        return http.build();
	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	                .userDetailsService(userDetailsService)
	                .passwordEncoder(passwordEncoder());
	    }
	    
	   
	    @Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	        return (web) -> web.ignoring().requestMatchers("/uploads/**");
	    }
	    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
		.addResourceLocations("file:C:\\SPRING Boot\\gestionarticles\\src\\main\\resources\\static\\uploads\\");
    }

	/*@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }*/

	
	    
	  
	

}
