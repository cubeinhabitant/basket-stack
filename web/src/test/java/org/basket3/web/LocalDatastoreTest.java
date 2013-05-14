package org.basket3.web;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.basket3.BucketResource;
import org.basket3.bo.AuthenticatorException;
import org.basket3.bo.CanonicalUser;
import org.basket3.bo.HackAuthenticator;
import org.basket3.bo.S3Authenticator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;


public class LocalDatastoreTest {

	private static final Logger LOG 
		= Logger.getLogger(LocalDatastoreTest.class.getName());
	
    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig()
                .setDefaultHighRepJobPolicyUnappliedJobPercentage(0)); 		

    @Before
    public void setUp() {
        helper.setUp();
        LOG.debug("S3ObjectTest");
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }	
	
	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void test_serviceEndpoint() {
		S3ObjectRequest o = new S3ObjectRequest();

		assertEquals("Unexpected serviceEndpoint", null, o.getServiceEndpoint());
		o.setServiceEndpoint("http://localhost");
		assertEquals("Unexpected serviceEndpoint", "http://localhost", o
				.getServiceEndpoint());
	}	

	/**
	 * Test getting/setting the bucket.
	 */
	@Test
	public void test_bucket() {
		S3ObjectRequest o = new S3ObjectRequest();

		assertEquals("Unexpected bucket", null, o.getBucket());
		o.setBucket("testBucket");
		assertEquals("Unexpected bucket", "testBucket", o.getBucket());
	}
	
	/**
	 * Test getting/setting the key.
	 */
	@Test
	public void test_key() {
		S3ObjectRequest o = new S3ObjectRequest();

		assertEquals("Unexpected key", null, o.getKey());
		o.setKey("testKey");
		assertEquals("Unexpected key", "testKey", o.getKey());
	}

	/**
	 * Test a basic <code>create</code>.
	 */
	@Test
	public void xtest_create() {
		S3ObjectRequest o;

		HttpServletRequest mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockHttpServletRequest.getPathInfo()).thenReturn("/myBucket/myKey.txt");
		Mockito.when(mockHttpServletRequest.getHeader("Host")).thenReturn("localhost");
		Mockito.when(mockHttpServletRequest.getRequestURL()).thenReturn(new StringBuffer("http://localhost/context/myBucket/myKey.txt"));
		Mockito.when(mockHttpServletRequest.getUserPrincipal()).thenReturn(new CanonicalUser("unitTest"));
		Mockito.when(mockHttpServletRequest.getHeader("x-hack-user")).thenReturn(null);
		
		try {
			HackAuthenticator authenticator = new HackAuthenticator();
			authenticator.setAuthenticator(new S3Authenticator());
			o = S3ObjectRequest.create(mockHttpServletRequest, "localhost", authenticator);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		} catch (AuthenticatorException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		}

		assertEquals("Unexpected serviceEndpoint", "http://localhost/context",
				o.getServiceEndpoint());
		assertEquals("Unexpected bucket", "myBucket", o.getBucket());
		assertEquals("Unexpected key", "myKey.txt", o.getKey());
		assertEquals("Unexpected requestor", new CanonicalUser("unitTest"), o
				.getRequestor());
	}
	
	/**
	 * Test a basic <code>create</code> with an anonymous request.
	 */
	@Test
	public void xtest_createAnonymousRequest() {
		S3ObjectRequest o;
		
		HttpServletRequest mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockHttpServletRequest.getPathInfo()).thenReturn("/myBucket/myKey.txt");
		Mockito.when(mockHttpServletRequest.getHeader("Host")).thenReturn("localhost");
		Mockito.when(mockHttpServletRequest.getRequestURL()).thenReturn(new StringBuffer("http://localhost/context/myBucket/myKey.txt"));
		Mockito.when(mockHttpServletRequest.getUserPrincipal()).thenReturn(null); 
		Mockito.when(mockHttpServletRequest.getHeader("x-hack-user")).thenReturn(null);	

		try {
			HackAuthenticator authenticator = new HackAuthenticator();
			authenticator.setAuthenticator(new S3Authenticator());

			o = S3ObjectRequest.create(
					(HttpServletRequest) mockHttpServletRequest,
					"localhost", authenticator);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		} catch (AuthenticatorException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		}

		assertEquals("Unexpected serviceEndpoint", "http://localhost/context",
				o.getServiceEndpoint());
		assertEquals("Unexpected bucket", "myBucket", o.getBucket());
		assertEquals("Unexpected key", "myKey.txt", o.getKey());
		assertEquals("Unexpected requestor", new CanonicalUser(
				CanonicalUser.ID_ANONYMOUS), o.getRequestor());
	}

	/**
	 * Test a basic <code>create</code> but with a space in the key.
	 */
	@Test
	public void xtest_createWithSpace() {
		S3ObjectRequest o;

		HttpServletRequest mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockHttpServletRequest.getPathInfo()).thenReturn("/myBucket/myKey.txt");
		Mockito.when(mockHttpServletRequest.getHeader("Host")).thenReturn("localhost");
		Mockito.when(mockHttpServletRequest.getRequestURL()).thenReturn(new StringBuffer("http://localhost/context/myBucket/my%20Key.txt"));
		Mockito.when(mockHttpServletRequest.getUserPrincipal()).thenReturn(new CanonicalUser("unitTest"));
		Mockito.when(mockHttpServletRequest.getHeader("x-hack-user")).thenReturn(null);
		
		try {
			HackAuthenticator authenticator = new HackAuthenticator();
			authenticator.setAuthenticator(new S3Authenticator());

			o = S3ObjectRequest.create(
					(HttpServletRequest) mockHttpServletRequest,
					"localhost", authenticator);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		} catch (AuthenticatorException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		}

		assertEquals("Unexpected serviceEndpoint", "http://localhost/context",
				o.getServiceEndpoint());
		assertEquals("Unexpected bucket", "myBucket", o.getBucket());
		assertEquals("Unexpected key", "my Key.txt", o.getKey());
	}

	/**
	 * Test a <code>create</code> with no key but with a slash character after
	 * the bucket.
	 */
	//@Test
	public void xtest_createNoKeyBucketEndsWithSlash() {
		S3ObjectRequest o;
		
		HttpServletRequest mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockHttpServletRequest.getPathInfo()).thenReturn("/myBucket/");
		Mockito.when(mockHttpServletRequest.getHeader("Host")).thenReturn("localhost");
		Mockito.when(mockHttpServletRequest.getRequestURL()).thenReturn(new StringBuffer("http://localhost/context/myBucket/"));
		Mockito.when(mockHttpServletRequest.getUserPrincipal()).thenReturn(new CanonicalUser("unitTest"));
		Mockito.when(mockHttpServletRequest.getHeader("x-hack-user")).thenReturn(null);		

		try {
			HackAuthenticator authenticator = new HackAuthenticator();
			authenticator.setAuthenticator(new S3Authenticator());

			o = S3ObjectRequest.create(
					(HttpServletRequest) mockHttpServletRequest,
					"localhost", authenticator);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		} catch (AuthenticatorException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		}

		assertEquals("Unexpected serviceEndpoint", "http://localhost/context",
				o.getServiceEndpoint());
		assertEquals("Unexpected bucket", "myBucket", o.getBucket());
		assertNull("Unexpected key", o.getKey());
	}

	/**
	 * Test a <code>create</code> using virtual hosting of buckets. Ordinary
	 * method.
	 */
	//@Test
	public void xtest_virtualHostingOrdinaryMethod() {
		S3ObjectRequest o;
		
		HttpServletRequest mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockHttpServletRequest.getPathInfo()).thenReturn("/johnsmith/homepage.html");
		Mockito.when(mockHttpServletRequest.getHeader("Host")).thenReturn("localhost");
		Mockito.when(mockHttpServletRequest.getRequestURL()).thenReturn(new StringBuffer("http://s3.amazonaws.com/johnsmith/homepage.html"));
		Mockito.when(mockHttpServletRequest.getUserPrincipal()).thenReturn(new CanonicalUser("unitTest"));
		Mockito.when(mockHttpServletRequest.getHeader("x-hack-user")).thenReturn(null);			

		try {
			HackAuthenticator authenticator = new HackAuthenticator();
			authenticator.setAuthenticator(new S3Authenticator());

			o = S3ObjectRequest.create(
					(HttpServletRequest) mockHttpServletRequest,
					"s3.amazonaws.com", authenticator);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		} catch (AuthenticatorException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		}

		assertEquals("Unexpected serviceEndpoint", "http://s3.amazonaws.com", o
				.getServiceEndpoint());
		assertEquals("Unexpected bucket", "johnsmith", o.getBucket());
		assertEquals("Unexpected key", "homepage.html", o.getKey());
	}

	/**
	 * Test a <code>create</code> using virtual hosting of buckets. HTTP 1.0,
	 * contains no Host header.
	 */
	@Test
	public void xtest_virtualHostingHTTP10() {
		S3ObjectRequest o;

		HttpServletRequest mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockHttpServletRequest.getPathInfo()).thenReturn("/johnsmith/homepage.html");
		Mockito.when(mockHttpServletRequest.getHeader("Host")).thenReturn(null);
		Mockito.when(mockHttpServletRequest.getRequestURL()).thenReturn(new StringBuffer("http://s3.amazonaws.com/johnsmith/homepage.html"));
		Mockito.when(mockHttpServletRequest.getUserPrincipal()).thenReturn(new CanonicalUser("unitTest"));
		Mockito.when(mockHttpServletRequest.getHeader("x-hack-user")).thenReturn(null);	
		
		try {
			HackAuthenticator authenticator = new HackAuthenticator();
			authenticator.setAuthenticator(new S3Authenticator());

			o = S3ObjectRequest.create(
					(HttpServletRequest) mockHttpServletRequest,
					"s3.amazonaws.com", authenticator);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		} catch (AuthenticatorException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		}

		assertEquals("Unexpected serviceEndpoint", "http://s3.amazonaws.com", o
				.getServiceEndpoint());
		assertEquals("Unexpected bucket", "johnsmith", o.getBucket());
		assertEquals("Unexpected key", "homepage.html", o.getKey());
	}

	/**
	 * Test a <code>create</code> using virtual hosting of buckets. Sub-domain
	 * method.
	 */
	@Test
	public void xtest_virtualHostingSubDomain() {
		S3ObjectRequest o;

		HttpServletRequest mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockHttpServletRequest.getPathInfo()).thenReturn("/homepage.html");
		Mockito.when(mockHttpServletRequest.getHeader("Host")).thenReturn("johnsmith.s3.amazonaws.com");
		Mockito.when(mockHttpServletRequest.getRequestURL()).thenReturn(new StringBuffer("http://s3.amazonaws.com/johnsmith/homepage.html"));
		Mockito.when(mockHttpServletRequest.getUserPrincipal()).thenReturn(new CanonicalUser("unitTest"));
		Mockito.when(mockHttpServletRequest.getHeader("x-hack-user")).thenReturn(null);	
		
		try {
			HackAuthenticator authenticator = new HackAuthenticator();
			authenticator.setAuthenticator(new S3Authenticator());

			o = S3ObjectRequest.create(
					(HttpServletRequest) mockHttpServletRequest,
					"s3.amazonaws.com", authenticator); 

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		} catch (AuthenticatorException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		}

		assertEquals("Unexpected serviceEndpoint",
				"http://johnsmith.s3.amazonaws.com", o.getServiceEndpoint());
		assertEquals("Unexpected bucket", "johnsmith", o.getBucket());
		assertEquals("Unexpected key", "homepage.html", o.getKey());
	}

	/**
	 * Test a <code>create</code> using virtual hosting of buckets. Sub-domain
	 * method with upper case Host header.
	 */
	@Test
	public void xtest_virtualHostingSubDomainUpperCase() {
		S3ObjectRequest o;

		HttpServletRequest mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockHttpServletRequest.getPathInfo()).thenReturn("/homepage.html");
		Mockito.when(mockHttpServletRequest.getHeader("Host")).thenReturn("JohnSmith.s3.amazonaws.com");
		Mockito.when(mockHttpServletRequest.getRequestURL()).thenReturn(new StringBuffer("http://johnsmith.s3.amazonaws.com/homepage.html"));
		Mockito.when(mockHttpServletRequest.getUserPrincipal()).thenReturn(new CanonicalUser("unitTest"));
		Mockito.when(mockHttpServletRequest.getHeader("x-hack-user")).thenReturn(null);			

		try {
			HackAuthenticator authenticator = new HackAuthenticator();
			authenticator.setAuthenticator(new S3Authenticator());

			o = S3ObjectRequest.create(
					(HttpServletRequest) mockHttpServletRequest,
					"s3.amazonaws.com", authenticator);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		} catch (AuthenticatorException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		}

		assertEquals("Unexpected serviceEndpoint",
				"http://johnsmith.s3.amazonaws.com", o.getServiceEndpoint());
		assertEquals("Unexpected bucket", "johnsmith", o.getBucket());
		assertEquals("Unexpected key", "homepage.html", o.getKey());
	}

	/**
	 * Test a <code>create</code> using virtual hosting of buckets. Domain is
	 * the bucket.
	 */
	@Test
	public void xtest_virtualHostingDomain() {
		S3ObjectRequest o;
		
		HttpServletRequest mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockHttpServletRequest.getPathInfo()).thenReturn("/homepage.html");
		Mockito.when(mockHttpServletRequest.getHeader("Host")).thenReturn("www.johnsmith.net");
		Mockito.when(mockHttpServletRequest.getRequestURL()).thenReturn(new StringBuffer("http://www.johnsmith.net/homepage.html"));
		Mockito.when(mockHttpServletRequest.getUserPrincipal()).thenReturn(new CanonicalUser("unitTest"));
		Mockito.when(mockHttpServletRequest.getHeader("x-hack-user")).thenReturn(null);		

		try {
			HackAuthenticator authenticator = new HackAuthenticator();
			authenticator.setAuthenticator(new S3Authenticator());

			o = S3ObjectRequest.create(
					(HttpServletRequest) mockHttpServletRequest,
					"s3.amazonaws.com", authenticator);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		} catch (AuthenticatorException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		}

		assertEquals("Unexpected serviceEndpoint", "http://www.johnsmith.net",
				o.getServiceEndpoint());
		assertEquals("Unexpected bucket", "www.johnsmith.net", o.getBucket());
		assertEquals("Unexpected key", "homepage.html", o.getKey());
	}

	/**
	 * Test a <code>create</code> with no key but and no slash character after
	 * the bucket.
	 */
	@Test
	public void xtest_createNoKeyBucketNoSlash() {
		S3ObjectRequest o;
		
		HttpServletRequest mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockHttpServletRequest.getPathInfo()).thenReturn("/myBucket");
		Mockito.when(mockHttpServletRequest.getHeader("Host")).thenReturn("localhost");
		Mockito.when(mockHttpServletRequest.getRequestURL()).thenReturn(new StringBuffer("http://localhost/context/myBucket"));
		Mockito.when(mockHttpServletRequest.getUserPrincipal()).thenReturn(new CanonicalUser("unitTest"));
		Mockito.when(mockHttpServletRequest.getHeader("x-hack-user")).thenReturn(null);				

		try {
			HackAuthenticator authenticator = new HackAuthenticator();
			authenticator.setAuthenticator(new S3Authenticator());

			o = S3ObjectRequest.create(
					(HttpServletRequest) mockHttpServletRequest,
					"localhost", authenticator);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		} catch (AuthenticatorException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		}

		assertEquals("Unexpected serviceEndpoint", "http://localhost/context",
				o.getServiceEndpoint());
		assertEquals("Unexpected bucket", "myBucket", o.getBucket());
		assertNull("Unexpected key", o.getKey());
	}

	/**
	 * Test a <code>create</code> with no bucket.
	 */
	@Test
	public void xtest_createNoBucket() {
		S3ObjectRequest o;
		
		HttpServletRequest mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockHttpServletRequest.getPathInfo()).thenReturn("/");
		Mockito.when(mockHttpServletRequest.getHeader("Host")).thenReturn("localhost");
		Mockito.when(mockHttpServletRequest.getRequestURL()).thenReturn(new StringBuffer("http://localhost/context/"));
		Mockito.when(mockHttpServletRequest.getUserPrincipal()).thenReturn(new CanonicalUser("unitTest"));
		Mockito.when(mockHttpServletRequest.getHeader("x-hack-user")).thenReturn(null);				

		try {
			HackAuthenticator authenticator = new HackAuthenticator();
			authenticator.setAuthenticator(new S3Authenticator());

			o = S3ObjectRequest.create(
					(HttpServletRequest) mockHttpServletRequest,
					"localhost", authenticator);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		} catch (AuthenticatorException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		}

		assertEquals("Unexpected serviceEndpoint", "http://localhost/context",
				o.getServiceEndpoint());
		assertNull("Unexpected bucket", o.getBucket());
		assertNull("Unexpected key", o.getKey());
	}

	/**
	 * Test a <code>create</code> with an invalid request.
	 */
	@Test
	public void xtest_createIllegalRequest() {

		HttpServletRequest mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockHttpServletRequest.getPathInfo()).thenReturn("/foo");
		Mockito.when(mockHttpServletRequest.getHeader("Host")).thenReturn("localhost");
		Mockito.when(mockHttpServletRequest.getRequestURL()).thenReturn(new StringBuffer("http://localhost/context/bar"));		
		
		try {
			HackAuthenticator authenticator = new HackAuthenticator();
			authenticator.setAuthenticator(new S3Authenticator());

			S3ObjectRequest.create((HttpServletRequest) mockHttpServletRequest, "localhost", authenticator);
			fail("Expected exception");
		} catch (IllegalArgumentException e) {
			// expected
		} catch (AuthenticatorException e) {
			e.printStackTrace();
			fail("Unexpected exception");
			return;
		}
	}

	/**
	 * Basically a utility test for creating an ISO 8601 date.
	 */
	@Test
	public void test_isoDate() {
		SimpleDateFormat iso8601 = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		TimeZone utc = TimeZone.getTimeZone("UTC");
		iso8601.setTimeZone(utc);

		GregorianCalendar cal = new GregorianCalendar(2007, 6, 19, 9, 50, 33);

		assertEquals("Unexpected formatted date", "2007-07-19T14:50:33.000Z",
				iso8601.format(cal.getTime()));
	}

	/**
	 * Integration test to execute the algorithm for hostname resolution.
	 */
	@Test
	public void xtest_localHost() {
		String configHost;
		String token = "$resolvedLocalHost$";

		configHost = "foo:" + token + token + ":8080";

		if (configHost.indexOf(token) >= 0) {
			InetAddress localHost;
			String resolvedLocalHost = "localhost";

			try {
				localHost = InetAddress.getLocalHost();
				resolvedLocalHost = localHost.getCanonicalHostName();
			} catch (UnknownHostException e) {
				LOG.fatal("Unable to resolve local host", e);
			}

			configHost = configHost.replace(token, resolvedLocalHost);
		}

		// this is machine dependent
		System.out.println(">>>> configHost: " + configHost);
		assertEquals("Unexpected hostname",
				"foo:PEM32Z2RC1LIC.NCSP.PEROOT.COMPEM32Z2RC1LIC.NCSP.PEROOT.COM:8080", configHost);
	}		

	private void l(Object log){
		System.out.print(String.valueOf(log) + "\n"); 
	}		
	
}
