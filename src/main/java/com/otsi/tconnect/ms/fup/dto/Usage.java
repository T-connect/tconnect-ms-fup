package com.otsi.tconnect.ms.fup.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Usage {

	private LocalDate date;
	private double uploadUsage;
	private double downLoadUsage;

}
