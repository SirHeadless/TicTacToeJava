package com.sirheadless.kt.Config

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity




@Configuration
@EnableWebSecurity
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.authorizeRequests()
                .antMatchers( "/home*", "/index*", "/index.html", "/gs-guide-websocket/**").permitAll() //Is here also * working?
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login2.html")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/")
                .failureUrl("/hello.html")
                .permitAll()
                .and()
                .logout()
                .permitAll()

    }


    @Throws(Exception::class)
    override fun configure(web: WebSecurity?) {
        web!!.ignoring().antMatchers("/dist/**", "/node_modules/**")
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth
                .inMemoryAuthentication()
                .withUser("u1").password("1").roles("USER").and()
                .withUser("u3").password("1").roles("USER").and()
                .withUser("u4").password("1").roles("USER").and()
                .withUser("u2").password("1").roles("USER")
    }
}