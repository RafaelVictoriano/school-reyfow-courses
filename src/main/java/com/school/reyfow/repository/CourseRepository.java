package com.school.reyfow.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.school.reyfow.model.Course;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class CourseRepository {

    @Inject
    DynamoDBMapper dynamoDBMapper;

    public void save(Course course) {
        dynamoDBMapper.save(course);
    }

    public Optional<Course> findById(String id, String rangeKey) {
        return Optional.ofNullable(dynamoDBMapper.load(Course.class, id, rangeKey));
    }

    public Optional<List<Course>> findAll(String tableName) {
        final var keyValues = Map.of(":sk", new AttributeValue()
                .withS(tableName));

        final var queryExpression = new DynamoDBQueryExpression<Course>()
                .withIndexName("sortKey-gsi").withConsistentRead(false)
                .withKeyConditionExpression("sortKey = :sk")
                .withExpressionAttributeValues(keyValues);

        return Optional.ofNullable(dynamoDBMapper.query(Course.class, queryExpression));

    }
}
