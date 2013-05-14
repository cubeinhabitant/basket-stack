/**
 * 	
 * Copyright 2013 Pagecrumb
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *  
 */
package org.basket3.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
/**
 * 
 * @author Kerby Martino
 *
 */
public interface ObjectService {
	// DELETE /ObjectName
	@DELETE
	@Path("/{objectName}")
	@Produces(MediaType.APPLICATION_XML)	
	public Response deleteObject(@PathParam("objectName") String objectName,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);
	
	// DELETE /ObjectName
	@DELETE
	@Path("/{objectName}")
	@Produces(MediaType.APPLICATION_XML)	
	public Response deleteMultipleObject(@PathParam("objectName") String objectName,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);	

	// GET /ObjectName
	@GET
	@Path("/{objectName}")
	@Produces(MediaType.APPLICATION_XML)	
	public Response getObject(@PathParam("objectName") String objectName,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);
	
	// HEAD /ObjectName
	@HEAD
	@Path("/{objectName}")
	@Produces(MediaType.APPLICATION_XML)	
	public Response getObjectMetadata(@PathParam("objectName") String objectName,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);	

	// POST /ObjectName
	// Content-Type: multipart/form-data; boundary=9431149156168
	/**
	 * This operation initiates a multipart upload and returns an upload ID. 
	 * This upload ID is used to associate all the parts in the specific multipart upload. 
	 * You specify this upload ID in each of your subsequent upload part requests (see Upload Part). 
	 * You also include this upload ID in the final request to either complete or abort the multipart upload request.
	 * 
	 * @param destBucketName
	 * @param uploads
	 * @param request
	 * @param headers
	 * @param uriInfo
	 * @param securityContext
	 * @return
	 */
	@POST
	@Path("/{destBucketName}")
	@Produces(MediaType.APPLICATION_XML)	
	public Response addObjectToBucket(@PathParam("destBucketName") String destBucketName,
			@QueryParam("uploads") String uploads,
			@Context HttpServletRequest request, @Context HttpHeaders headers, 
			@Context UriInfo uriInfo, @Context SecurityContext securityContext);	
	
}
