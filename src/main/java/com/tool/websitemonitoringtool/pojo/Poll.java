package com.tool.websitemonitoringtool.pojo;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Poll {

	@Id
	private Long id;

	private Status status;

	private ZonedDateTime time;

	private Long latency;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Check chk;

	public Poll() {
	}

	public Poll(Status status, ZonedDateTime time, Long latency, Check chk) {
		this.status = status;
		this.time = time;
		this.latency = latency;
		this.chk = chk;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ZonedDateTime getTime() {
		return time;
	}

	public void setTime(ZonedDateTime time) {
		this.time = time;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Check getChk() {
		return chk;
	}

	public void setChk(Check chk) {
		this.chk = chk;
	}

	public Long getLatency() {
		return latency;
	}

	public void setLatency(Long latency) {
		this.latency = latency;
	}
}
