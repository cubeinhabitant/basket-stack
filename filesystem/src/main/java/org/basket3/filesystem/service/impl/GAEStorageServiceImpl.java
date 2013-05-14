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
package org.basket3.filesystem.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.basket3.bo.Bucket;
import org.basket3.bo.CanonicalUser;
import org.basket3.bo.S3Object;
import org.basket3.common.S3Exception;
import org.basket3.dao.BucketDao;
import org.basket3.dao.S3ObjectDao;
import org.basket3.service.StorageService;

import com.google.inject.Inject;
import com.google.inject.Singleton;
/**
 * 
 * @author Kerby Martino <kerbymart@gmail.com>
 *
 */
@Singleton
public class GAEStorageServiceImpl implements StorageService {
	private Log logger;

	private BucketDao bucketDao;
	private S3ObjectDao s3ObjectDao;

	@Inject
	public GAEStorageServiceImpl(BucketDao bucketDao, S3ObjectDao s3ObjectDao) {
		logger = LogFactory.getLog(this.getClass());
		this.bucketDao = bucketDao;
		this.s3ObjectDao = s3ObjectDao;
	}

	public S3Object createS3Object(Bucket bucket, String key,
			CanonicalUser owner) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public S3Object load(String bucket, String key) throws S3Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void store(S3Object s3Object) throws S3Exception {
		// TODO Auto-generated method stub
		
	}

	public void remove(S3Object s3Object) throws S3Exception {
		// TODO Auto-generated method stub
		
	}

	public void setS3ObjectDao(S3ObjectDao s3ObjectDao) {
		// TODO Auto-generated method stub
		
	}

	public Bucket createBucket(String name, CanonicalUser owner)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Bucket loadBucket(String name) throws S3Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void storeBucket(Bucket bucket) throws S3Exception {
		// TODO Auto-generated method stub
		
	}

	public void deleteBucket(Bucket bucket) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public List<Bucket> findBuckets(String username) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public String listKeys(Bucket bucket, String prefix, String marker,
			String delimiter, int maxKeys) throws S3Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void setBucketDao(BucketDao bucketDao) {
		// TODO Auto-generated method stub
		
	}



}
