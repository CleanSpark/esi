package com.cleanspark.esi.struct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class RecordedData.
 */
public class RecordedData {
	
	/** The data values. */
	List<DataValue> dataValues = new ArrayList<>();
	
	/** The mapper. */
	static ObjectMapper mapper = new ObjectMapper();
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Profile.class);
	
	static {
		mapper.configure(Feature.INDENT_OUTPUT, true);
	}
	
	/**
	 * Gets the data values.
	 *
	 * @return the data values
	 */
	public List<DataValue> getDataValues() {
		return dataValues;
	}

	/**
	 * Sets the data values.
	 *
	 * @param dataValues the new data values
	 */
	public void setDataValues(List<DataValue> dataValues) {
		this.dataValues = dataValues;
	}

	/**
	 * From message.
	 *
	 * @param body the body
	 * @return the recorded data
	 * @throws JsonMappingException the json mapping exception
	 * @throws JsonParseException the json parse exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static RecordedData fromMessage(String body) throws JsonMappingException, JsonParseException, IOException {
		return mapper.readValue(body, RecordedData.class);
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
