package com.graduation.clinic.dto;

public class SetReply {

	public Long reviewId;
	public String Reply;
	
	public SetReply(Long reviewId, String reply) {
		this.reviewId = reviewId;
		Reply = reply;
	}
	
	public Long getReviewId() {
		return reviewId;
	}
	public String getReply() {
		return Reply;
	}


	
}
