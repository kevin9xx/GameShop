package ch.bzz.bookshelf.service;

import ch.bzz.bookshelf.data.DataHandler;
import ch.bzz.bookshelf.model.Publisher;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;


@Path("publisher")
public class PublisherService {


    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPublishers(
    ) {
        Map<String, Publisher> publisherMap = DataHandler.getPublisherMap();
        Response response = Response
                .status(200)
                .entity(publisherMap)
                .build();
        return response;
    }


    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPublisher(
            @QueryParam("uuid") String publisherUUID
    ) {
        Publisher publisher = null;
        int httpStatus;

        try {
            UUID.fromString(publisherUUID);
            publisher = DataHandler.readPublisher(publisherUUID);
            if (publisher.getPublisher() != null) {
                httpStatus = 200;
            } else {
                httpStatus = 200;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity(publisher)
                .build();
        return response;
    }
}
