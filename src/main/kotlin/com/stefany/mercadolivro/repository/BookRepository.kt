package com.stefany.mercadolivro.repository

import com.stefany.mercadolivro.enums.BookStatus
import com.stefany.mercadolivro.model.BookModel
import com.stefany.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookModel, Int> {
    fun findByStatus(status: BookStatus): List<BookModel>
    fun findByCustomer(customerModel: CustomerModel) : List<BookModel>
}