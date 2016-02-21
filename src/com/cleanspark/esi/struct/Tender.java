package com.cleanspark.esi.struct;

// TODO: Auto-generated Javadoc
/**
 * The Class Tender.
 */
public class Tender {
	
	/** The id. */
	int id;
	
	/** The price. */
	Double price; //Offered price of a single unit of energy.  This is a limit order price (the highest buy price or lowest sell).
	
	/** The power. */
	Double power; //Constant rate of delivery over the specified delivery period
	
	/** The delivery period. */
	TimeRange deliveryPeriod;
	
	/** The offer type. */
	OfferType offerType;
	
	/** The offering party. */
	Party offeringParty;
	
	/** The counter party. */
	Party counterParty;
	
	/** The availability interval. */
	TimeRange availabilityInterval;
	
	/** The point of delivery. */
	Location pointOfDelivery;
	
	/** The meter id. */
	String meterId;
	
	/** The currency. */
	Currency currency;
	
	/** The units. */
	String units;
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	
	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * Gets the power.
	 *
	 * @return the power
	 */
	public Double getPower() {
		return power;
	}
	
	/**
	 * Sets the power.
	 *
	 * @param power the new power
	 */
	public void setPower(Double power) {
		this.power = power;
	}
	
	/**
	 * Gets the delivery period.
	 *
	 * @return the delivery period
	 */
	public TimeRange getDeliveryPeriod() {
		return deliveryPeriod;
	}
	
	/**
	 * Sets the delivery period.
	 *
	 * @param deliveryPeriod the new delivery period
	 */
	public void setDeliveryPeriod(TimeRange deliveryPeriod) {
		this.deliveryPeriod = deliveryPeriod;
	}
	
	/**
	 * Gets the offer type.
	 *
	 * @return the offer type
	 */
	public OfferType getOfferType() {
		return offerType;
	}
	
	/**
	 * Sets the offer type.
	 *
	 * @param offerType the new offer type
	 */
	public void setOfferType(OfferType offerType) {
		this.offerType = offerType;
	}
	
	/**
	 * Gets the offering party.
	 *
	 * @return the offering party
	 */
	public Party getOfferingParty() {
		return offeringParty;
	}
	
	/**
	 * Sets the offering party.
	 *
	 * @param offeringParty the new offering party
	 */
	public void setOfferingParty(Party offeringParty) {
		this.offeringParty = offeringParty;
	}
	
	/**
	 * Gets the counter party.
	 *
	 * @return the counter party
	 */
	public Party getCounterParty() {
		return counterParty;
	}
	
	/**
	 * Sets the counter party.
	 *
	 * @param counterParty the new counter party
	 */
	public void setCounterParty(Party counterParty) {
		this.counterParty = counterParty;
	}
	
	/**
	 * Gets the availability interval.
	 *
	 * @return the availability interval
	 */
	public TimeRange getAvailabilityInterval() {
		return availabilityInterval;
	}
	
	/**
	 * Sets the availability interval.
	 *
	 * @param availabilityInterval the new availability interval
	 */
	public void setAvailabilityInterval(TimeRange availabilityInterval) {
		this.availabilityInterval = availabilityInterval;
	}
	
	/**
	 * Gets the meter id.
	 *
	 * @return the meter id
	 */
	public String getMeterId() {
		return meterId;
	}
	
	/**
	 * Sets the meter id.
	 *
	 * @param meterId the new meter id
	 */
	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}
	
	/**
	 * Gets the currency.
	 *
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}
	
	/**
	 * Sets the currency.
	 *
	 * @param currency the new currency
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
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
	 * Gets the point of delivery.
	 *
	 * @return the point of delivery
	 */
	public Location getPointOfDelivery() {
		return pointOfDelivery;
	}
	
	/**
	 * Sets the point of delivery.
	 *
	 * @param pointOfDelivery the new point of delivery
	 */
	public void setPointOfDelivery(Location pointOfDelivery) {
		this.pointOfDelivery = pointOfDelivery;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
}
