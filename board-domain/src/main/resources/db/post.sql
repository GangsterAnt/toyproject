CREATE TABLE post_v1 (
    post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    content VARCHAR(255) NOT NULL,
    likes INT DEFAULT 0 NOT NULL,
    dislikes INT DEFAULT 0 NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    hidden BOOLEAN DEFAULT FALSE NOT NULL,
    owner_member_id BIGINT NOT NULL
);

--create unique index post_idx_1 on post_v0 (created_at);
--create unique index idx_deleted_at on post_v1 (deleted_at);
create index idx_deleted_at on post_v1 (deleted_at);
create index idx_created_at on post_v1 (created_at);