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

package org.basket3.service;

import java.io.IOException;

/**
 * Inidicates that a bucket is not empty. Usually thrown when trying to delete a
 * bucket.
 * 
 * @author Jesse Peterson
 */
public class BucketNotEmptyException extends IOException {
	private static final long serialVersionUID = 1L;

	/**
	 * Basic constructor.
	 */
	public BucketNotEmptyException() {
		super();
	}

	/**
	 * Constructor with message.
	 * 
	 * @param s
	 *            message
	 */
	public BucketNotEmptyException(String s) {
		super(s);
	}

	/**
	 * Constructor with message and cause.
	 * 
	 * @param s
	 *            message
	 * @param c
	 *            cause
	 */
	public BucketNotEmptyException(String s, Throwable c) {
		super(s);
		initCause(c);
	}
}
