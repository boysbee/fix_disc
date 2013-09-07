package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

public class ObjectUtils {
	private static final Logger log = Logger.getLogger(ObjectUtils.class);

	/**
	 * Private constructor. This class should not be instantiate by caller.
	 */
	private ObjectUtils() {
	}

	public static String reflectionAllToString(Object obj) {
		return reflection(obj, true, true, true);
	}

	public static String reflectionToString(Object obj) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("\n---------------------------Start Extract---------------------------");
		sb.append(reflection(obj, false, false, false));
		sb
				.append("\n---------------------------End Extract---------------------------");
		return sb.toString();
	}

	public static String reflectionToString(
			Object obj,
				boolean isExtractObject,
				boolean isExtractCollection) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("\n---------------------------Start Extract---------------------------");
		sb.append(reflection(obj, isExtractObject, isExtractCollection, false));
		sb
				.append("\n---------------------------End Extract---------------------------");
		return sb.toString();
	}

	public static String reflectionToString(
			Object obj,
				boolean isExtractObject,
				boolean isExtractCollection,
				boolean isExtractArray) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("\n---------------------------Start Extract---------------------------");
		sb.append(reflection(obj, isExtractObject, isExtractCollection,
				isExtractArray));
		sb
				.append("\n---------------------------End Extract---------------------------");
		return sb.toString();
	}

	/**
	 * Return specified object's property values using reflection. Do not use
	 * this method if the object has any properties with cyclic dependency.
	 * 
	 * @param obj
	 * @return Specified object's property values using reflection
	 */
	private static String reflection(
			Object obj,
				boolean isExtractObject,
				boolean isExtractCollection,
				boolean isExtractArray) {
		StringBuffer sb = new StringBuffer();

		if (obj == null) {
			return null;
		}

		if (!(obj instanceof Collection)) {

			try {
				sb.append("\n");
				sb.append("Object toString: " + obj.toString());
				sb.append("\n");

				Class c = obj.getClass();
				Map fields = getFieldRecursive(c);
				Set entrySet = fields.entrySet();
				for (Iterator i = entrySet.iterator(); i.hasNext();) {
					Map.Entry entry = (Map.Entry) i.next();
					String propertyName = (String) entry.getKey();
					Field field = (Field) entry.getValue();

					// Enable access to private fields.
					field.setAccessible(true);

					String strValue = null;
					Object rawValue = field.get(obj);
					// System.out.println("field.getType()->>"+field.getType());
					// System.out.println(field.getType().getName());
					// System.out.println("-->"+field.getType().getName().startsWith("java",
					// 0));
					// System.out.println(rawValue + "\tisCollection->" +
					// (rawValue
					// instanceof Collection));
					// System.out.println(rawValue + "\tisObject->" + (rawValue
					// instanceof Object));

					if (rawValue != null) {
						strValue = rawValue.toString();
					}

					sb.append(propertyName);
					sb.append(": [");
					sb.append(strValue);
					sb.append("]");
					sb.append("\n");

					if (isExtractObject
							&& !field.getType().getName().equalsIgnoreCase(
									"byte")
							&& !field.getType().getName().equalsIgnoreCase(
									"short")
							&& !field.getType().getName().equalsIgnoreCase(
									"int")
							&& !field.getType().getName().equalsIgnoreCase(
									"long")
							&& !field.getType().getName().equalsIgnoreCase(
									"float")
							&& !field.getType().getName().equalsIgnoreCase(
									"double")
							&& !field.getType().getName().equalsIgnoreCase(
									"boolean")
							&& !field.getType().getName().equalsIgnoreCase(
									"char")
							&& !field.getType().getName().startsWith("java", 0)
							&& !field.getType().getName().startsWith("org", 0)
							&& !(rawValue instanceof Object[])
					// check if field is not primitive and default java type
					// reflect again.
					) {
						sb.append("=======[Start Extract Object]=======\n");
						sb.append(reflection(rawValue, false,
								isExtractCollection, isExtractArray)
								+ "\n");
						sb.append("=======[End Extract Object]=======\n");
					} else if (isExtractCollection
							&& rawValue instanceof Collection) {
						Iterator iter = ((Collection) rawValue).iterator();
						sb
								.append("\n<<<<<<[Start Extract Object in Collection]>>>>>>>\n");
						while (iter.hasNext()) {
							Object element = (Object) iter.next();
							sb.append(reflection(element, isExtractObject,
									false, isExtractArray)
									+ "\n");
						}
						sb
								.append("<<<<<<[End Extract Object in Collection]>>>>>>>\n\n");
					} else if (isExtractArray && rawValue instanceof Object[]) {
						Object[] array = (Object[]) rawValue;
						sb.append("\n<<<<<<[Start Extract Array]>>>>>>>\n");
						for (int j = 0; j < array.length; j++) {
							sb.append("\n\t\t\t<<<<<<[Start Item " + j
									+ "]>>>>>>>\n");
							sb.append(reflection(array[j], isExtractObject,
									isExtractCollection, false)
									+ "\n");
							sb.append("\n\t\t\t<<<<<<[End Item " + j
									+ "]>>>>>>>\n");
						}
						sb.append("<<<<<<[End Extract Array]>>>>>>>\n\n");
					}
				}

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return null;
			}
		} else {
			Collection col = (Collection) obj;
			int i = 1;
			for (Iterator iterator = col.iterator(); iterator.hasNext(); i++) {
				Object objInCol = iterator.next();
				// if(isExtractCollection) str+="\t";
				sb.append("\n\t-----Start Object [" + i + "] in "
						+ objInCol.toString() + " -----");
				sb.append(reflection(objInCol, isExtractObject,
						isExtractCollection, isExtractArray));
				sb.append("\n\t-----End [" + i + "]  -----");
			}
		}
		return sb.toString();
	}

	/**
	 * Recursively retrieve all fields of specified class, as well as its
	 * superclass.
	 * 
	 * @param c
	 * @return Map of [Property Name (String) --> {@link Field} object]
	 */
	private static Map getFieldRecursive(Class c) {
		Map result = new TreeMap();
		if (!Object.class.equals(c.getSuperclass())) {
			result.putAll(getFieldRecursive(c.getSuperclass()));
		}

		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			// Store result in a Map, using property name as key to the
			// property's Field object.
			result.put(fields[i].getName(), fields[i]);
		}

		return result;
	}

	public static Object deepCopy(Object original) {
		Object clone = null;
		try {
			// Increased buffer size to speed up writing
			ByteArrayOutputStream bos = new ByteArrayOutputStream(5120);
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(original);
			out.flush();
			out.close();

			ObjectInputStream in = new ObjectInputStream(
					new ByteArrayInputStream(bos.toByteArray()));
			clone = in.readObject();

			in.close();
			bos.close();

			return clone;
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}
}
