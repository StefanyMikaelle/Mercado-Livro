package com.stefany.mercadolivro.extension

import com.stefany.mercadolivro.controller.request.PostBookRequest
import com.stefany.mercadolivro.controller.request.PostCustomerRequest
import com.stefany.mercadolivro.controller.request.PutBookRequest
import com.stefany.mercadolivro.controller.request.PutCustomerRequest
import com.stefany.mercadolivro.enums.BookStatus
import com.stefany.mercadolivro.enums.CustomerStatus
import com.stefany.mercadolivro.model.BookModel
import com.stefany.mercadolivro.model.CustomerModel

fun PostCustomerRequest.toCustomerModel() : CustomerModel {
    return CustomerModel(name = this.name , email = this.email, status = CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomerModel(previousValue : CustomerModel) : CustomerModel {
    return CustomerModel(id = previousValue.id , name = this.name , email = this.email, status = previousValue.status)
}

fun PostBookRequest.toBookModel(customer: CustomerModel) : BookModel{
    return BookModel (
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer

    )
}

fun PutBookRequest.toBookModel(previousValue: BookModel) : BookModel{
    return BookModel (
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        price = this.price ?: previousValue.price ,
        status = previousValue.status,
        customer = previousValue.customer
    )
}