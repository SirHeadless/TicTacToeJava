//package com.sirheadless.kt.Config
//
//
//import org.springframework.context.annotation.Configuration
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
//
//@Configuration
//open class MvcConfig : WebMvcConfigurerAdapter() {
//
//    override fun addViewControllers(registry: ViewControllerRegistry?) {
//        registry!!.addViewController("/home").setViewName("home")
//        registry.addViewController("/").setViewName("home")
//        registry.addViewController("/hello").setViewName("hello")
//        registry.addViewController("/login").setViewName("login")
//    }
//
//}