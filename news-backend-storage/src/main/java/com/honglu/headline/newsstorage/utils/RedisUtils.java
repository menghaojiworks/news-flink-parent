package com.honglu.headline.newsstorage.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisUtils {

    static JedisPool jedisPool;

    @Autowired(required = true)
    public void setJedisPool(JedisPool jedisPool) {
    	RedisUtils.jedisPool = jedisPool;
	}

	public static Jedis getResource() {
        return jedisPool.getResource();
    }

    @SuppressWarnings("deprecation")
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResourceObject(jedis);
        }
    }
    
    @SuppressWarnings("deprecation")
    public static void returnBrokenResource(Jedis jedis) {
    	if (jedis != null) { 
            jedisPool.returnBrokenResource(jedis);
        }
    }
    
	/**
	 * 模糊查询key
	 * @param key 	于Redis中的key
	 */
	public static StringBuffer batchQuery(String pre_str) {
		Jedis jedis = null;
		
		StringBuffer sb = new StringBuffer ();
		
		try {
			jedis = getResource();
			
			Set<String> set = jedis.keys(pre_str +"*");  
			
	        Iterator<String> it = set.iterator();  
	        
	        while (it.hasNext()) {  
	        	
	            String keyStr = it.next(); 
	            
	            sb.append(keyStr+",");
	            
	        }  
	        
		} catch (Throwable e) {
			
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			
			e.printStackTrace();
			
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
		return sb;
	}
    
    /**
	 * 删除key
	 * @param key 	于Redis中的key
	 */
	public static void del(String key) {
		Jedis jedis = null;
		try {
			jedis = getResource();

			// 获取redis中的value
			jedis.del(key);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 判断key是否存在
	 * @param key 	于Redis中的key
	 */
	public static Boolean exists(String key) {
		Jedis jedis = null;
		Boolean flag = false;
		try {
			jedis = getResource();
			// 获取redis中的value
			flag = jedis.exists(key);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return flag;
	}
	
	/**
	 * 判断key是否存在
	 * @param key 	于Redis中的key
	 */
	public static Boolean existsKey(String key) {
		Jedis jedis = null;
		Boolean flag = false;
		try {
			jedis = getResource();
			
			if(jedis == null){
				flag = false;
			}else{
				flag = jedis.exists(key);
			}
		} catch (Throwable e) {
			// 异常情况释放redis对象
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return flag;
	}
	
	/**
	 * 判断名称为key的hash中是否存在键为field的域(redis map操作)
	 * @param key 	于Redis中的key
	 * @param field 于Redis Map中的key
	 */
	public static Boolean hexists(String key, String field) {
		Jedis jedis = null;
		Boolean flag = false;
		try {
			jedis = getResource();
			// 获取redis中的value
			flag = jedis.hexists(key, field);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return flag;
	}
	
	/**
	 * 将 value 的值写入 Redis Map中(redis map操作)
	 * HSET 将哈希表 key 中的域 field 的值设为 value 。如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。如果域 field 已经存在于哈希表中，旧值将被覆盖。
	 * 如果 field 是哈希表中的一个新建域，并且值设置成功，返回 1 。如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回 0 。
	 * @param key   于Redis中的key
	 * @param field 于Redis Map中的key
	 * @param value 存储数据
	 * 			
	 */
	public static void hset(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			// 获取redis中的value
			jedis.hset(key, field, value);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 将 value 的值写入 Redis Map中(redis map操作)
	 * HSETNX 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。若域 field 已经存在，该操作无效。如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。
	 * 设置成功，返回 1 。如果给定域已经存在且没有操作被执行，返回 0.
	 * @param key     于Redis中的key
	 * @param field   于Redis Map中的key
	 * @param value   存储数据
	 * @param seconds 失效时间
	 */
	public static void hsetEx(String key, String field, String value, int seconds) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			// 获取redis中的value
			jedis.hset(key, field, value);
			jedis.expire(key, seconds);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 将 value 的值写入 Redis Map中(redis map操作) 永久有效
	 * HSETNX 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。若域 field 已经存在，该操作无效。如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。
	 * 设置成功，返回 1 。如果给定域已经存在且没有操作被执行，返回 0.
	 * @param key     于Redis中的key
	 * @param field   于Redis Map中的key
	 * @param value   存储数据
	 */
	public static void hsetEx(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			// 获取redis中的value
			jedis.hset(key, field, value);
			//移除给定key的生存时间(设置这个key永不过期)
			jedis.persist(key); 
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	 /**
	  * 返回哈希表key中所有域和值(redis map操作)
	  * @param key 于Redis中的key
	  * @return key对应的Map对象
	  */
	public static Map<String, String> hgetAll(String key) {
		Jedis jedis = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			jedis = getResource();
			// 获取redis中的value
			map = jedis.hgetAll(key);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return map;
	}
	
	public static void rpush(String key, String... values) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.rpush(key, values);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	
	/**
	 * 返回名称为key的hash中field对应的value(redis map操作)
	 * @param key 	于Redis中的key
	 * @param field 于Redis Map中的key
	 */
	public static String hget(String key, String field) {
		Jedis jedis = null;
		String value = "";
		try {
			jedis = getResource();
			// 获取redis中的value
			value = jedis.hget(key, field);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * 返回名称为key的hash中field对应的value(redis map操作)
	 * @param key 	于Redis中的key
	 * @param fields 于Redis Map中的key(可以传入数组)
	 */
	public static List<String> hmget(String key, String... fields) {
		Jedis jedis = null;
		List<String> value = null;
		try {
			jedis = getResource();
			// 获取redis中的value
			value = jedis.hmget(key, fields);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * 将数据写入redis List(String 类型的操作)
	 * @param key 	于Redis中的key
	 * @param values 存储数据
	 */
	public static void lpush(String key, String... values) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.lpush(key, values);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 将数据写入redis List(String 类型的操作)
	 * @param key 		于Redis中的key
	 * @param seconds	有效期时间
	 * @param values 	存储数据
	 */
	public static void lpushEx(String key, int seconds, String... values) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.lpush(key, values);
			jedis.expire(key, seconds);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 获取redis中 List(String 类型的操作)
	 * @param key 		于Redis中的key
	 * @param start		开始索引
	 * @param end 		结束索引
	 */
	public static List<String> lrange(String key, long start, long end) {
		Jedis jedis = null;
		List<String> values = null;
		try {
			jedis = getResource();
			values = jedis.lrange(key, start, end);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return values;
	}
	
	public static void ltrim(String key, long start, long end) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.ltrim(key, start, end);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	/**
	 * 获取redis中 List(String 类型的操作)
	 * @param key 		于Redis中的key
	 * @param start		开始索引
	 * @param end 		结束索引
	 */
	public static <T> List<T> lrange(String key, long start, long end,Class<T> clazz) {
		Jedis jedis = null;
		List<String> values = null;
		List<T> resuts = new ArrayList();
		try {
			jedis = getResource();
			values = jedis.lrange(key, start, end);
			if(values != null && !values.isEmpty()){
				for(String value : values){
					JSONObject jsonObject = JSONObject.fromObject(value);
					T object =  (T)JSONObject.toBean(jsonObject,clazz);
					resuts.add(object);
				}
			}
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return resuts;
	}
	
	
	/**
	 * 获取redis中 List指定索引的值(String 类型的操作)
	 * @param key 		于Redis中的key
	 * @param index		索引
	 */
	public static String lindex(String key, long index) {
		Jedis jedis = null;
		String value = null;
		try {
			jedis = getResource();
			value = jedis.lindex(key, index);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * 获取redis中 List的长度(String 类型的操作)
	 * @param key 		于Redis中的key
	 */
	public static long llen(String key) {
		Jedis jedis = null;
		long length = -1;
		try {
			jedis = getResource();
			length = jedis.llen(key);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return length;
	}
	
	/**
	 * 将数据写入redis(String 类型的操作)
	 * @param key 	于Redis中的key
	 * @param value 存储数据
	 */
	public static void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.set(key, value);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 将数据写入redis List(String 类型的操作)
	 * @param key 	于Redis中的key
	 * @param values 存储数据
	 */
	public static <T> void lpush(String key, T... values) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			String[] jsonStr = new String[values.length];
			for(int index = 0; index < values.length; index++){
				JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(values[index]);
				jsonStr[index] = jsonObject.toString();
			}
			jedis.lpush(key, jsonStr);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	public static <T> void set(String key, T value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(value);
			jedis.set(key,jsonObject.toString());
		} catch (Throwable e) {

			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	
	
	
	/**
	 * 将数据写入redis并设置失效日期(String 类型的操作)
	 * @param key 	     于Redis中的key
	 * @param seconds 超时时间（单位：秒）
	 * @param value	     存储数据
	 */
	public static void setEx(String key, int seconds, String value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.setex(key, seconds, value);
		} catch (Throwable e) {
			
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 从redis中读取数据(String 类型的操作)
	 * @param key 	于Redis中的key
	 * @param value 存储数据
	 */
	public static String get(String key) {
		Jedis jedis = null;
		String value = "";
		try {
			jedis = getResource();
			// 获取redis中的value
			value = jedis.get(key);
		} catch (Throwable e) {
			
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * 从redis中读取数据(String 类型的操作)
	 * @param key 	于Redis中的key
	 * @param value 存储数据
	 */
	public static <T> T get(String key,Class<T> clazz) {
		Jedis jedis = null;
		String value = "";
		T object = null;
		try {
			jedis = getResource();
			// 获取redis中的value
			value = jedis.get(key);
			if(value == null){
				return null;
			}
			JSONObject jsonObject = JSONObject.fromObject(value);
			object =  (T)JSONObject.toBean(jsonObject,clazz); 
		} catch (Throwable e) {
			
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return object;
	}
	
	/**
	 * 从redis中读取数据(String 类型的操作)
	 * @param key 	于Redis中的key
	 * @param value 存储数据
	 */
	public static <T> T get(String key,Class<T> clazz,Map<String, Class> classMap) {
		Jedis jedis = null;
		String value = "";
		T object = null;
		try {
			jedis = getResource();
			// 获取redis中的value
			value = jedis.get(key);
			if(value == null){
				return null;
			}
			JSONObject jsonObject = JSONObject.fromObject(value);
			object =  (T)JSONObject.toBean(jsonObject,clazz,classMap); 
		} catch (Throwable e) {
			
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return object;
	}

	/**
	 * 将数据写入redis
	 * @param key 	     于Redis中的key
	 * @param value	     存储数据
	 */
	public static Long setNx(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.setnx(key, value);
		} catch (Throwable e) {

			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
			return null;
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 将数据写入redis并设置失效日期
	 * @param key 	     于Redis中的key
	 * @param value	     存储数据
	 * @param seconds expire in seconds
	 */
	public static Long setNx(String key, int seconds, String value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			/*将 key 的值设为 value，当且仅当 key 不存在。
			 *若给定的 key 已经存在，则 SETNX 不做任何动作。
			 *SETNX 是SET if Not eXists的简写
			 * 1，当 key 的值被设置 
			0，当 key 的值没被设置*/
			 if (jedis.setnx(key, value) == 1) {
                 jedis.expire(key, seconds);
                 return 1l;
             }
		     return 0l;
		} catch (Throwable e) {

			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
			return null;
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	
	public static <T> Long setNx(String key, int seconds, T value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(value);
			 if (jedis.setnx(key, jsonObject.toString()) == 1) {
                 jedis.expire(key, seconds);
                 return 1l;
             }
		     return 0l;
		} catch (Throwable e) {

			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
			return null;
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 将数据更新入redis List(String 类型的操作)
	 * 
	 * @param key
	 * @param index
	 * @param value
	 */
	public static void lset(String key, long index, String value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.lset(key, index, value);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	public static <T> void lset(String key, long index, T value) {
		Jedis jedis = null;
		try {
			jedis = getResource();			
			JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(value);
			String jsonStr = jsonObject.toString();
			jedis.lset(key, index, jsonStr);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}

	public static String getSet(String key, String valueNew) {
		Jedis jedis = null;
		String value = "";
		try {
			jedis = getResource();
			// 获取redis中的value
			value = jedis.getSet(key, valueNew);
		} catch (Throwable e) {
			
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return value;
	}
	
	
	  /*
     * 序列化
     * */
    public static byte[] serizlize(Object object){
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(baos != null){
                    baos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
    /*
     * 反序列化
     * */
    public static Object deserialize(byte[] bytes){
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null; 
        try{
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
    
    public static double incrBy(String key, long incrValue) {
		Jedis jedis = null;
		double value = 0;
		try {
			jedis = getResource();
			// 获取redis中的value
			value = jedis.incrBy(key, incrValue);
		} catch (Throwable e) {
			
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return value;
	}
    
    public static double decrBy(String key, long decrValue) {
		Jedis jedis = null;
		double value = 0;
		try {
			jedis = getResource();
			// 获取redis中的value
			value = jedis.decrBy(key, decrValue);
		} catch (Throwable e) {
			
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return value;
	}

}
