package com.cuongnm.mercari.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuongnm.mercari.model.Campaigns;

public interface CampaignRepository extends JpaRepository<Campaigns, Long> {

}
