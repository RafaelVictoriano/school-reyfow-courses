package com.school.reyfow.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Optional;

@ApplicationScoped
public class DynamoDbConfigure {

    @ConfigProperty(name = "aws.local", defaultValue = "false")
    boolean isLocal;

    @ConfigProperty(name = "aws.endpoint")
    Optional<String> endpoint;

    @ConfigProperty(name = "aws.region")
    Optional<String> region;

    @Produces
    public DynamoDBMapper dynamoDBMapper() {
        if (isLocal) {
            return new DynamoDBMapper(AmazonDynamoDBClientBuilder
                    .standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint.get(), region.get()))
                    .build());
        }
        return new DynamoDBMapper(AmazonDynamoDBClientBuilder.defaultClient());
    }
}