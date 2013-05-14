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
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

import org.basket3.model.BucketResultXmlModel;
import org.basket3.model.CreateBucketConfiguration;
import org.basket3.model.VersioningConfiguration;
import org.basket3.model.WebsiteConfiguration;
/**
 * 
 * @author Kerby Martino
 *
 */
public interface BucketService {
	
	// DELETE /bucketname
	@DELETE
	@Path("/{bucketName}")
	@Produces(MediaType.APPLICATION_XML)	
	public Response deleteBucket(@PathParam("bucketName") String bucketName,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);
	
	// DELETE /?cors
	@DELETE
	@Path("/{bucketName}/")
	@Produces(MediaType.APPLICATION_XML)	
	public Response deleteBucketCors(@PathParam("bucketName") String bucketName,
			@QueryParam("cors") String cors,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);	
	
	// DELETE /?lifecycle
	@DELETE
	@Path("/{bucketName}/")
	@Produces(MediaType.APPLICATION_XML)	
	public Response deleteBucketLifeCycle(@PathParam("bucketName") String bucketName,
			@QueryParam("lifecycle") String lifecycle,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);	
	

	// DELETE /?policy
	@DELETE
	@Path("/{bucketName}/")
	@Produces(MediaType.APPLICATION_XML)	
	public Response deleteBucketPolicy(@PathParam("bucketName") String bucketName,
			@QueryParam("policy") String lifecycle,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);	
	
	
	// DELETE /?tagging
	@DELETE
	@Path("/{bucketName}/")
	@Produces(MediaType.APPLICATION_XML)	
	public Response deleteBucketTagging(@PathParam("bucketName") String bucketName,
			@QueryParam("tagging") String tagging,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);		
	
	
	// DELETE /?website
	@DELETE
	@Path("/{bucketName}/")
	@Produces(MediaType.APPLICATION_XML)	
	public Response deleteBucketWebsite(@PathParam("bucketName") String bucketName,
			@QueryParam("website") String tagging,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);	
	
	// GET /
	@GET
	@Path("/{bucketName}")
	@Produces(MediaType.APPLICATION_XML)	
	public BucketResultXmlModel getBucket(@PathParam("bucketName") String bucketName,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context HttpServletRequest req, @Context HttpServletResponse resp, @Context SecurityContext securityContext);
	
	// GET /
	@GET
	@Path("/{bucketName}")
	@Produces(MediaType.APPLICATION_XML)	
	public BucketResultXmlModel getBucketAcl(@PathParam("bucketName") String bucketName,
			@QueryParam("acl") String acl,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);
	

	// GET /
	@GET
	@Path("/{bucketName}")
	@Produces(MediaType.APPLICATION_XML)	
	public BucketResultXmlModel getBucketCors(@PathParam("bucketName") String bucketName,
			@QueryParam("cors") String cors,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, 
            @Context HttpServletRequest req, @Context HttpServletResponse resp, @Context SecurityContext securityContext);

	// GET /
	@GET
	@Path("/{bucketName}")
	@Produces(MediaType.APPLICATION_XML)	
	public BucketResultXmlModel getBucketLifecycle(@PathParam("bucketName") String bucketName,
			@QueryParam("lifecycle") String lifecycle,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);
	
	// GET /
	@GET
	@Path("/{bucketName}")
	@Produces(MediaType.APPLICATION_XML)	
	public BucketResultXmlModel getBucketPolicy(@PathParam("bucketName") String bucketName,
			@QueryParam("policy") String policy,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);	

	// GET /
	@GET
	@Path("/{bucketName}")
	@Produces(MediaType.APPLICATION_XML)	
	public BucketResultXmlModel getBucketLocation(@PathParam("bucketName") String bucketName,
			@QueryParam("location") String location,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);

	// GET /
	@GET
	@Path("/{bucketName}")
	@Produces(MediaType.APPLICATION_XML)	
	public BucketResultXmlModel getBucketLogging(@PathParam("bucketName") String bucketName,
			@QueryParam("logging") String logging,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);

	// GET /
	@GET
	@Path("/{bucketName}")
	@Produces(MediaType.APPLICATION_XML)	
	public BucketResultXmlModel getBucketNotification(@PathParam("bucketName") String bucketName,
			@QueryParam("notification") String notification,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);

	// GET /
	@GET
	@Path("/{bucketName}")
	@Produces(MediaType.APPLICATION_XML)	
	public BucketResultXmlModel getBucketTagging(@PathParam("bucketName") String bucketName,
			@QueryParam("tagging") String tagging,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);

	// GET /
	@GET
	@Path("/{bucketName}")
	@Produces(MediaType.APPLICATION_XML)	
	public BucketResultXmlModel getBucketObject(@PathParam("bucketName") String bucketName,
			@QueryParam("versions") String versions,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);

	// GET /
	@GET
	@Path("/{bucketName}")
	@Produces(MediaType.APPLICATION_XML)	
	public VersioningConfiguration getBucketVersioning(@PathParam("bucketName") String bucketName,
			@QueryParam("versioning") String versioning,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);
	
	// GET /
	@GET
	@Path("/{bucketName}")
	@Produces(MediaType.APPLICATION_XML)	
	public WebsiteConfiguration getBucketWebsite(@PathParam("bucketName") String bucketName,
			@QueryParam("webiste") String website,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);

	// http://docs.aws.amazon.com/AmazonS3/latest/API/RESTBucketGETwebsite.html
	@PUT
	@Path("/{bucketName}")
	@Produces(MediaType.APPLICATION_XML)	
	public CreateBucketConfiguration putBucket(@PathParam("bucketName") String bucketName,
			@QueryParam("uploads") String updates,
            @Context HttpHeaders headers, @Context UriInfo uriInfo, @Context SecurityContext securityContext);
	
	
}
