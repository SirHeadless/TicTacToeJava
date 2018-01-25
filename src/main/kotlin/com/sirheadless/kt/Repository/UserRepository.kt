//package com.sirheadless.kt.Repository
//
//import org.apache.catalina.Manager
//
//import org.springframework.data.repository.Repository
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//@RepositoryRestResource(exported = false)
//interface ManagerRepository : Repository<Manager, Long> {
//
//    fun save(manager: Manager): Manager
//
//    fun findByName(name: String): Manager
//
//}