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
    private val graphQLDataFetchers: GraphQLDataFetchers
) {
    @Bean
    fun graphQL(): GraphQL {
        val url = Resources.getResource("schema.graphqls")
        val sdl: String = Resources.toString(url, UTF_8)
        val graphQLSchema = buildSchema(sdl)
        return GraphQL.newGraphQL(graphQLSchema).build()
    }

    private fun buildSchema(sdl: String): GraphQLSchema? {
        val typeRegistry = SchemaParser().parse(sdl)
        val runtimeWiring = buildWiring()
        val schemaGenerator = SchemaGenerator()
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)
    }

    private fun buildWiring(): RuntimeWiring =
        RuntimeWiring.newRuntimeWiring()
            .type(
                newTypeWiring("Query")
                    .dataFetcher("bookById", graphQLDataFetchers.bookByIdDataFetcher)
            )
            .type(
                newTypeWiring("Book")
                    .dataFetcher("author", graphQLDataFetchers.authorDataFetcher)
            )
            .build()
}
