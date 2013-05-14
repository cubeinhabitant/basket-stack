package org.basket3.bo;

import javax.servlet.http.HttpServletRequest;

import org.basket3.web.S3ObjectRequest;
/**
 * S3 authentication provider.
 * 
 * @author Jesse Peterson
 */
public interface Authenticator {
	/**
	 * Authenticates the request.
	 * 
	 * @param req
	 *            The original HTTP request.
	 * @param s3Request
	 *            The S3 specific information for authenticating the request.
	 * @return The authenticated <code>CanonicalUser</code> making the
	 *         request.
	 * @throws RequestTimeTooSkewedException
	 *             Thrown if the request timestamp is outside of the allotted
	 *             timeframe.
	 */
	public CanonicalUser authenticate(HttpServletRequest req,
			S3ObjectRequest s3Request) throws AuthenticatorException;
}
