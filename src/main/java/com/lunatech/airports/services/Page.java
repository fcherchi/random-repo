
package com.lunatech.airports.services;

/**
 * @author Fernando
 *
 */
public class Page {
	/**
	 * The offset
	 */
	int offset;
	
	/** The Limit */
	int limit;

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
