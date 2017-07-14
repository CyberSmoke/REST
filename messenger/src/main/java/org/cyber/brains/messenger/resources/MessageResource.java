package org.cyber.brains.messenger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.cyber.brains.messenger.beans.MessageFilterBean;
import org.cyber.brains.messenger.model.Message;
import org.cyber.brains.messenger.service.MessageService;

@Path("/messages")
public class MessageResource 
{
	MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages( @BeanParam MessageFilterBean filterBean )
	{
		if( filterBean.getYear() > 0 )
		{
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		
		if( filterBean.getStart() >= 0 && filterBean.getSize() > 0 )
		{
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage( @PathParam("messageId") long messageId )
	{
		return messageService.getMessage(messageId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message addMessage( Message message )
	{
		return messageService.addMessage(message);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/${messageId}")
	public Message updateMessage( @PathParam("messageId") long id, Message message )
	{
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message deleteMessage( @PathParam("messageId") long id )
	{
		return messageService.deleteMessage(id);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource()
	{
		return new CommentResource();
	}
}
