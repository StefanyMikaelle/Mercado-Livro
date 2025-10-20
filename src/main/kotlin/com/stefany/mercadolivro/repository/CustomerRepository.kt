package com.stefany.mercadolivro.repository

import com.stefany.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerModel, Int> {

    fun findByNameContaining(name : String) : List<CustomerModel>
}