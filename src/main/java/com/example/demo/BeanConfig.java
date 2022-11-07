package com.example.demo;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;

@Configuration
public class BeanConfig {

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    EmployeesResolver employeesResolver;

    @Bean
    public GraphQLSchema graphQLSchema(){
        SchemaParser schemaParser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();

        Resource resource = null;
        try{ resource = resourceLoader.getResource("classpath:models.graphqls");}
        catch(Exception exc){ System.out.println("ERROR: getting resource: " + exc.getMessage()); }

        File schemaFile = null;
        try{schemaFile = resource.getFile();}
        catch(Exception exc){ System.out.println("ERROR: getting file: " + exc.getMessage()); }

        TypeDefinitionRegistry registry = null;
        try{ registry = schemaParser.parse(schemaFile);}
        catch(Exception exc){ System.out.println("ERROR: getting registry: " + exc.getMessage()); }

        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type("Queries", builder -> builder
                    .dataFetcher("employees",employeesResolver)
                )
                .build();

        return schemaGenerator.makeExecutableSchema(registry,wiring);
    }

}
