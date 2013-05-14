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
package org.basket3.filesystem.bo;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.pagecrumb.mungo.common.SerializationException;
import com.pagecrumb.mungo.serializer.ObjectSerializer;
// TODO: Move this class to the Mungo library project
/**
 * 
 * @author Kerby Martino <kerbymart@gmail.com>
 *
 */
public class GsonSerializer implements ObjectSerializer {

	public Object deserialize(String s) throws SerializationException {
		JSONParser parser = new JSONParser();
		try {
			return parser.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String serialize(Object o) throws SerializationException {
		return new Gson().toJson(o);
	}

}
