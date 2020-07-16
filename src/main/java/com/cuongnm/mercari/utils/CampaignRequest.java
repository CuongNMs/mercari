package com.cuongnm.mercari.utils;

import java.io.Serializable;


public class CampaignRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7261829674111275837L;

	private String campaignsName;

	private String campaignImagePath;

	private String campaignDescribed;

	public CampaignRequest(String campaignsName, String campaignImagePath, String campaignDescribed) {
		super();
		this.campaignsName = campaignsName;
		this.campaignImagePath = campaignImagePath;
		this.campaignDescribed = campaignDescribed;
	}

	public CampaignRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCampaignsName() {
		return campaignsName;
	}

	public void setCampaignsName(String campaignsName) {
		this.campaignsName = campaignsName;
	}

	public String getCampaignImagePath() {
		return campaignImagePath;
	}

	public void setCampaignImagePath(String campaignImagePath) {
		this.campaignImagePath = campaignImagePath;
	}

	public String getCampaignDescribed() {
		return campaignDescribed;
	}

	public void setCampaignDescribed(String campaignDescribed) {
		this.campaignDescribed = campaignDescribed;
	}

}
