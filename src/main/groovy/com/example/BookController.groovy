package com.example

import groovy.transform.CompileStatic
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux

@CompileStatic
@Controller("/books")
class BookController {
    private final BookCatalogueOperations bookCatalogueOperations
    private final BookInventoryOperations bookInventoryOperations

    BookController(BookCatalogueOperations bookCatalogueOperations,
                   BookInventoryOperations bookInventoryOperations) {
        this.bookCatalogueOperations = bookCatalogueOperations
        this.bookInventoryOperations = bookInventoryOperations
    }

    @Get
    Publisher<BookRecommendation> index() {
        Flux.from(bookCatalogueOperations.findAll())
                .flatMap(b -> Flux.from(bookInventoryOperations.stock(b.isbn))
                        .filter(Boolean::booleanValue)
                        .map(rsp -> b)
                ).map(book -> new BookRecommendation(book.name))
    }
}
