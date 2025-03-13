CREATE TABLE comment (
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(255) NOT NULL,
    likes INT DEFAULT 0 NOT NULL,
    dislikes INT DEFAULT 0 NOT NULL,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP,
    owner_member_id BIGINT NOT NULL
    root_post_id BIGINT NOT NULL
    child_comment_id BIGINT
    parent_comment_id BIGINT
);