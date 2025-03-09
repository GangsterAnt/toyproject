CREATE TABLE post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    VARCHAR(100) title NOT NULL,
    VARCHAR(255) content NOT NULL,
    int likes NOT NULL,
    int dislikes NOT NULL
);