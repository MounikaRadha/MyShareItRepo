DROP TABLE IF EXISTS `regular_words`;
CREATE  TABLE `regular_words`
(

    `id`                      BIGINT(20)                           NOT NULL AUTO_INCREMENT,
    `regular_word`                      VARCHAR(1024)  DEFAULT NULL,
    `already_used_status`               TINYINT(1) DEFAULT 0, --0 means not yet used
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;