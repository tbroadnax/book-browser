CREATE TABLE book (
    id INT NOT NULL,
    title VARCHAR(50),
    description VARCHAR(2000),
    thumbnail BLOB,
    page_views INT,
    publish_date DATE,
    PRIMARY KEY (id)
);
