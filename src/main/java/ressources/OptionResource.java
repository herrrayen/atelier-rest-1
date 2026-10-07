package ressources;

import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("options")
public class OptionResource {

    // not private: EtudiantResource reuses it
    static OptionBusiness business = new OptionBusiness();

    // A2 + A3: GET /options and GET /options?domaine=Mathématiques
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOptions(@QueryParam("domaine") String domaine) {
        List<Option> list;
        if (domaine == null) {
            list = business.getListeOptions();
        } else {
            list = business.getOptionsByDomaine(domaine);
        }
        return Response.ok(list).build();
    }

    // A6: GET /options/1
    @GET
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOption(@PathParam("code") int code) {
        Option option = business.getOptionByCode(code);
        if (option == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(option).build();
    }

    // A1: POST /options
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOption(Option option) {
        business.addOption(option);
        return Response.ok(option).build();
    }

    // A5: PUT /options/1
    @PUT
    @Path("{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOption(@PathParam("code") int code, Option option) {
        if (business.updateOption(code, option)) {
            return Response.ok(option).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // A4: DELETE /options/2
    @DELETE
    @Path("{code}")
    public Response deleteOption(@PathParam("code") int code) {
        if (business.deleteOption(code)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}