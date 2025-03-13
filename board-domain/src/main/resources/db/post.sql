CREATE TABLE post (
    post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    content VARCHAR(255) NOT NULL,
    likes INT DEFAULT 0 NOT NULL,
    dislikes INT DEFAULT 0 NOT NULL,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP,
    owner_member_id BIGINT NOT NULL
);