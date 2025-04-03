CREATE TABLE comment_v2 (
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(255) NOT NULL,
    likes INT DEFAULT 0 NOT NULL,
    dislikes INT DEFAULT 0 NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    hidden BOOLEAN DEFAULT FALSE NOT NULL,
    owner_member_id BIGINT NOT NULL,
    root_post_id BIGINT NOT NULL,
    parent_comment_id BIGINT NULL
);

--create unique index comment_idx_1 on comment_v0 (created_at);
--create unique index comment_idx_2 on comment_v0 (modified_at);
--create unique index comment_idx_3 on comment_v0 (created_at, parent_comment_id);
create index idx_deleted_at on comment_v2 (deleted_at);
create index idx_created_at on comment_v2 (created_at);