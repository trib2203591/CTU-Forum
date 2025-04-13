package ctu.forum.boundary;

import java.util.HashMap;
import java.util.Map;

import ctu.forum.dto.VoteDTO;
import ctu.forum.service.VoteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("votes")
public class VoteResource {
    private final VoteService voteService;

    @Inject
    public VoteResource(VoteService voteService) {
        this.voteService = voteService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVote(VoteDTO voteDTO) {

        Map<String, Object> response = new HashMap<>();
        
        try {

            voteService.createVote(voteDTO);
            response.put("success", true);
            response.put("message", "Created vote successfully");
            return Response
                    .status(Response.Status.CREATED)
                    .entity(response)
                    .build();
        } catch (IllegalArgumentException e) {
            response.put("message", e.getMessage());
            response.put("success", false);
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllVotes() {
        return Response.ok(voteService.getAllVotes()).build();
    }
    
    @GET
    @Path("/post/{post_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVoteByPostId(@PathParam("post_id") String post_id) {
        Map<String, Object> response = new HashMap<>();
        try{
            response.put("success", true);
            response.put("data", voteService.getVoteByPostId(post_id));
            return Response.ok(response)
                        .build();
            // return Response.ok(voteService.getVoteByPostId(post_id)).build();
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }
    }
    @GET
    @Path("/comment/{comment_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVoteByCommentId(@PathParam("comment_id") String comment_id) {
        Map<String, Object> response = new HashMap<>();
        try{
            response.put("success", true);
            response.put("data", voteService.getVoteByCommentId(comment_id));
            return Response.ok(response)
                    .build();
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateVote(@PathParam("id") String id, VoteDTO voteDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("success", true);
            response.put("message", "Updated vote successfully");
            voteService.updateVote(id, voteDTO);
            return Response
                    .status(Response.Status.OK)
                    .entity(response)
                    .build();            
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(response)
                    .build();
        }

    }


    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteVote(@PathParam("id") String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("success", true);
            response.put("message", "Deleted vote successfully");
            voteService.deleteVote(id);
            return Response
                    .status(Response.Status.OK)
                    .entity(response)
                    .build();            
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(response)
                    .build();
        }

    }
    
}
