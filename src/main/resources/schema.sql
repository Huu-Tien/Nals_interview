DROP TABLE IF EXISTS `WORK`;

CREATE TABLE `WORK` (
    ID            bigint auto_increment primary key,
    WORK_NAME     varchar(255) null,
    STARTING_DATE date         null,
    ENDING_DATE   date         null,
    STATUS        smallint     null
);