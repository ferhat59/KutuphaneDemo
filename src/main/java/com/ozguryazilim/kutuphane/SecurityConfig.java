package com.ozguryazilim.kutuphane;



import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.inMemoryAuthentication()
                .withUser("marko").password("{noop}12345").roles("USER").and()
                .withUser("jelena").password("{noop}12345").roles("USER").and()
                .withUser("zeljko").password("{noop}12345").roles("ADMIN");
    }

  


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
               .antMatchers("/book/new").hasAnyRole("USER", "ADMIN")
      
                
                .antMatchers("/add-publisher").hasRole("ADMIN")
                .antMatchers("/add-author").hasRole("ADMIN")
                .antMatchers("/book/edit/{bookId}").hasRole("ADMIN")
                
                .antMatchers("/book/delete/*").hasRole("ADMIN")
                .antMatchers("/save-publisher").hasRole("ADMIN")
                .antMatchers("/save-author").hasRole("ADMIN")
                .antMatchers("/author/{id}/book/{bookName}").hasRole("ADMIN")
                
                .antMatchers("/").permitAll()
                .and()
                .formLogin()
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");

        http.csrf().disable();
        http.headers().frameOptions().disable();

    }
}