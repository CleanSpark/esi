package com.cleanspark.esi.struct;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class DataValue.
 */
public class DataValue {

	/** The datetime. */
	long datetime;
	
	/** The name. */
	String name;
	
	/** The value. */
	Object value;
	
	/** The quality. */
	String quality;
	
	/** The units. */
	String units;
	
	
	/** The mapper. */
	static ObjectMapper mapper = new ObjectMapper();
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Profile.class);
	
	static {
		mapper.configure(Feature.INDENT_OUTPUT, true);
	}
	
	/**
	 * Instantiates a new data value.
	 */
	public DataValue() {}
	
	/**
	 * Instantiates a new data value.
	 *
	 * @param datetime the datetime
	 * @param name the name
	 * @param value the value
	 * @param quality the quality
	 * @param units the units
	 */
	public DataValue(long datetime, String name, Object value, String quality, String units) {
		this.datetime = datetime;
		this.name = name;
		this.value = value;
		this.quality = quality;
		this.units = units;
	}
	
	/**
	 * Gets the datetime.
	 *
	 * @return the datetime
	 */
	public long getDatetime() {
		return datetime;
	}
	
	/**
	 * Sets the datetime.
	 *
	 * @param datetime the new datetime
	 */
	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	
	/**
	 * Gets the quality.
	 *
	 * @return the quality
	 */
	public String getQuality() {
		return quality;
	}
	
	/**
	 * Sets the quality.
	 *
	 * @param quality the new quality
	 */
	public void setQuality(String quality) {
		this.quality = quality;
	}
	
	/**
	 * Gets the units.
	 *
	 * @return the units
	 */
	public String getUnits() {
		return units;
	}
	
	/**
	 * Sets the units.
	 *
	 * @param units the new units
	 */
	public void setUnits(String units) {
		this.units = units;
	}
	
	/**
	 * From message.
	 *
	 * @param body the body
	 * @return the data value
	 * @throws JsonMappingException the json mapping exception
	 * @throws JsonParseException the json parse exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static DataValue fromMessage(String body) throws JsonMappingException, JsonParseException, IOException {
		return mapper.readValue(body, DataValue.class);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonGenerationException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
}
