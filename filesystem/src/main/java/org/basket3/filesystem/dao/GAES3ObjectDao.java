package org.basket3.filesystem.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeSet;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.basket3.bo.Acp;
import org.basket3.bo.Bucket;
import org.basket3.bo.CanonicalUser;
import org.basket3.bo.S3Object;
import org.basket3.common.S3Exception;
import org.basket3.dao.S3ObjectDao;
import org.basket3.filesystem.bo.GsonSerializer;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
//import org.springframework.dao.DataAccessException;
//import org.springframework.dao.DataAccessResourceFailureException;
//import org.springframework.dao.DataRetrievalFailureException;

import com.google.appengine.repackaged.com.google.common.base.Preconditions;
import com.google.inject.Singleton;
import com.javaexchange.RandomGUID;
import com.pagecrumb.mungo.Mungo;
import com.pagecrumb.mungo.collection.DBCollection;
import com.pagecrumb.mungo.collection.DBObject;
import com.pagecrumb.mungo.collection.WriteResult;
import com.pagecrumb.mungo.entity.BasicDBObject;
import com.pagecrumb.mungo.serializer.ObjectSerializer;

/**
 * An implementation of <code>S3ObjectDao</code> that uses the GAE Datastore to
 * index and manage the S3Object's meta data.
 * 
 * @author Jesse Peterson
 */
@Singleton
public class GAES3ObjectDao extends GAEFileBase implements S3ObjectDao {
	private Log logger;

	public static final int MAXIMUM_MAX_KEYS = 1000;

	private static SimpleDateFormat iso8601 = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	private static TimeZone utc = TimeZone.getTimeZone("UTC");
	
	private ObjectSerializer _serializer; 
	private DBCollection _objects;
	
	static {
		iso8601.setTimeZone(utc);
	}

	public GAES3ObjectDao() {
		super();
		logger = LogFactory.getLog(this.getClass());
		logger.debug("FileS3ObjectDao created");
		_objects = new Mungo().getDB("S3").getCollection("S3Object");  
		_serializer = new GsonSerializer();
	}

	public S3Object loadS3Object(String bucket, String key) {
		S3Object theObject = null;
		try {
			BasicDBObject query = new BasicDBObject("bucket", bucket)
				.append("key", key); 
			DBObject obj = _objects.findOne(query);
			if (obj != null){
				theObject = obj.as(S3Object.class);
			} else {
				throw new RuntimeException("Could not find S3Object with key=" + key + " in bucket="
						+ bucket);				
			}
		} catch (Exception e) {
			throw new RuntimeException("Error in finding S3Object: "
					+ e.getMessage() );
		}
		return theObject;		
//		Map<String, String> keys;
//
//		// load key index
//		try {
//			keys = retrieveKeyIndex(bucket, true);
//		} catch (IOException e) {
//			throw new DataAccessResourceFailureException(
//					"Unable to load the key index for bucket: " + bucket, e);
//		}
//
//		return intLoadS3Object(keys, bucket, key);
	}



	public void storeS3Object(S3Object s3Object) throws S3Exception {
		Preconditions.checkNotNull(s3Object, "S3Object cannot be null");
		try {
			String serialized = _serializer.serialize(s3Object);
			logger.info("Serialized S3Object="+serialized);
			_objects.insert(new BasicDBObject(serialized).append("_id", new ObjectId())); 
		} catch (Exception e) {
			throw new S3Exception(
					"Unable to store S3Object: ", e);
		}

	}

	public void removeS3Object(S3Object s3Object) throws S3Exception {

	}


	
	private void toJSONStringPrint(DBObject obj) {
		JSONObject json = new JSONObject();
		json.putAll(obj.toMap());
		logger.info(json.toJSONString());
	}

	public String listKeys(String bucket, String prefix, String marker,
			String delimiter, int maxKeys) throws S3Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
