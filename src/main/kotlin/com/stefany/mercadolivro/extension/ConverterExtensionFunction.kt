package com.stefany.mercadolivro.extension

import com.stefany.mercadolivro.controller.request.PostCustomerRequest
import com.stefany.mercadolivro.controller.request.PutCustomerRequest
import com.stefany.mercadolivro.model.CustomerModel

fun PostCustomerRequest.toCustomerModel() : CustomerModel {
    return CustomerModel(name = this.name , email = this.email)
}

fun PutCustomerRequest.toCustomerModel(id : String) : CustomerModel {
    return CustomerModel(id = id , name = this.name , email = this.email)
}