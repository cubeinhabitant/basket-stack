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
package org.basket3;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.basket3.model.BucketResultXmlModel;
import org.basket3.model.CreateBucketConfiguration;
import org.basket3.model.VersioningConfiguration;
import org.basket3.model.WebsiteConfiguration;
import org.basket3.service.BucketService;
import org.basket3.web.S3ObjectRequest;

import com.google.inject.Singleton;
/**
 * 
 * @author Kerby Martino <kerbymart@gmail.com>
 *
 */
@Singleton
public class BucketResource implements BucketService {

	private static final Logger LOG 
		= Logger.getLogger(BucketResource.class.getName());
	
	@Override
	public Response deleteBucket(String bucketName, HttpHeaders headers,
			UriInfo uriInfo, SecurityContext securityContext) {
		return Response.ok("Delete Bucket").build();
	}

	@Override
	public Response deleteBucketCors(String bucketName, String cors,
			HttpHeaders headers, UriInfo uriInfo,
			SecurityContext securityContext) {
		return Response.ok("Delete Bucket Cors").build();
	}

	@Override
	public Response deleteBucketLifeCycle(String bucketName, String lifecycle,
			HttpHeaders headers, UriInfo uriInfo,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteBucketPolicy(String bucketName, String lifecycle,
			HttpHeaders headers, UriInfo uriInfo,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteBucketTagging(String bucketName, String tagging,
			HttpHeaders headers, UriInfo uriInfo,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteBucketWebsite(String bucketName, String tagging,
			HttpHeaders headers, UriInfo uriInfo,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BucketResultXmlModel getBucketAcl(String bucketName, String acl,
			HttpHeaders headers, UriInfo uriInfo,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BucketResultXmlModel getBucketLifecycle(String bucketName,
			String lifecycle, HttpHeaders headers, UriInfo uriInfo,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BucketResultXmlModel getBucketPolicy(String bucketName,
			String policy, HttpHeaders headers, UriInfo uriInfo,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BucketResultXmlModel getBucketLocation(String bucketName,
			String location, HttpHeaders headers, UriInfo uriInfo,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BucketResultXmlModel getBucketLogging(String bucketName,
			String logging, HttpHeaders headers, UriInfo uriInfo,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BucketResultXmlModel getBucketNotification(String bucketName,
			String notification, HttpHeaders headers, UriInfo uriInfo,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BucketResultXmlModel getBucketTagging(String bucketName,
			String tagging, HttpHeaders headers, UriInfo uriInfo,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BucketResultXmlModel getBucketObject(String bucketName,
			String versions, HttpHeaders headers, UriInfo uriInfo,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VersioningConfiguration getBucketVersioning(String bucketName,
			String versioning, HttpHeaders headers, UriInfo uriInfo,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebsiteConfiguration getBucketWebsite(String bucketName,
			String website, HttpHeaders headers, UriInfo uriInfo,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateBucketConfiguration putBucket(String bucketName,
			String updates, HttpHeaders headers, UriInfo uriInfo,
			SecurityContext securityContext) {
		try {
			S3ObjectRequest or;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	private boolean isLog(){
		return true;
	}

	@Override
	public BucketResultXmlModel getBucket(String bucketName,
			HttpHeaders headers, UriInfo uriInfo,
			HttpServletRequest req, HttpServletResponse resp, SecurityContext securityContext) {
		if (LOG.isDebugEnabled()) {
			LOG.log(Level.DEBUG, "Context path: " + req.getContextPath());
			LOG.log(Level.DEBUG, "Path info: " + req.getPathInfo());
			LOG.log(Level.DEBUG, "Path translated: " + req.getPathTranslated());
			LOG.log(Level.DEBUG, "Query string: " + req.getQueryString());
			LOG.log(Level.DEBUG, "Request URI: " + req.getRequestURI());
			LOG.log(Level.DEBUG, "Request URL: " + req.getRequestURL());
			LOG.log(Level.DEBUG, "Servlet path: " + req.getServletPath());
			//LOG.log(Level.DEBUG, "Servlet name: " + this.getServletName());

			for (Enumeration headerNames = req.getHeaderNames(); headerNames
					.hasMoreElements();) {
				String headerName = (String) headerNames.nextElement();
				String headerValue = req.getHeader(headerName);
				LOG.debug("Header- " + headerName + ": " + headerValue);
			}
		}		
		return new BucketResultXmlModel();
	}

	@Override
	public BucketResultXmlModel getBucketCors(String bucketName, String cors,
			HttpHeaders headers, UriInfo uriInfo, HttpServletRequest req,
			HttpServletResponse resp, SecurityContext securityContext) {
		return null;
	}

}
