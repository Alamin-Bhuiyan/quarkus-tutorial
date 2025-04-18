package srbl.resource;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/mobile")
public class MobileResource {
    List<Mobile> mobiles = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON) //response type
    public Response getMobiles() {
        return Response.ok(mobiles).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON) //request type
    public Response CreateMobile(Mobile mobile) {
        mobiles.add(mobile);
        return Response.ok(mobile).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdateMobile(@PathParam("id") int id, Mobile modifiedMobile) {
        mobiles = mobiles.stream().map(mobile -> {
            if (mobile.getId() == id) {
                return modifiedMobile;
            } else {
                return mobile;
            }
        }).collect(Collectors.toList());

        return Response.ok(mobiles).build();
    }

    @DELETE
    @Path("/{id}")
    public Response DeleteMobile(@PathParam("id") int id) {
        Optional<Mobile> mobileToDelete =
                mobiles.stream()
                        .filter(mobile -> mobile.getId() == id)
                        .findFirst();
        if (mobileToDelete.isPresent()) {
            mobiles.remove(mobileToDelete.get());
            return Response.ok(mobiles).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON) //response type
    public Response getMobile(@PathParam("id") int id) {
        Optional<Mobile> mobileToDelete =
                mobiles.stream()
                        .filter(mobile -> mobile.getId() == id)
                        .findFirst();
        if (mobileToDelete.isPresent()) {
            return Response.ok(mobileToDelete.get()).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
