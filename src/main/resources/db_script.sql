CREATE DATABASE fup CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
use fup;
CREATE TABLE fup_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client VARCHAR(255),
    device VARCHAR(255),
    service INT,
    nas_ip_address VARCHAR(255),
    framed_ip_address VARCHAR(255),
    framed_ipv6_prefix VARCHAR(255),
    acct_status_type VARCHAR(255),
    acct_input_octets BIGINT,
    acct_output_octets BIGINT,
    acct_input_gigawords INT,
    acct_output_gigawords INT,
    acct_session_time INT,
    event_timestamp BIGINT,
    nas_port_id VARCHAR(255),
    acct_session_id VARCHAR(255),
    acct_delay_time INT,
    acct_terminate_cause VARCHAR(255)
);
