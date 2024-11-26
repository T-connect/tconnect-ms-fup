package com.otsi.tconnect.ms.fup.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FUPScheduledTask {

	@Autowired
	private FUPService fUPService;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Scheduled(cron = "0 */5 * * * *")
	public void fUPScheduledTask() {
		log.info("FUPScheduledTask::Running task started at: " + LocalDateTime.now().format(formatter));
		fUPService.calculateFUPUsage();
		log.info("FUPScheduledTask::Running task ended at: " + LocalDateTime.now().format(formatter));
	}

}
