package com.otsi.tconnect.ms.fup.user.entity;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dim_group")
public class Group {

	@Id
	@Column(name = "group_id")
	private String groupId;

	@Column(name = "name")
	private String name;

	@Column(name = "`desc`")
	private String desc;

	@Column(name = "status")
	private String status;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_group", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users;
	
	@PrePersist
	public void prePersist() {
		this.groupId = generateId();
	}

	private String generateId() {
		StringBuilder sb = new StringBuilder("Grp");
		String randomSuffix = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
		sb.append(randomSuffix);
		return sb.toString();
	}

}
