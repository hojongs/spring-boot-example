package com.hojong.springbootexample.graphql

import com.google.common.io.Resources
import graphql.GraphQL
import graphql.schema.GraphQLSchema
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeRuntimeWiring.newTypeWiring
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import kotlin.text.Charsets.UTF_8

@Component
class GraphQLProvider(
    private val graphQLDataFetchers: GraphQLDataFetchers,
    private val pageCountDataFetcher: PageCountDataFetcher,
) {
    companion object {
        const val schemaResourceName = "schema.graphqls"
    }

    @Bean
    fun graphQL(): GraphQL =
        Resources.getResource(schemaResourceName)
            .let { url -> Resources.toString(url, UTF_8) }
            .let { sdl -> buildSchema(sdl) }
            .let { graphQLSchema -> GraphQL.newGraphQL(graphQLSchema).build() }

    private fun buildSchema(sdl: String): GraphQLSchema =
        SchemaParser().parse(sdl)
            .let { typeRegistry -> SchemaGenerator().makeExecutableSchema(typeRegistry, buildRuntimeWiring()) }

    private fun buildRuntimeWiring(): RuntimeWiring =
        RuntimeWiring.newRuntimeWiring()
            .type(
                newTypeWiring("Query")
                    .dataFetcher("bookById", graphQLDataFetchers.bookByIdDataFetcher)
            )
            .type(
                newTypeWiring("Book")
                    .dataFetcher("author", graphQLDataFetchers.authorDataFetcher)
//                    .dataFetcher("pageCount", pageCountDataFetcher)
            )
            .build()
}
