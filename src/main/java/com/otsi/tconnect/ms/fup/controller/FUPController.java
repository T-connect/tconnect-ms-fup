package com.otsi.tconnect.ms.fup.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.otsi.tconnect.ms.fup.service.FUPService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/cdr")
@Slf4j
public class FUPController {

	@Autowired
	private FUPService fUPService;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadCdrFile(@RequestParam("file") MultipartFile file,
			@RequestParam("deleteAll") String flag) {
		try {
			long successCount = 0;
			long failedCount = 0;
			if (file.isEmpty() || !file.getOriginalFilename().endsWith(".cdr")) {
				return ResponseEntity.badRequest().body("Invalid file format. Please upload a .cdr file.");
			}

			if (null != flag && flag.equalsIgnoreCase("Yes")) {
				fUPService.truncateTable();
			}
			
			fUPService.sendEmail();

			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

				String line;
				while ((line = reader.readLine()) != null) {
					try {
						fUPService.saveCdrRecord(line);
						successCount++;
					} catch (Exception e) {
						failedCount++;
						log.error("Skipping the Record error  "+e.getMessage() +"   line =" + line);
					}
				}
			}
			return ResponseEntity.ok("CDR file uploaded and processed successfully.  successCount = " + successCount
					+ " failedCount =  " + failedCount);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to process the file: " + e.getMessage());
		}
	}

}
