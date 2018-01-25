//package com.sirheadless.kt.User
//
//import com.fasterxml.jackson.annotation.JsonIgnore
//import javax.persistence.Entity
//
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.crypto.password.PasswordEncoder
//import javax.persistence.GeneratedValue
//import javax.persistence.Id
//
//@Entity
//open class User(@Id @GeneratedValue var id: Long, var name : String, var password: String, var roles: Array<out String>){
//
//    companion object {
//
//        val PASSWORD_ENCODER: PasswordEncoder = BCryptPasswordEncoder()
//    }
//
//    init {
//        this.password = PASSWORD_ENCODER.encode(password)
//    }
//
//
//}