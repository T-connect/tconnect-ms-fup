package com.otsi.tconnect.ms.fup.fup.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FUPUsageNotificationID implements Serializable {

	private static final long serialVersionUID = 2323433286289643447L;

	private String deviceId;

	private Integer month;

	private Integer year;

	private Integer threshold;

	@Override
	public int hashCode() {
		return Objects.hash(deviceId, month, threshold, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FUPUsageNotificationID other = (FUPUsageNotificationID) obj;
		return Objects.equals(deviceId, other.deviceId) && Objects.equals(month, other.month)
				&& Objects.equals(threshold, other.threshold) && Objects.equals(year, other.year);
	}

}
