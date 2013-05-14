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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.basket3.bo.Acp;
import org.basket3.bo.Bucket;
import org.basket3.bo.CanonicalUser;
import org.basket3.bo.S3Object;
import org.basket3.common.S3Exception;
import org.basket3.dao.BucketDao;
import org.basket3.dao.S3ObjectDao;
import org.basket3.filesystem.bo.GAES3Object;
import org.basket3.filesystem.dao.GAEBucketDao;
import org.basket3.filesystem.dao.GAES3ObjectDao;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;

import com.google.appengine.tools.development.testing.LocalBlobstoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.pagecrumb.mungo.collection.DBObject;

@RunWith(MockitoJUnitRunner.class)
public class S3ObjectDaoTest {

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
    
    @Test(expected=RuntimeException.class)
	public void testGetObjectNotExist() throws S3Exception{ 
		S3ObjectDao dao = new GAES3ObjectDao();
		dao.loadS3Object("test", "test");
		assertNotNull(dao);
    }
	
    @Test
    public void testStoreS3Object() throws S3Exception{ 
    	S3ObjectDao dao = new GAES3ObjectDao();
    	S3Object s3Object = null;
		try {
			s3Object = new GAES3Object("test", "test", new URL("file://test/test.txt"));
			dao.storeS3Object(s3Object);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}  
		S3Object result = dao.loadS3Object("test", "test");
		assertNotNull(result);
		toJSONStringPrint("Result S3Object=", result);
    }
    
	private void toJSONStringPrint(String message, Object obj) {
		logger.info(message + ":" + new Gson().toJson(obj));
	}	
}
