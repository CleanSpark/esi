package com.cleanspark.esi.struct;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class SampleRate.
 */
public class SampleRate {
	
	/** The second. */
	public static SampleRate SECOND = new SampleRate("SECOND", 	1, "S");
	
	/** The minute. */
	public static SampleRate MINUTE = new SampleRate("MINUTE", 	1, "M");
	
	/** The hour. */
	public static SampleRate HOUR 	= new SampleRate("HOUR", 	1, "H");
	
	/** The day. */
	public static SampleRate DAY 	= new SampleRate("DAY", 	1, "D");
	
	/** The week. */
	public static SampleRate WEEK 	= new SampleRate("WEEK", 	1, "W");
	
	/** The month. */
	public static SampleRate MONTH 	= new SampleRate("MONTH", 	1, "m");
	
	/** The year. */
	public static SampleRate YEAR 	= new SampleRate("YEAR", 	1, "Y");
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SampleRate.class);
	
	/** The mapper. */
	static ObjectMapper mapper = new ObjectMapper();
	
	/** The quantity. */
	private int quantity;
	
	/** The abbr. */
	private String abbr;
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new sample rate.
	 */
	public SampleRate() {
		//For JSON
	}
	
	/**
	 * Instantiates a new sample rate.
	 *
	 * @param name the name
	 * @param quantity the quantity
	 * @param abbr the abbr
	 */
	public SampleRate(String name, int quantity, String abbr) {
		this.name = name;
		this.quantity = quantity;
		this.abbr = abbr;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the abbr.
	 *
	 * @return the abbr
	 */
	public String getAbbr() {
		return abbr;
	}

	/**
	 * Sets the abbr.
	 *
	 * @param abbr the new abbr
	 */
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	
	/**
	 * From message.
	 *
	 * @param body the body
	 * @return the sample rate
	 * @throws JsonMappingException the json mapping exception
	 * @throws JsonParseException the json parse exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static SampleRate fromMessage(String body) throws JsonMappingException,JsonParseException, IOException {
		return mapper.readValue(body, SampleRate.class);
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
	 * Gets the rate in millis.
	 *
	 * @return the rate in millis
	 */
	@JsonIgnore
	public long getRateInMillis() {
		long millis = 0;
		switch(name) {
			case "SECOND":
				millis = quantity * 1000;
				break;
			case "MINUTE":
				millis = quantity * 60 * 1000;
				break;
			case "HOUR":
				millis = quantity * 60 * 60 * 1000;
				break;
			case "DAY":
				millis = quantity * 24 * 60 * 60 * 1000;
				break;
			case "WEEK":
				millis = quantity * 7 * 24 * 60 * 60 * 1000;
				break;
			case "MONTH":
				millis = quantity * 30 * 24 * 60 * 60 * 1000;
				break;
			case "YEAR":
				millis = quantity * 365 * 24 * 60 * 60 * 1000;
				break;
			default:
				throw new IllegalArgumentException("Name " + name + " is not supported.");
		}
		
		return millis;
	}
	

}

