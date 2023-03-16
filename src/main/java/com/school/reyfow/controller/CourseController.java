package com.school.reyfow.controller;

import com.school.reyfow.mapper.dto.CourseDTO;
import com.school.reyfow.mapper.dto.CourseRequestDTO;
import com.school.reyfow.mapper.dto.RegisterStudentDTO;
import com.school.reyfow.service.CourseService;
import com.school.reyfow.service.RegisterStudentService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.net.URI;


@Slf4j
@Path("course")
@RegisterRestClient
public class CourseController {

    @Inject
    CourseService courseService;

    @Inject
    RegisterStudentService registerStudentService;

    @POST
    public Response create(@Valid CourseRequestDTO courseDTO) {
        var courseUuid = courseService.create(courseDTO);
        log.info("Course of {} created success", courseDTO.getName());
        return Response.created(URI.create(courseUuid)).build();
    }

    @POST
    @Path("/register/student")
    public Response registerStudent(@Valid RegisterStudentDTO registerStudentDTO) {
        var regsiterId = registerStudentService.start(registerStudentDTO);
        return Response.created(URI.create(regsiterId)).build();
    }

    @GET
    @Path("/{id}")
    public CourseDTO findById(@PathParam("id") String id) {
        return courseService.findById(id);
    }
}
