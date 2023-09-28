package com.simplogics.baseapplication.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Where;

import com.simplogics.baseapplication.entity.listener.EntityListener;
import com.simplogics.baseapplication.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Contains basic user information both store operator and 
 * 	farmer basic user information is saved here
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_users", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "email" }, name = "idx_users_email") })
@Where(clause = "deleted = false")
@EntityListeners(EntityListener.class)
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	@Enumerated(value = EnumType.STRING)
	private UserRole role;
}
