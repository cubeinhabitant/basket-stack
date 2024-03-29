/*
 * Copyright 2007 Jesse Peterson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.basket3.util.etag;

/**
 * An "ETag" is <quote>"The ETag response-header field value, an entity tag,
 * provides for an "opaque" cache validator."</quote>
 * 
 * @author Jesse Peterson
 * 
 */
public interface ETag {
	/**
	 * Calculate the ETag for an object.
	 * 
	 * @param o
	 *            The object to calculate the ETag for.
	 * @return The ETag value for the object. May be <code>null</code> if an
	 *         ETag can not be calculated.
	 */
	public String calculate(Object o);
}
