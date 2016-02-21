package com.cleanspark.esi.ven;

import java.util.Iterator;
import java.util.List;

import com.cleanspark.esi.struct.Event;
import com.cleanspark.esi.struct.MarketCredentials;
import com.cleanspark.esi.struct.Profile;
import com.cleanspark.esi.struct.RecordedData;
import com.cleanspark.esi.struct.Tender;
import com.cleanspark.esi.struct.TimeRange;
import com.cleanspark.esi.struct.Transaction;
import com.cleanspark.esi.vtn.Agent;

/**
 * The Class MarketParticipant.  This class will be fully implemented in such a way as to service as a VEN for a market facilitator.
 * The implementation provided will implement a TEMIX style TE implementation, but there is no reason this cannot be overridden 
 * to implement a different market.
 */
public class MarketParticipant {
	
	/** The represented entity (EV, device, microgrid, storage device, etc). */
	Agent representedEntity;
	
	/** The credentials needed to authenticate into the market. */
	MarketCredentials credentials;
	
	/**
	 * Register into the market using the provided credentials.
	 *
	 * @return true, if successful
	 */
	public boolean register() {
		return false;
	}
	
	/**
	 * Enroll as a market participant.
	 *
	 * @return true, if successful
	 */
	public boolean enroll() {
		return false;
	}
	
	/**
	 * Builds the rate forecast for time range by submitting non-binding tenders against the VTN.
	 *
	 * @param range the range
	 * @return the profile
	 */
	public Profile buildRateForecast(TimeRange range) {
		Profile demandProfile = representedEntity.getDemandForecast(range);
		
		//TODO Build list of tenders from profile and send to VTN
		List<Tender> tenders = buildTenders(demandProfile);
		
		//Build profile on results returned
		return null;
	}
	
	/**
	 * Gets the rate forecast for time range by submitting non-binding tenders against the VTN.
	 *
	 * @param demandProfile the demand profile
	 * @return the rate forecast
	 */
	public Profile getRateForecast(Profile demandProfile) {
		//TODO Build list of tenders from profile and send to VTN
		List<Tender> tenders = buildTenders(demandProfile);
		
		//Build profile on results returned
		return null;
	}
	
	/**
	 * Submit binding tenders for profile.
	 *
	 * @param profile the profile
	 * @return true, if successful
	 */
	public boolean submitTendersForProfile(Profile profile) {
		for (Iterator<Tender> i = buildTenders(profile).iterator(); i.hasNext();) {
			Tender tender = (Tender) i.next();
			submitTender(tender);
		}
		
		return true;
	}
	
	/**
	 * Builds the tenders for each time period in the profile.
	 *
	 * @param profile the demand profile
	 * @return the list
	 */
	public List<Tender> buildTenders(Profile profile) {
		return null;
	}
	
	/**
	 * Submit tender using EITender functionality.
	 *
	 * @param tender the tender
	 * @return true, if successful
	 */
	public boolean submitTender(Tender tender) {
		//Build EiTender payload
		return false;
	}
	
	/**
	 * Gets the current tenders.
	 *
	 * @return the current tenders
	 */
	public List<Tender> getCurrentTenders() {

		return null;
	}
	
	/**
	 * Subscribe to current tenders.
	 *
	 * @return true, if successful
	 */
	public boolean subscribeToCurrentTenders() {

		return false;
	}
	
	/**
	 * Receive tender.
	 *
	 * @param tender the tender
	 */
	public void receiveTender(Tender tender) {

		return;
	}
	
	/**
	 * Send delivery report containing meter information about the tender's time range.
	 *
	 * @param tender the tender
	 * @return true, if successful
	 */
	public boolean sendDeliveryReport(Tender tender) {
		RecordedData historicalData = representedEntity.getHistory(tender.getDeliveryPeriod());
		
		//TODO Build delivery report and send to VTN
		return false;
	}
	
	/**
	 * Receive transaction from the VTN that will require action on the part of the represented entity.
	 *
	 * @param transaction the transaction
	 * @return true, if successful
	 */
	public boolean receiveTransaction(Transaction transaction) {
		return representedEntity.processTransaction(transaction);
	}
	
	/**
	 * Receive adr event from the VTN that will require action on the part of the represented entity.
	 *
	 * @param event the event
	 * @return true, if successful
	 */
	public boolean receiveADREvent(Event event) {
		return representedEntity.processADREvent(event);
	}

	/**
	 * Gets the market context, or the rules of the current marketing implementation.
	 *
	 * @return the market context
	 */
	public void getMarketContext() {
		//Placeholder
		return ;
	}
	
	/**
	 * Gets the represented entity.
	 *
	 * @return the represented entity
	 */
	public Agent getRepresentedEntity() {
		return representedEntity;
	}

	/**
	 * Sets the represented entity.
	 *
	 * @param representedEntity the new represented entity
	 */
	public void setRepresentedEntity(Agent representedEntity) {
		this.representedEntity = representedEntity;
	}

	/**
	 * Gets the credentials.
	 *
	 * @return the credentials
	 */
	public MarketCredentials getCredentials() {
		return credentials;
	}

	/**
	 * Sets the credentials.
	 *
	 * @param credentials the new credentials
	 */
	public void setCredentials(MarketCredentials credentials) {
		this.credentials = credentials;
	}
	
	
}
