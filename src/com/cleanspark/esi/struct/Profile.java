package com.cleanspark.esi.struct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
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
 * The Class Profile.
 */
public class Profile {
	
	/** The resource name. */
	String resourceName;
	
	/** The periods. */
	List<Period> periods = new ArrayList<>();
	
	/** The mapper. */
	static ObjectMapper mapper = new ObjectMapper();
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Profile.class);
	
	static {
		mapper.configure(Feature.INDENT_OUTPUT, true);
	}
	
	/**
	 * Gets the resource name.
	 *
	 * @return the resource name
	 */
	public String getResourceName() {
		return resourceName;
	}
	
	/**
	 * Sets the resource name.
	 *
	 * @param resourceName the new resource name
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	/**
	 * Gets the periods.
	 *
	 * @return the periods
	 */
	public List<Period> getPeriods() {
		return periods;
	}
	
	/**
	 * Sets the periods.
	 *
	 * @param periods the new periods
	 */
	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}
	
	/**
	 * Gets the period.
	 *
	 * @param dateTime the date time
	 * @return the period
	 */
	public Period getPeriod(Date dateTime) {
		for (Iterator<Period> i = getPeriods().iterator(); i.hasNext();) {
			Period period = (Period) i.next();
			if (period.getPeriodRange().contains(dateTime)) return period;
		}
		return null;
	}
	
	/**
	 * Update value.
	 *
	 * @param periodRange the period range
	 * @param newValue the new value
	 */
	public void updateValue(TimeRange periodRange, Double newValue) {
		for (Iterator<Period> i = getPeriods().iterator(); i.hasNext();) {
			Period period = (Period) i.next();
			if (period.getPeriodRange().equals(periodRange)) {
				period.setValue(newValue);
			}
		}
	}
	
	/**
	 * Gets the value.
	 *
	 * @param periodRange the period range
	 * @return the value
	 */
	public Double getValue(TimeRange periodRange) {
		Period period = getPeriod(new Date(periodRange.getStartTimeEpoch()));
		if (period == null) return null;
		else return period.getValue();
	}

	/**
	 * From message.
	 *
	 * @param body the body
	 * @return the profile
	 * @throws JsonMappingException the json mapping exception
	 * @throws JsonParseException the json parse exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Profile fromMessage(String body) throws JsonMappingException, JsonParseException, IOException {
		return mapper.readValue(body, Profile.class);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		try {
			Collections.sort(periods, new PeriodRangeComparator());
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
	 * The Class PeriodRangeComparator.
	 */
	class PeriodRangeComparator implements Comparator<Period> {
	    
    	/* (non-Javadoc)
    	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
    	 */
    	@Override
	    public int compare(Period a, Period b) {
	        return new Long(a.getPeriodRange().getStartTimeEpoch()).compareTo(b.getPeriodRange().getStartTimeEpoch());
	    }
	}
}
