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
package org.basket3.filesystem;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.basket3.bo.Acp;
import org.basket3.bo.Bucket;
import org.basket3.bo.CanonicalUser;
import org.basket3.dao.BucketDao;
import org.basket3.filesystem.dao.GAEBucketDao;
import org.basket3.common.S3Exception;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.appengine.tools.development.testing.LocalBlobstoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.pagecrumb.mungo.collection.DBObject;

@RunWith(MockitoJUnitRunner.class)
public class BucketDaoTest {

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig()
                .setDefaultHighRepJobPolicyUnappliedJobPercentage(0)); 		
	
    Log logger = LogFactory.getLog(this.getClass());
	
	@Before
    public void setUp() {
		helper.setUp();
	}

    @After
    public void tearDown() {
    	helper.tearDown();
    }
    
    @Test(expected=S3Exception.class)
	public void testGetBucketNotExist() throws S3Exception {
		BucketDao dao = new GAEBucketDao();
		assertNotNull(dao);
		dao.loadBucket("test");
    }

	@Test
	public void testStoreGetBucket() throws S3Exception{ 
		
		BucketDao dao = new GAEBucketDao();
		
		assertNotNull(dao);

		CanonicalUser owner = new CanonicalUser("pete");
		Acp policy = new Acp();
		policy.setOwner(owner);
		
		Bucket bucket = new Bucket();
		bucket.setName("test");
		bucket.setCreated(new Date());
		bucket.setAcp(policy);
		
		dao.storeBucket(bucket);
		
		Bucket result = dao.loadBucket("test");
		assertNotNull(result);
		
		toJSONStringPrint("", result);
		
		
	}	
	
	@Test
	public void testStoreGetPartialBucket() throws S3Exception{
		BucketDao dao = new GAEBucketDao();
		
		assertNotNull(dao);

		Bucket bucket = new Bucket();
		bucket.setName("test");
		bucket.setCreated(new Date());
		
		Acp policy = new Acp();
		bucket.setAcp(policy);
		
		dao.storeBucket(bucket);
		
		Bucket result = dao.loadBucket("test");
		assertNotNull(result);
		
		toJSONStringPrint("Partial bucket", result);
	}	
	
//	@Test(expected=S3Exception.class)
//	public void testRemoveBucket() throws S3Exception{ 
//		BucketDao dao = new FileBucketDao();
//		Bucket bucket = new Bucket();
//		bucket.setName("test");
//		dao.removeBucket(bucket);
//	}
	
	private void toJSONStringPrint(String message, Object obj) {
		logger.info(message + ":" + new Gson().toJson(obj));
	}	
}
