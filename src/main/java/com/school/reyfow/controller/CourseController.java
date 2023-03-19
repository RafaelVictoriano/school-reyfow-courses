package com.school.reyfow.controller;

import com.school.reyfow.dto.CourseDTO;
import com.school.reyfow.dto.CourseRequestDTO;
import com.school.reyfow.dto.RegisterStudentDTO;
import com.school.reyfow.service.CreateCourseService;
import com.school.reyfow.service.FindCoursesService;
import com.school.reyfow.service.RegisterStudentService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;


@Slf4j
@Path("course")
public class CourseController {

    @Inject
    FindCoursesService findCoursesService;

    @Inject
    CreateCourseService createCourseService;

    @Inject
    RegisterStudentService registerStudentService;

    @POST
    @RolesAllowed({"ROLES_CORDENADOR"})
    public Response create(@Valid CourseRequestDTO courseDTO) {
        var courseUuid = createCourseService.start(courseDTO);
        log.info("Course of {} created success", courseDTO.getName());
        return Response.created(URI.create(courseUuid)).build();
    }

    @POST
    @Path("/register/student")
    @RolesAllowed({"ROLES_CORDENADOR", "ROLES_ESTUDANTE"})
    public Response registerStudent(@Valid RegisterStudentDTO registerStudentDTO) {
        var regsiterId = registerStudentService.start(registerStudentDTO);
        return Response.created(URI.create(regsiterId)).build();
    }

    @GET
    @Path("/{id}")
    public CourseDTO findById(@PathParam("id") String id) {
        return findCoursesService.byId(id);
    }

    @GET
    @Path("/all")
    public Response findAll() {
        final var response = new AtomicReference<Response>();
        findCoursesService.all()
                .ifPresentOrElse(body -> response.set(Response.ok(body).build()),
                        () -> response.set(Response.status(404).build())
                );
        return response.get();
    }

}
