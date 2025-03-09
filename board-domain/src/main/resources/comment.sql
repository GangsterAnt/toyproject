CREATE TABLE comment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    parent_comment_id BIGINT,
    content VARCHAR(255) NOT NULL,
    likes INT NOT NULL,
    dislikes INT NOT NULL
);