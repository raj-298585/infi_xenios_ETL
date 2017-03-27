package com.infi.xenios.batch.item.entity;

public class HttpRequestEntity {
	private String asNumber;
	private String ipCidr;
	private String networkName;
	private String ipVersion;
	private String ipUsageType;

	public String getAsNumber() {
		return asNumber;
	}

	public void setAsNumber(String asNumber) {
		this.asNumber = asNumber;
	}

	public String getIpCidr() {
		return ipCidr;
	}

	public void setIpCidr(String ipCidr) {
		this.ipCidr = ipCidr;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getIpVersion() {
		return ipVersion;
	}

	public void setIpVersion(String ipVersion) {
		this.ipVersion = ipVersion;
	}

	public String getIpUsageType() {
		return ipUsageType;
	}

	public void setIpUsageType(String ipUsageType) {
		this.ipUsageType = ipUsageType;
	}

}
