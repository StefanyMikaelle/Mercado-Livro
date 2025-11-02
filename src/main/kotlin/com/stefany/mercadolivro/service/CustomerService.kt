package com.stefany.mercadolivro.service

import com.stefany.mercadolivro.enums.CustomerStatus
import com.stefany.mercadolivro.model.CustomerModel
import com.stefany.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService (
    val customerRepository: CustomerRepository,
    val bookService: BookService
){
    fun getAll(name : String? ) : List<CustomerModel> {
        name?.let{
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel)  {
        customerRepository.save(customer)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow()
    }

    fun updateCustomer( customerModel: CustomerModel) {

        if(!customerRepository.existsById(customerModel.id!!)){
            throw Exception()
        }

        customerRepository.save(customerModel)
    }

    fun delete( id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }
}