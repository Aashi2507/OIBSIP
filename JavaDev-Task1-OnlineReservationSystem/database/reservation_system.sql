CREATE DATABASE IF NOT EXISTS reservation_system;

USE reservation_system;

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS trains (
    train_number INT PRIMARY KEY,
    train_name VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS reservations (
    pnr VARCHAR(20) PRIMARY KEY,
    passenger_name VARCHAR(100) NOT NULL,
    train_number INT NOT NULL,
    class_type VARCHAR(30) NOT NULL,
    journey_date DATE NOT NULL,
    source_station VARCHAR(100) NOT NULL,
    destination_station VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_reservation_train
        FOREIGN KEY (train_number)
        REFERENCES trains(train_number)
);

INSERT INTO users (username, password)
VALUES ('aashi', 'aashi123')
ON DUPLICATE KEY UPDATE username = username;

INSERT INTO trains (train_number, train_name) VALUES
(12009, 'Mumbai Central Shatabdi Express'),
(12010, 'Ahmedabad Shatabdi Express'),
(12101, 'Jnaneswari Express'),
(12105, 'Vidarbha Express'),
(12137, 'Punjab Mail'),
(11010, 'Sinhagad Express'),
(12139, 'Sewagram Express'),
(11030, 'Koyna Express'),
(12301, 'Howrah Rajdhani Express'),
(12302, 'Howrah Rajdhani Express'),
(12309, 'Rajendra Nagar Rajdhani Express'),
(12310, 'Rajendra Nagar Rajdhani Express'),
(12313, 'Sealdah Rajdhani Express'),
(12314, 'Sealdah Rajdhani Express'),
(12423, 'Dibrugarh Rajdhani Express'),
(12424, 'Dibrugarh Rajdhani Express'),
(12425, 'Jammu Rajdhani Express'),
(12426, 'Jammu Rajdhani Express'),
(12431, 'Thiruvananthapuram Rajdhani Express'),
(12432, 'Thiruvananthapuram Rajdhani Express'),
(12433, 'Chennai Rajdhani Express'),
(12434, 'Chennai Rajdhani Express'),
(12901, 'Gujarat Mail'),
(12902, 'Gujarat Mail'),
(12903, 'Golden Temple Mail'),
(12904, 'Golden Temple Mail'),
(12939, 'Pune Jaipur Express'),
(12940, 'Jaipur Pune Superfast Express')
ON DUPLICATE KEY UPDATE train_name = VALUES(train_name);