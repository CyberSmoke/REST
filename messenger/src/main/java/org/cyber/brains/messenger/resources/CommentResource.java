package org.cyber.brains.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.cyber.brains.messenger.model.Comment;
import org.cyber.brains.messenger.service.CommentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource 
{
	private CommentService commentService = new CommentService();
	
	@GET
	public List<Comment> getAllComments( @PathParam("messageId") long messageId )
	{
		return commentService.getAllComments(messageId);
	}

	@GET
	@Path("/{commentId}")
	public String test2( @PathParam("commentId") long id, @PathParam("messageId") long messageId)
	{
		return "Method to return comment Id: " + id + " MessageId: "+messageId;
	}
}
