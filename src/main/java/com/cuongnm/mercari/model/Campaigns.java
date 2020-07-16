package com.cuongnm.mercari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "campaigns")
public class Campaigns {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "increment")
	@Column(name = "campaigns_id")
	private Long campaignsId;

	@Column(name = "campaign_name")
	private String campaignsName;

	@Column(name = "campaign_image")
	private String campaignImagePath;

	@Column(name = "campaign_described")
	private String campaignDescribed;

	public Campaigns() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Campaigns(String campaignsName, String campaignDescribed) {
		this.campaignsName = campaignsName;
		this.campaignDescribed = campaignDescribed;
	}

	public Campaigns(String campaignsName, String campaignImagePath, String campaignDescribed) {
		super();
		this.campaignsName = campaignsName;
		this.campaignImagePath = campaignImagePath;
		this.campaignDescribed = campaignDescribed;
	}

	public Long getCampaignsId() {
		return campaignsId;
	}

	public void setCampaignsId(Long campaignsId) {
		this.campaignsId = campaignsId;
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
