package com.cleanspark.esi.vtn;

import com.cleanspark.esi.struct.Event;
import com.cleanspark.esi.struct.Profile;
import com.cleanspark.esi.struct.RecordedData;
import com.cleanspark.esi.struct.TimeRange;
import com.cleanspark.esi.struct.Transaction;

/**
 * The Agent interface.  Any market participant must simply implement this interface on their represented entity (a
 * device, DER, Microgrid, etc.) to participate in the energy market.  In the future this may
 * also contain VTN functionality.
 */
public interface Agent {
	
	/**
	 * Gets the demand forecast for the specified time range.  This should be the forecasted amount of power
	 * to be utilized by the represented entity.
	 *
	 * @param range the time range for which a demand forecast is requested.
	 * @return the demand forecast
	 */
	public Profile getDemandForecast(TimeRange range);

	/**
	 * Gets the history for the specified time range.  This is generally meter data.
	 *
	 * @param range the time range
	 * @return the recorded data for the time range
	 */
	public RecordedData getHistory(TimeRange range);

	/**
	 * Process transaction.  The device is now part of an irrevocable contract to produce or consume power.
	 * The implementing method should record the transaction and schedule the represented entity to support the 
	 * terms of the transaction. 
	 *
	 * @param transaction the transaction
	 * @return true, if successful
	 */
	public boolean processTransaction(Transaction transaction);
	
	/**
	 * Process adr event.  Future.  Similarly to the transaction processing, the system should record the event a
	 * and schedule any system entity that is availale to support the terms of the event.
	 *
	 * @param event the event
	 * @return true, if successful
	 */
	//ADR-related
	public boolean processADREvent(Event event);
	
	/**
	 * Gets the sheddable demand forecast.  Similar to above demand forecast, this describes the portion of the demand
	 * that is non-critical for the specified time range.
	 *
	 * @param range the time range
	 * @return the sheddable demand forecast
	 */
	public Profile getSheddableDemandForecast(TimeRange range);
}
