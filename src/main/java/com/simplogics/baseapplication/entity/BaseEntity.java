package com.simplogics.baseapplication.entity;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

	private OffsetDateTime createdAt;

	private OffsetDateTime updatedAt;

	@OneToOne(fetch = FetchType.LAZY)
	private User createdByUser;

	@OneToOne(fetch = FetchType.LAZY)
	private User updatedByUser;

	@Column(columnDefinition = "boolean not null default false")
	private Boolean deleted = false;

	@Column(columnDefinition = "boolean not null default true")
	private Boolean active = true;

}
