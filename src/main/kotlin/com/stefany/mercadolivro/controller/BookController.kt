package com.stefany.mercadolivro.controller

import com.stefany.mercadolivro.controller.request.PostBookRequest
import com.stefany.mercadolivro.controller.request.PutBookRequest
import com.stefany.mercadolivro.extension.toBookModel
import com.stefany.mercadolivro.model.BookModel
import com.stefany.mercadolivro.service.BookService
import com.stefany.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("book")
class BookController (
    val customerService: CustomerService ,
    val bookService : BookService
){
    @PostMapping
    fun create(@RequestBody request: PostBookRequest){
        val customer = customerService.findById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping
    fun findAll () : List<BookModel>{
        return bookService.findAll()
    }

    @GetMapping("/active")
    fun findActives(): List<BookModel>{
        return bookService.findActives();
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int) : BookModel {
        return bookService.findById(id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int){
        bookService.delete(id)
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updata(@PathVariable id: Int, @RequestBody book: PutBookRequest){
        val bookSaved = bookService.findById(id)
        bookService.update(book.toBookModel(bookSaved))
    }
}

