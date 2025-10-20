package com.stefany.mercadolivro.service

import com.stefany.mercadolivro.model.CustomerModel
import com.stefany.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService (
    val customerRepository: CustomerRepository
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

    fun getCustomer(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow()
    }

    fun updateCustomer( customerModel: CustomerModel) {

        if(!customerRepository.existsById(customerModel.id!!)){
            throw Exception()
        }

        customerRepository.save(customerModel)
    }

    fun delete( id: Int) {
        customerRepository.deleteById(id)
    }
}