package com.cleanspark.esi.struct;

// TODO: Auto-generated Javadoc
/**
 * The Class Transaction.
 */
public class Transaction {
	
	/** The id. */
	int id;
	
	/** The extended price. */
	Double extendedPrice; //Equivalent to power over delivery period; cost of transaction
	
	/** The power. */
	Double power; //Constant rate of delivery over the specified delivery period
	
	/** The delivery period. */
	TimeRange deliveryPeriod;
	
	/** The buyer. */
	Party buyer;
	
	/** The seller. */
	Party seller;
	
	/** The execution time. */
	long executionTime;
	
	/** The point of delivery. */
	Location pointOfDelivery;
	
	/** The meter id. */
	String meterId;
	
	/** The currency. */
	Currency currency;
	
	/** The units. */
	String units;

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
	
	/**
	 * Gets the extended price.
	 *
	 * @return the extended price
	 */
	public Double getExtendedPrice() {
		return extendedPrice;
	}
	
	/**
	 * Sets the extended price.
	 *
	 * @param extendedPrice the new extended price
	 */
	public void setExtendedPrice(Double extendedPrice) {
		this.extendedPrice = extendedPrice;
	}
	
	/**
	 * Gets the buyer.
	 *
	 * @return the buyer
	 */
	public Party getBuyer() {
		return buyer;
	}
	
	/**
	 * Sets the buyer.
	 *
	 * @param buyer the new buyer
	 */
	public void setBuyer(Party buyer) {
		this.buyer = buyer;
	}
	
	/**
	 * Gets the seller.
	 *
	 * @return the seller
	 */
	public Party getSeller() {
		return seller;
	}
	
	/**
	 * Sets the seller.
	 *
	 * @param seller the new seller
	 */
	public void setSeller(Party seller) {
		this.seller = seller;
	}
	
	/**
	 * Gets the execution time.
	 *
	 * @return the execution time
	 */
	public long getExecutionTime() {
		return executionTime;
	}
	
	/**
	 * Sets the execution time.
	 *
	 * @param executionTime the new execution time
	 */
	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}
}
