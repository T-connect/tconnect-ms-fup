package com.otsi.tconnect.ms.fup.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.otsi.tconnect.ms.fup.dto.FUPDetailUsageResponse;
import com.otsi.tconnect.ms.fup.dto.FUPUsageResponse;
import com.otsi.tconnect.ms.fup.fup.entity.FUPRecord;
import com.otsi.tconnect.ms.fup.service.FUPService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/fup")
@Slf4j
public class FUPController {

	@Autowired
	private FUPService fUPService;

	@PostMapping("/cdr/upload")
	public ResponseEntity<String> uploadCdrFile(@RequestParam("cdrFile") MultipartFile file,
			@RequestParam("isFullUpload") String flag) {
		try {
			long successCount = 0;
			long failedCount = 0;
			List<FUPRecord> crdRecordList = new ArrayList<>();
			if (file.isEmpty() || !file.getOriginalFilename().endsWith(".cdr")) {
				return ResponseEntity.badRequest().body("Invalid file format. Please upload a .cdr file.");
			}
			if (null != flag && flag.equalsIgnoreCase("Yes")) {
				fUPService.truncateTable();
			}
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

				String line;
				while ((line = reader.readLine()) != null) {
					try {
						crdRecordList.add(fUPService.saveCdrRecord(line));
						successCount++;
					} catch (Exception e) {
						failedCount++;
						log.error("Skipping the Record error  " + e.getMessage() + "   line =" + line);
					}
				}
			}
			fUPService.saveAll(crdRecordList);
			return ResponseEntity.ok("CDR file uploaded and processed successfully.  successCount = " + successCount
					+ " failedCount =  " + failedCount);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to process the file: " + e.getMessage());
		}
	}

	@GetMapping("/usage/{deviceId}")
	public ResponseEntity<FUPUsageResponse> getCurrentUsage(@PathVariable String deviceId) {
		return new ResponseEntity<FUPUsageResponse>(fUPService.getCurrentUsage(deviceId), HttpStatus.OK);
	}

	@GetMapping("/details/usage/{deviceId}")
	public ResponseEntity<FUPDetailUsageResponse> getDetailsCurrentUsage(@PathVariable String deviceId) {
		return new ResponseEntity<FUPDetailUsageResponse>(fUPService.getDetailsCurrentUsage(deviceId), HttpStatus.OK);
	}
	
	
}
