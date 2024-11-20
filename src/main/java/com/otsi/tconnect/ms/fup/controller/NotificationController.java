/*
 * package com.otsi.tconnect.ms.fup.controller;
 * 
 * 
 * import org.springframework.messaging.handler.annotation.MessageMapping;
 * import org.springframework.messaging.handler.annotation.Payload; import
 * org.springframework.messaging.simp.SimpMessagingTemplate; import
 * org.springframework.stereotype.Controller;
 * 
 * @Controller public class NotificationController {
 * 
 * private final SimpMessagingTemplate messagingTemplate;
 * 
 * public NotificationController(SimpMessagingTemplate messagingTemplate) {
 * this.messagingTemplate = messagingTemplate; }
 * 
 * @MessageMapping("/sendToUser") // Endpoint for sending messages public void
 * sendToUser(@Payload Notification notification) { // Send a message to a
 * specific user messagingTemplate.convertAndSendToUser(
 * notification.getRecipient(), // User-specific destination "/queue/messages",
 * // Destination suffix notification ); } }
 */