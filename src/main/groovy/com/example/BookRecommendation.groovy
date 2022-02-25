package com.example;


import groovy.transform.CompileStatic;
import groovy.transform.EqualsAndHashCode;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;

import javax.validation.constraints.NotBlank;

@CompileStatic
@EqualsAndHashCode
@Introspected
public class BookRecommendation {
    @NonNull
    @NotBlank
    final String name

    BookRecommendation(@NonNull @NotBlank String name) {
        this.name = name
    }
}
