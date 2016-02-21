package com.cleanspark.esi.struct;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class Period.
 */
public class Period {
	
	/** The period range. */
	TimeRange periodRange;
	
	/** The value. */
	Double value;
	
	/** The mapper. */
	static ObjectMapper mapper = new ObjectMapper();
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Period.class);
	
	/**
	 * Gets the period range.
	 *
	 * @return the period range
	 */
	public TimeRange getPeriodRange() {
		return periodRange;
	}
	
	/**
	 * Sets the period range.
	 *
	 * @param periodRange the new period range
	 */
	public void setPeriodRange(TimeRange periodRange) {
		this.periodRange = periodRange;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(Double value) {
		this.value = value;
	}
	
	/**
	 * From message.
	 *
	 * @param body the body
	 * @return the period
	 * @throws JsonMappingException the json mapping exception
	 * @throws JsonParseException the json parse exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Period fromMessage(String body) throws JsonMappingException, JsonParseException, IOException {
		return mapper.readValue(body, Period.class);
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
