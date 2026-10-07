package ressources;

import entities.Etudiant;
import entities.Option;
import metiers.EtudiantBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("etudiants")
public class EtudiantResource {

    private static EtudiantBusiness business = new EtudiantBusiness();

    // B2: GET /etudiants
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(business.getAllEtudiants()).build();
    }

    // B6: GET /etudiants/option?codeOption=1 (XML)
    @GET
    @Path("option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getByOption(@QueryParam("codeOption") int codeOption) {
        Option option = OptionResource.business.getOptionByCode(codeOption);
        if (option == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Etudiant> list = business.getEtudiantsByOption(option);
        return Response.ok(new GenericEntity<List<Etudiant>>(list) {}).build();
    }

    // B3: GET /etudiants/I003
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") String id) {
        Etudiant e = business.getEtudiantByIdentifiant(id);
        if (e == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(e).build();
    }

    // B1: POST /etudiants
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Etudiant e) {
        if (e.getOption() == null || !business.addEtudiant(e)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(e).build();
    }

    // B5: PUT /etudiants/I001
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, Etudiant e) {
        if (business.updateEtudiant(id, e)) {
            return Response.ok(e).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // B4: DELETE /etudiants/I003
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        if (business.deleteEtudiant(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}