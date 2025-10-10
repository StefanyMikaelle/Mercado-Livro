package com.stefany.mercadolivro.service

import com.stefany.mercadolivro.controller.request.PostCustomerRequest
import com.stefany.mercadolivro.controller.request.PutCustomerRequest
import com.stefany.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {

    val customers = mutableListOf<CustomerModel>()

    fun getAll(name : String? ) : List<CustomerModel> {
        name?.let{
            return customers.filter { it.name.contains(name, true) }
        }
        return customers
    }

    fun create(customer: CustomerModel)  {

        val id = if(customers.isEmpty()){
            1
        } else {
            customers.last().id!!.toInt() + 1
        }.toString()

        customer.id = id

        customers.add(CustomerModel(id, customer.name, customer.email))
    }

    fun getCustomer(id: String): CustomerModel {
        return customers.filter { it.id == id }.first()
    }

    fun updateCustomer( customerModel: CustomerModel) {
        customers.filter { it.id == customerModel.id }.first().let{
            it.name = customerModel.name
            it.email = customerModel.email
        }
    }

    fun delete( id: String) {
        customers.removeIf {
            it.id == id
        }
    }
}