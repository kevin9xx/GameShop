package ch.bzz.bookshelf.service;

import ch.bzz.bookshelf.data.DataHandler;
import ch.bzz.bookshelf.model.Game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;


@Path("game")
public class GameService {

    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)

    public Response createGame(
            @FormParam("title") String title,
            @FormParam("Hersteller") String author,
            @FormParam("publisherUUID") String publisherUUID,
            @FormParam("price") BigDecimal price,
            @FormParam("isbn") String isbn
    ) {
        int httpStatus = 200;
        Response response = Response
                .status(httpStatus)
                .entity("Aufruf erfolgreich")
                .build();
        return response;
    }
    
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listGames(
    ) {
        Map<String, Game> gameMap = DataHandler.getGameMap();
        Response response = Response
                .status(200)
                .entity(gameMap)
                .build();
        return response;

    }

    
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readGame(
            @QueryParam("uuid") String gameUUID
    ) {
        Game game = null;
        int httpStatus;

        try {
            UUID gameKey = UUID.fromString(gameUUID);
            game = DataHandler.readGame(gameUUID);
            if (game.getTitle() != null) {
                httpStatus = 200;
            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity(game)
                .build();
        return response;
    }
}
