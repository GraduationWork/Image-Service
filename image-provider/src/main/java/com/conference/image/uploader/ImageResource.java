package com.conference.image.uploader;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.stereotype.Component;

@Path("/image")
@Component
public class ImageResource {

    private static final String FILE_PATH = "d:\\FIFA.png";
    
    @GET
    @Path("/get")
    @Produces("image/png")
    public Response getFile(@Context Request request) {
        Calendar calendar = Calendar.getInstance();
        File file = new File(FILE_PATH);
        calendar.setTimeInMillis(file.lastModified());
        Date lastModified = calendar.getTime();
        ResponseBuilder responseBuilder = request
                .evaluatePreconditions(lastModified);
        if (responseBuilder == null) {
            responseBuilder = Response.ok(file).lastModified(lastModified);
        }
        return responseBuilder.build();
    }

}
