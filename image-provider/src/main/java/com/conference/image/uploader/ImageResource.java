package com.conference.image.uploader;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.stereotype.Component;

@Path(ImageResource.RESOURCE_PATH)
@Component
public class ImageResource {
	
	
	public static final String RESOURCE_PATH = "/content";
	
    private static final String FILE_PATH = "/uploadedfiles/image.png";
    
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
    
    @GET
    @Path("/{path:(([0-9]+/?)+)}{vresize: (.vresize.[0-9]+.[0-9]+)}.{type}")
    public Response getResource(@Context Request request,
    		@PathParam(value = "path") String path,
    		@PathParam(value = "type") String type,
    		@PathParam(value = "vresize") String vresize) {
    	File file = new File(FILE_PATH);
    	ResponseBuilder responseBuilder = Response.ok(file);
        return responseBuilder.build();
    }
    

}
