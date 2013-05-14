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
package org.basket3.filesystem.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.basket3.bo.Bucket;
import org.basket3.common.S3Exception;
import org.basket3.dao.BucketDao;
import org.basket3.filesystem.bo.GsonSerializer;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;

import com.google.inject.Singleton;
import com.pagecrumb.mungo.Mungo;
import com.pagecrumb.mungo.collection.DBCollection;
import com.pagecrumb.mungo.collection.DBObject;
import com.pagecrumb.mungo.entity.BasicDBObject;
import com.pagecrumb.mungo.serializer.ObjectSerializer;
/**
 * An implementation of <code>BucketDao</code> that uses the the GAE Datastore to
 * index and manage the bucket's meta data.
 * 
 * @author Kerby Martino <kerbymart@gmail.com>
 */
@Singleton
public class GAEBucketDao extends GAEFileBase implements BucketDao {
	
	private Log logger;
	private ObjectSerializer serializer; 
	private DBCollection _buckets;
	
	public GAEBucketDao() {
		super();
		logger = LogFactory.getLog(this.getClass());
		logger.debug("FileBucketDao created");
		serializer = new GsonSerializer();
		_buckets = new Mungo().getDB("S3").getCollection("Bucket");  
	}

	/**
	 * This method will load the bucket from a Java serialized file.
	 * 
	 * @param bucket
	 *            The name of the bucket to load.
	 * @throws DataAccessResourceFailureException
	 *             General failure serializing the bucket.
	 */
	public Bucket loadBucket(String bucket) throws S3Exception {
		logger.info("Loading bucket name="+bucket);
		Bucket theBucket = null;
		try {
			BasicDBObject query = new BasicDBObject("name", bucket);
			DBObject obj = _buckets.findOne(query);
			if (obj != null){
				return obj.as(Bucket.class);
			}
		} catch (Exception e) {
			throw new S3Exception("Could not find Bucket: "
					+ bucket);
		}
		return theBucket;
	}

	/**
	 * This method will remove a Java serialized file representing the bucket.
	 * 
	 * @param bucket
	 *            The name of the bucket to remove.
	 * @throws DataAccessResourceFailureException
	 *             General failure removing the bucket.
	 */
	public void removeBucket(Bucket bucket) throws S3Exception {
		try {
			BasicDBObject query = new BasicDBObject("name", bucket.getName());
			boolean isOk = (Boolean) _buckets.remove(query).getLastError().get("ok");
			if(!isOk){
				throw new S3Exception("Cannot remove Bucket: " + bucket.getName());
			};
		} catch (Exception e) {
			e.printStackTrace();
			throw new S3Exception("Could not find Bucket: "
					+ bucket);
		}
	}

	/**
	 * This method will store the bucket as a Java serialized file.
	 * 
	 * @param bucket
	 *            The bucket to store.
	 * @throws DataAccessResourceFailureException
	 *             General failure serializing the bucket.
	 */
	public void storeBucket(Bucket bucket) throws S3Exception {
		try {
			ObjectId id = new ObjectId();
			_buckets.insert(new BasicDBObject(serializer.serialize(bucket)).append("_id", id)); 
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void toJSONStringPrint(DBObject obj) {
		JSONObject json = new JSONObject();
		json.putAll(obj.toMap());
		logger.info(json.toJSONString());
	}
}
