package com.school.reyfow.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.school.reyfow.model.Course;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
}
