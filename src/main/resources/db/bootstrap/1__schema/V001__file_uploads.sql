DROP TABLE IF EXISTS `file_uploads`;
CREATE  TABLE `file_uploads`
(

    `id`                      BIGINT(20)                           NOT NULL AUTO_INCREMENT,
    `uuid`                      VARCHAR(1024)  DEFAULT NULL,
    `generic_name`               VARCHAR(1024)  DEFAULT NULL,
    `s3_path`          VARCHAR(1024),
    `data_type`  VARCHAR(1024),
    `date_of_upload`  DATETIME                                      DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;