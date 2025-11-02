package com.stefany.mercadolivro.controller

import com.stefany.mercadolivro.controller.request.PostCustomerRequest
import com.stefany.mercadolivro.controller.request.PutCustomerRequest
import com.stefany.mercadolivro.extension.toCustomerModel
import com.stefany.mercadolivro.model.CustomerModel
import com.stefany.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customer")
class CustomerController (

    val customerService: CustomerService

){


    @GetMapping
    fun getAll(@RequestParam name : String? ) : List<CustomerModel> {
       return customerService.getAll(name)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest)  {
        return customerService.create(customer.toCustomerModel())
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerModel {
        return customerService.findById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: PutCustomerRequest) {
        val customerSaved = customerService.findById(id)
        return customerService.updateCustomer(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        return customerService.delete(id)
    }

}