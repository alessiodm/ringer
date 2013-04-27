SET storage_engine=MYISAM;

 -- Dropping tables: pay attention to order for FK
drop table if exists T_RELATION;
drop table if exists T_RING;
drop table if exists T_USER;

create table T_USER (
    `ID` BIGINT NOT NULL AUTO_INCREMENT,
    `USERNAME` VARCHAR(255) UNIQUE NOT NULL,
    `ENC_PASSWORD` VARCHAR(255) NOT NULL,

    PRIMARY KEY (`ID`)
);

create table T_RING (
    `ID` BIGINT NOT NULL AUTO_INCREMENT,
    `USER_ID` BIGINT NOT NULL,
    `CONTENT` VARCHAR(255) NULL,
    `TIMESTAMP` TIMESTAMP NOT NULL,

    PRIMARY KEY (`ID`),
    FOREIGN KEY (`USER_ID`) REFERENCES T_USER(`ID`) ON DELETE CASCADE,
	FULLTEXT INDEX `full_text_ring` (`CONTENT` ASC)
);

create table T_RELATION (
    `FOLLOWER_ID` BIGINT NOT NULL,
    `FOLLOWED_ID` BIGINT NOT NULL,

    PRIMARY KEY (`FOLLOWER_ID`, `FOLLOWED_ID`),
    FOREIGN KEY (`FOLLOWER_ID`) REFERENCES T_USER(`ID`) ON DELETE CASCADE,
    FOREIGN KEY (`FOLLOWED_ID`) REFERENCES T_USER(`ID`) ON DELETE CASCADE
);

INSERT INTO T_USER (ID, USERNAME, ENC_PASSWORD) VALUES (1, 'user1', '18aab3392031eab3c193e9f24ed43897'); -- Pass=user1
INSERT INTO T_USER (ID, USERNAME, ENC_PASSWORD) VALUES (2, 'user2', 'b1f4cdf12f5cfc7b8995e7c6a925d456'); -- Pass=user2

INSERT INTO T_RELATION (FOLLOWER_ID, FOLLOWED_ID) VALUES (1, 2);

INSERT INTO T_RING (ID, USER_ID, CONTENT, TIMESTAMP) VALUES (1, 1, 'Ring 1 User 1', '2010-09-12 10:10:10');
INSERT INTO T_RING (ID, USER_ID, CONTENT, TIMESTAMP) VALUES (2, 1, 'Ring 2 User 1', '2010-10-11 10:10:10');
INSERT INTO T_RING (ID, USER_ID, CONTENT, TIMESTAMP) VALUES (3, 1, 'Ring 3 User 1', '2010-11-09 10:10:10');
INSERT INTO T_RING (ID, USER_ID, CONTENT, TIMESTAMP) VALUES (4, 2, 'Ring 1 User 2', '2011-09-12 10:10:10');
INSERT INTO T_RING (ID, USER_ID, CONTENT, TIMESTAMP) VALUES (5, 2, 'Ring 2 User 2', '2011-10-11 10:10:10');
INSERT INTO T_RING (ID, USER_ID, CONTENT, TIMESTAMP) VALUES (6, 2, 'Ring 3 User 2', '2011-11-09 10:10:10');

