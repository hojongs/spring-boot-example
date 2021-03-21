package com.hojong.springbootexample.graphql

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class PageCountDataFetcher :
    DataFetcher<String?> by DataFetcher({ dataFetchingEnvironment: DataFetchingEnvironment ->
        val book = dataFetchingEnvironment.getSource<Map<String, String>>()
        book["totalPages"]
    })
