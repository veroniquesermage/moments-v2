package com.moments.modules.program.presentation;

import java.util.UUID;

import com.moments.modules.program.application.queryHandler.GetActivityQueryHandler;
import com.moments.modules.program.application.useCase.CreateActivityUseCase;
import com.moments.modules.program.presentation.request.CreateActivityRequest;
import com.moments.modules.program.presentation.response.ActivityResponse;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/activities")
public class ActivityResource {

    private final CreateActivityUseCase createActivityUseCase;
    private final GetActivityQueryHandler getActivityQueryHandler;

    public ActivityResource(CreateActivityUseCase createActivityUseCase, GetActivityQueryHandler getActivityQueryHandler) {
        this.createActivityUseCase = createActivityUseCase;
        this.getActivityQueryHandler = getActivityQueryHandler;
    }

    @GET
    @Path("/{id}")
    public Response getActivity(@PathParam("id") UUID id) {
        ActivityResponse activityResponse = getActivityQueryHandler.getActivityById(id);
        return Response.ok(activityResponse).build();
    }


    @POST
    public Response createActivity(CreateActivityRequest request) {
        return Response.ok().build();
    }

}
