package com.cleanspark.esi.struct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class TimeRange.
 */
public class TimeRange {
	
	/** The start time. */
	String startTime;
	
	/** The end time. */
	String endTime;
	
	/** The start time epoch. */
	long startTimeEpoch;
	
	/** The end time epoch. */
	long endTimeEpoch;
	
	/** The format string. */
	String formatString = "MM/dd/yyyy HH:mm:00";
	
	/** The formatter. */
	SimpleDateFormat formatter;

	/** The mapper. */
	static ObjectMapper mapper = new ObjectMapper();

	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TimeRange.class);
	
	/**
	 * Instantiates a new time range.
	 */
	public TimeRange() {}
	
	/**
	 * Instantiates a new time range.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 */
	public TimeRange(String startTime, String endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	 * Instantiates a new time range.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 */
	public TimeRange(long startTime, long endTime) {
		this.startTimeEpoch = startTime;
		this.endTimeEpoch = endTime;
	}
	
	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public String getStartTime() {
		if (startTime == null || startTime.length() < 1) {
			if (startTimeEpoch != 0)  startTime = getSimpleDateFormat().format(startTimeEpoch);
		}
		return startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public String getEndTime() {
		if (endTime == null || endTime.length() < 1) {
			if (endTimeEpoch != 0)  endTime = getSimpleDateFormat().format(endTimeEpoch);
		}
		return endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime the new end time
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the start time epoch.
	 *
	 * @return the start time epoch
	 */
	public long getStartTimeEpoch() {
		if (startTimeEpoch == 0) {
			try {
				startTimeEpoch = getSimpleDateFormat().parse(startTime).getTime();
			} catch (Exception e) {
				LOG.error("No start time or invalid start time provided for TimeRange: " + e.getMessage());
			}
		}
		return startTimeEpoch;
	}

	/**
	 * Sets the start time epoch.
	 *
	 * @param startTimeEpoch the new start time epoch
	 */
	public void setStartTimeEpoch(long startTimeEpoch) {
		this.startTimeEpoch = startTimeEpoch;
	}

	/**
	 * Gets the end time epoch.
	 *
	 * @return the end time epoch
	 */
	public long getEndTimeEpoch() {
		if (endTimeEpoch == 0) {
			try {
				endTimeEpoch = getSimpleDateFormat().parse(endTime).getTime();
			} catch (Exception e) {
				LOG.error("No end time or invalid end time provided for TimeRange: " + e.getMessage());
			}
		}
		return endTimeEpoch;
	}

	/**
	 * Sets the end time epoch.
	 *
	 * @param endTimeEpoch the new end time epoch
	 */
	public void setEndTimeEpoch(long endTimeEpoch) {
		this.endTimeEpoch = endTimeEpoch;
	}

	/**
	 * Gets the format string.
	 *
	 * @return the format string
	 */
	public String getFormatString() {
		return formatString;
	}

	/**
	 * Sets the format string.
	 *
	 * @param formatString the new format string
	 */
	public void setFormatString(String formatString) {
		if (startTimeEpoch > 0 && endTimeEpoch > 0) {
			//Left purposely empty
		} else if (startTime != null && endTime != null) {
			try {
				startTimeEpoch = getSimpleDateFormat().parse(startTime).getTime();
				endTimeEpoch = getSimpleDateFormat().parse(endTime).getTime();
			} catch (ParseException e) {
				LOG.error("Parse exception: " + e.getMessage());
				e.printStackTrace();
			}
			
		} 
		
		formatter = null;
		this.formatString = formatString;
		startTime = null;
		endTime = null;
	}
	
	/**
	 * Gets the simple date format.
	 *
	 * @return the simple date format
	 */
	@JsonIgnore
	private SimpleDateFormat getSimpleDateFormat() {
		if (formatter == null) {
			formatter = new SimpleDateFormat(formatString);
		}
		
		return formatter;
	}
	
	/**
	 * Builds the time ranges.
	 *
	 * @param period the period
	 * @return the list
	 */
	@JsonIgnore
	public List<TimeRange> buildTimeRanges(SampleRate period) {
		List<TimeRange> ranges = new ArrayList<>();
		
		LOG.debug("Start time is: " + getStartTime() + " (epoch " + getStartTimeEpoch() + ")");
		Calendar startTime = GregorianCalendar.getInstance();
		startTime.setTimeInMillis(getStartTimeEpoch());
		
		Calendar endPeriod = GregorianCalendar.getInstance();
		endPeriod.setTimeInMillis(getStartTimeEpoch());
		
		LOG.debug("End time is: " + getEndTime() + " (epoch " + getEndTimeEpoch() + ")");
		Calendar endTime = GregorianCalendar.getInstance();
		endTime.setTimeInMillis(getEndTimeEpoch());
		
		while (endTime.after(endPeriod)) {
			switch(period.getAbbr()) {
				case "S":
					endPeriod.add(Calendar.SECOND, period.getQuantity());
					break;
				case "D":
					endPeriod.add(Calendar.DAY_OF_MONTH, period.getQuantity());
					break;
				case "H":
					endPeriod.add(Calendar.HOUR_OF_DAY, period.getQuantity());
					break;
				case "M":
					endPeriod.add(Calendar.MINUTE, period.getQuantity());
					break;
				default:
					LOG.error("Unsupported time range");
					break;
			}
			TimeRange range = new TimeRange();
			range.setFormatString(formatString);
			range.setStartTimeEpoch(startTime.getTimeInMillis());
			range.setEndTimeEpoch(endPeriod.getTimeInMillis());
			ranges.add(range);
			
			startTime.setTimeInMillis(endPeriod.getTimeInMillis());
		}
		return ranges;
	}
	
	/**
	 * Contains.
	 *
	 * @param date the date
	 * @return true, if successful
	 */
	public boolean contains(Date date) {
		return ((getStartTimeEpoch() <= date.getTime())
				&& (getEndTimeEpoch() > date.getTime()));
				
	}
	
	/**
	 * Gets the today.
	 *
	 * @return the today
	 */
	@JsonIgnore
	public static TimeRange getToday() {
		TimeRange range = new TimeRange();

		Calendar begin = GregorianCalendar.getInstance();
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		Calendar end = GregorianCalendar.getInstance();
		end.add(Calendar.DAY_OF_WEEK, 1);
		end.set(Calendar.HOUR_OF_DAY, 0);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		range.setEndTimeEpoch(end.getTimeInMillis());
		
		return range;
	}
	
	/**
	 * Gets the tomorrow.
	 *
	 * @return the tomorrow
	 */
	@JsonIgnore
	public static TimeRange getTomorrow() {
		TimeRange range = new TimeRange();
		
		Calendar begin = GregorianCalendar.getInstance();
		begin.add(Calendar.DAY_OF_WEEK, 1);
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		Calendar end = GregorianCalendar.getInstance();
		end.add(Calendar.DAY_OF_WEEK, 2);
		end.set(Calendar.HOUR_OF_DAY, 0);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		range.setEndTimeEpoch(end.getTimeInMillis());
		
		return range;
	}
	
	/**
	 * Gets the yesterday.
	 *
	 * @return the yesterday
	 */
	@JsonIgnore
	public static TimeRange getYesterday() {
		TimeRange range = new TimeRange();
		Calendar begin = GregorianCalendar.getInstance();
		begin.add(Calendar.DAY_OF_MONTH, -1);
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		
		Calendar end = GregorianCalendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, 0);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		range.setEndTimeEpoch(end.getTimeInMillis());
		
		return range;
	}
	
	
	/**
	 * Gets the this week.
	 *
	 * @return the this week
	 */
	@JsonIgnore
	public static TimeRange getThisWeek() {
		
		TimeRange range = new TimeRange();
		Calendar now = GregorianCalendar.getInstance();
		range.setEndTimeEpoch(now.getTimeInMillis());
		Calendar begin = GregorianCalendar.getInstance();
		begin.setTimeInMillis(now.getTimeInMillis());
		begin.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		return range;
		
	}
	
	/**
	 * Gets the last hour.
	 *
	 * @return the last hour
	 */
	@JsonIgnore
	public static TimeRange getLastHour() {
		TimeRange range = new TimeRange();
		Calendar begin = GregorianCalendar.getInstance();
		begin.add(Calendar.HOUR_OF_DAY, -1);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		
		Calendar end = GregorianCalendar.getInstance();
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		range.setEndTimeEpoch(end.getTimeInMillis());
		
		return range;
	}
	
	/**
	 * Gets the this hour.
	 *
	 * @return the this hour
	 */
	@JsonIgnore
	public static TimeRange getThisHour() {
		TimeRange range = new TimeRange();

		Calendar begin = GregorianCalendar.getInstance();
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		Calendar end = GregorianCalendar.getInstance();
		end.add(Calendar.HOUR_OF_DAY, 1);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		range.setEndTimeEpoch(end.getTimeInMillis());
		
		return range;
	}
	
	/**
	 * Gets the next hour.
	 *
	 * @return the next hour
	 */
	@JsonIgnore
	public static TimeRange getNextHour() {
		TimeRange range = new TimeRange();

		Calendar begin = GregorianCalendar.getInstance();
		begin.add(Calendar.HOUR_OF_DAY, 1);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		Calendar end = GregorianCalendar.getInstance();
		end.add(Calendar.HOUR_OF_DAY, 2);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		range.setEndTimeEpoch(end.getTimeInMillis());
		
		return range;
	}
	
	/**
	 * Gets the rolling24 hours.
	 *
	 * @return the rolling24 hours
	 */
	@JsonIgnore
	public static TimeRange getRolling24Hours() {
		TimeRange range = new TimeRange();
		Calendar now = GregorianCalendar.getInstance();
		range.setEndTimeEpoch(now.getTimeInMillis());
		Calendar begin = GregorianCalendar.getInstance();
		begin.setTimeInMillis(now.getTimeInMillis());
		begin.add(Calendar.DAY_OF_MONTH, -1);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		return range;
	}
	
	/**
	 * Gets the next24 hours.
	 *
	 * @return the next24 hours
	 */
	@JsonIgnore
	public static TimeRange getNext24Hours() {
		TimeRange range = new TimeRange();
		Calendar begin = GregorianCalendar.getInstance();
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		Calendar end = GregorianCalendar.getInstance();
		end.add(Calendar.DAY_OF_MONTH, 1);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		range.setEndTimeEpoch(end.getTimeInMillis());
		
		return range;
	}
	
	/**
	 * Gets the next12 hours.
	 *
	 * @return the next12 hours
	 */
	@JsonIgnore
	public static TimeRange getNext12Hours() {
		TimeRange range = new TimeRange();
		Calendar begin = GregorianCalendar.getInstance();
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		Calendar end = GregorianCalendar.getInstance();
		end.add(Calendar.HOUR_OF_DAY, 12);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		range.setEndTimeEpoch(end.getTimeInMillis());
		
		return range;
	}
	
	/**
	 * Gets the next8 hours.
	 *
	 * @return the next8 hours
	 */
	@JsonIgnore
	public static TimeRange getNext8Hours() {
		TimeRange range = new TimeRange();
		Calendar begin = GregorianCalendar.getInstance();
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		Calendar end = GregorianCalendar.getInstance();
		end.add(Calendar.HOUR_OF_DAY, 8);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		range.setEndTimeEpoch(end.getTimeInMillis());
		
		return range;
	}
	
	/**
	 * Gets the next6 hours.
	 *
	 * @return the next6 hours
	 */
	@JsonIgnore
	public static TimeRange getNext6Hours() {
		TimeRange range = new TimeRange();
		Calendar begin = GregorianCalendar.getInstance();
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		Calendar end = GregorianCalendar.getInstance();
		end.add(Calendar.HOUR_OF_DAY, 6);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		range.setEndTimeEpoch(end.getTimeInMillis());
		
		return range;
	}
	
	/**
	 * Gets the next4 hours.
	 *
	 * @return the next4 hours
	 */
	@JsonIgnore
	public static TimeRange getNext4Hours() {
		TimeRange range = new TimeRange();
		Calendar begin = GregorianCalendar.getInstance();
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		Calendar end = GregorianCalendar.getInstance();
		end.add(Calendar.HOUR_OF_DAY, 4);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		range.setEndTimeEpoch(end.getTimeInMillis());
		
		return range;
	}
	
	/**
	 * Gets the rolling7 days.
	 *
	 * @return the rolling7 days
	 */
	@JsonIgnore
	public static TimeRange getRolling7Days() {
		TimeRange range = new TimeRange();
		Calendar now = GregorianCalendar.getInstance();
		range.setEndTimeEpoch(now.getTimeInMillis());
		Calendar begin = GregorianCalendar.getInstance();
		begin.setTimeInMillis(now.getTimeInMillis());
		begin.add(Calendar.DAY_OF_MONTH, -7);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		return range;
	}
	
	/**
	 * Gets the this month.
	 *
	 * @return the this month
	 */
	@JsonIgnore
	public static TimeRange getThisMonth() {
		
		TimeRange range = new TimeRange();
		Calendar now = GregorianCalendar.getInstance();
		range.setEndTimeEpoch(now.getTimeInMillis());
		Calendar begin = GregorianCalendar.getInstance();
		begin.setTimeInMillis(now.getTimeInMillis());
		begin.set(Calendar.DAY_OF_MONTH, 1);
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		return range;
		
	}
	
	/**
	 * Gets the rolling30 days.
	 *
	 * @return the rolling30 days
	 */
	@JsonIgnore
	public static TimeRange getRolling30Days() {
		TimeRange range = new TimeRange();
		Calendar now = GregorianCalendar.getInstance();
		range.setEndTimeEpoch(now.getTimeInMillis());
		Calendar begin = GregorianCalendar.getInstance();
		begin.setTimeInMillis(now.getTimeInMillis());
		begin.add(Calendar.DAY_OF_MONTH, -30);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		return range;
	}
	
	/**
	 * Gets the this year.
	 *
	 * @return the this year
	 */
	@JsonIgnore
	public static TimeRange getThisYear() {
		TimeRange range = new TimeRange();
		Calendar now = GregorianCalendar.getInstance();
		range.setEndTimeEpoch(now.getTimeInMillis());
		Calendar begin = GregorianCalendar.getInstance();
		begin.setTimeInMillis(now.getTimeInMillis());
		begin.set(Calendar.MONTH, 0);
		begin.set(Calendar.DAY_OF_MONTH, 1);
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		return range;
	}
	
	/**
	 * Gets the rolling365 days.
	 *
	 * @return the rolling365 days
	 */
	@JsonIgnore
	public static TimeRange getRolling365Days() {
		TimeRange range = new TimeRange();
		Calendar now = GregorianCalendar.getInstance();
		range.setEndTimeEpoch(now.getTimeInMillis());
		Calendar begin = GregorianCalendar.getInstance();
		begin.setTimeInMillis(now.getTimeInMillis());
		begin.add(Calendar.DAY_OF_MONTH, -365);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		return range;
	}
	
	/**
	 * From message.
	 *
	 * @param body the body
	 * @return the time range
	 * @throws JsonMappingException the json mapping exception
	 * @throws JsonParseException the json parse exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static TimeRange fromMessage(String body) throws JsonMappingException, JsonParseException, IOException {
		return mapper.readValue(body, TimeRange.class);
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
	 * Gets the last5 minutes.
	 *
	 * @return the last5 minutes
	 */
	@JsonIgnore
	public static TimeRange getLast5Minutes() {
		TimeRange range = new TimeRange();
		
		Calendar begin = GregorianCalendar.getInstance();
		begin.add(Calendar.MINUTE, -5);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		range.setStartTimeEpoch(begin.getTimeInMillis());
		
		Calendar end = GregorianCalendar.getInstance();
		range.setEndTimeEpoch(end.getTimeInMillis());
		
		return range;
	}
	
}
