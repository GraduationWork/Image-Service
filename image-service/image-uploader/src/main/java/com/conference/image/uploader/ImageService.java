package com.conference.image.uploader;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.stereotype.Component;

@Path("/image")
@Component
public class ImageService {

    private static final String FILE_PATH = "d:\\FIFA.png";

    @GET
    @Path("/get")
    @Produces("image/png")
    public Response getFile() {

        File file = new File(FILE_PATH);

        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
            "attachment; filename=image_from_server.png");
        return response.build();

    }
    
}
