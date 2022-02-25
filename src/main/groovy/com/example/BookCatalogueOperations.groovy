package com.example

import org.reactivestreams.Publisher

interface BookCatalogueOperations {
    Publisher<Book> findAll()
}
