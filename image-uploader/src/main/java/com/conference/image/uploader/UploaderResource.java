package com.conference.image.uploader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.sun.jersey.api.view.Viewable;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/")
public class UploaderResource {

    private final String UPLOADED_FILE_PATH = "d:\\uploaded";
    
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
    	File file = new File("/uploadedfiles/image.png");
    	FileOutputStream out = new FileOutputStream(file);
    	IOUtils.copy(uploadedInputStream, out);
        String uploadedFileLocation = "d://uploaded/"
                + fileDetail.getFileName();
        String output = "File uploaded to : " + uploadedFileLocation;
        return Response.status(200).entity(output).build();
    }

    @POST
    @Path("/api/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(MultipartFormDataInput input) {

        String fileName = "";

        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("uploadedFile");

        for (InputPart inputPart : inputParts) {

            try {

                MultivaluedMap<String, String> header = inputPart.getHeaders();
                fileName = getFileName(header);

                // convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class,
                        null);

                byte[] bytes = IOUtils.toByteArray(inputStream);

                // constructs upload file path
                fileName = UPLOADED_FILE_PATH + fileName;

                writeFile(bytes, fileName);

                System.out.println("Done");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return Response
                .status(200)
                .entity("uploadFile is called, Uploaded file name : "
                        + fileName).build();
    }

    @POST
    @Path("/check")
    public Response checkStatus() {
        return Response.status(200).entity("Status").build();
    }

    @GET
    public Viewable getUploadPage() {
        return new Viewable("index.html");
    }

    
  //save to somewhere
    private void writeFile(byte[] content, String filename) throws IOException {

        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fop = new FileOutputStream(file);

        fop.write(content);
        fop.flush();
        fop.close();

    }
    
    private String getFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
        
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {

                String[] name = filename.split("=");
                
                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }
    
}
