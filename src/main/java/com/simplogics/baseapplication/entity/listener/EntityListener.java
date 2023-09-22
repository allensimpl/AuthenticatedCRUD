package com.simplogics.baseapplication.entity.listener;

import java.time.OffsetDateTime;
import java.time.ZoneId;

import javax.persistence.PrePersist;

import org.springframework.stereotype.Component;

import com.simplogics.baseapplication.entity.BaseEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EntityListener {

	@PrePersist
	public void beforeSave(final BaseEntity reference) {
		reference.setCreatedAt(OffsetDateTime.now(ZoneId.of("UTC")));
		reference.setUpdatedAt(OffsetDateTime.now(ZoneId.of("UTC")));
//		TODO save createdby // need auth support to enable it
	}
	
	public void beforeUpdate(final BaseEntity reference) {
		reference.setUpdatedAt(OffsetDateTime.now(ZoneId.of("UTC")));
//		TODO save updatedBy // need auth support to enable it
	}

}
