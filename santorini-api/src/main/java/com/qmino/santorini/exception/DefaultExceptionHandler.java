package com.qmino.santorini.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * The default exception handler.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 2/12/13<br>
 * <i>Creation-Time</i>: 17:30<br>
 * </p>
 *
 * @author Elliot Gossieaux
 */
@Provider
public class DefaultExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        StringBuilder response = new StringBuilder("<response>");
        response.append("<status>ERROR</status>");
        response.append("<message>" + e.getMessage() + "</message>");
        response.append("</response>");
        return Response.serverError().entity(response.toString()).type(MediaType.APPLICATION_XML).build();
    }
}