package com.sumerge.program.rest;

import com.sumerge.program.user.User;
import com.sumerge.program.user.UserManager;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Path("users")
public class UserResources {

    private static final Logger LOGGER = Logger.getLogger(UserResources.class.getName());
    private UserManager userManager = new UserManager();

    @POST
    public Response postUser(User user) {
        try {
            return Response.ok().
                    entity(userManager.createUser(user)).
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }

    @GET
    public Response getAllUsers() {
        try {
            return Response.ok().
                    entity(userManager.readUsers()).
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }

    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") int id) {
        try {
            return Response.ok().
                    entity(userManager.readUser(id)).
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }

    @PUT
    public Response putUser(User user) {
        try {
            return Response.ok().
                    entity(userManager.updateUser(user)).
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id) {
        try {
            return Response.ok().
                    entity(userManager.deleteUser(id)).
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }
}
