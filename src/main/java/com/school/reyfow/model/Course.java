package com.school.reyfow.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamoDBTable(tableName = "course")
public class Course {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBRangeKey()
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "sortKey-gsi", attributeName = "sortKey")
    private String sortKey;

    @DynamoDBAttribute
    private String name;

    @DynamoDBAttribute
    private Integer hours;

    @DynamoDBAttribute
    private String studentName;

    @DynamoDBAttribute
    private String studentId;

    private String email;


}