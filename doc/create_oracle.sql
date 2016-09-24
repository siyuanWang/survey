ALTER TABLE SYSTEM.SURVEY_PAPER
 DROP PRIMARY KEY CASCADE;

DROP TABLE SYSTEM.SURVEY_PAPER CASCADE CONSTRAINTS;

CREATE TABLE SYSTEM.SURVEY_PAPER
(
  ID           NUMBER(20)                       NOT NULL,
  TITLE        VARCHAR2(200 BYTE)               NOT NULL,
  START_TIME   DATE                             NOT NULL,
  END_TIME     DATE                             NOT NULL,
  IS_PUBLISH   NUMBER(4)                        NOT NULL,
  CREATE_TIME  DATE                             NOT NULL,
  UPDATE_TIME  DATE                             NOT NULL,
  IS_DEL       INTEGER                          NOT NULL,
  DESCRIBE     VARCHAR2(4000 BYTE)
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;


DROP TABLE SYSTEM.SURVEY_PAPER_MODULE CASCADE CONSTRAINTS;

CREATE TABLE SYSTEM.SURVEY_PAPER_MODULE
(
  ID            NUMBER,
  NAME          VARCHAR2(300 BYTE),
  MODULE_INDEX  INTEGER,
  PAPER_ID      NUMBER
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;


ALTER TABLE SYSTEM.SURVEY_PAPER_MODULE_QUESTION
 DROP PRIMARY KEY CASCADE;

DROP TABLE SYSTEM.SURVEY_PAPER_MODULE_QUESTION CASCADE CONSTRAINTS;

CREATE TABLE SYSTEM.SURVEY_PAPER_MODULE_QUESTION
(
  ID           NUMBER                           NOT NULL,
  QUESTION_ID  NUMBER                           NOT NULL,
  MODULE_ID    NUMBER
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;


ALTER TABLE SYSTEM.SURVEY_QUESTION
 DROP PRIMARY KEY CASCADE;

DROP TABLE SYSTEM.SURVEY_QUESTION CASCADE CONSTRAINTS;

CREATE TABLE SYSTEM.SURVEY_QUESTION
(
  ID           NUMBER(20)                       NOT NULL,
  TITLE        VARCHAR2(200 BYTE)               NOT NULL,
  MODE_TYPE    INTEGER                          NOT NULL,
  OPTIONS      VARCHAR2(2000 BYTE)              NOT NULL,
  IS_DEL       INTEGER                          NOT NULL,
  UPDATE_TIME  DATE                             NOT NULL,
  CREATE_TIME  DATE                             NOT NULL
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;


ALTER TABLE SYSTEM.SURVEY_STATISTICS
 DROP PRIMARY KEY CASCADE;

DROP TABLE SYSTEM.SURVEY_STATISTICS CASCADE CONSTRAINTS;

CREATE TABLE SYSTEM.SURVEY_STATISTICS
(
  ID           NUMBER                           NOT NULL,
  IP           VARCHAR2(100 BYTE)               NOT NULL,
  EMAIL        VARCHAR2(200 BYTE),
  INSERT_TIME  DATE                             NOT NULL,
  PAPER_ID     NUMBER                           NOT NULL
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;


DROP TABLE SYSTEM.SURVEY_STATISTICS_QUESTION CASCADE CONSTRAINTS;

CREATE TABLE SYSTEM.SURVEY_STATISTICS_QUESTION
(
  PAPER_ID       NUMBER                         NOT NULL,
  QUESTION_ID    NUMBER                         NOT NULL,
  ANSWER         VARCHAR2(4000 BYTE)            NOT NULL,
  QUESTION_MODE  INTEGER                        NOT NULL,
  STATISTICS_ID  NUMBER                         NOT NULL
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;


ALTER TABLE SYSTEM.SYS_USER
 DROP PRIMARY KEY CASCADE;

DROP TABLE SYSTEM.SYS_USER CASCADE CONSTRAINTS;

CREATE TABLE SYSTEM.SYS_USER
(
  ID           NUMBER                           NOT NULL,
  LOGIN_NAME   VARCHAR2(200 BYTE)               NOT NULL,
  PASSWORD     VARCHAR2(400 BYTE)               NOT NULL,
  LOGIN_TIME   DATE                             NOT NULL,
  CREATE_TIME  DATE                             NOT NULL
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;


CREATE UNIQUE INDEX SYSTEM.SURVEY_PAPER_PK ON SYSTEM.SURVEY_PAPER
(ID)
LOGGING
TABLESPACE SYSTEM
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX SYSTEM.SURVEY_QUESTION_PK ON SYSTEM.SURVEY_QUESTION
(ID)
LOGGING
TABLESPACE SYSTEM
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX SYSTEM.SURVEY_STATISTICS_PK ON SYSTEM.SURVEY_STATISTICS
(ID)
LOGGING
TABLESPACE SYSTEM
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX SYSTEM.SYS_USER_PK ON SYSTEM.SYS_USER
(ID)
LOGGING
TABLESPACE SYSTEM
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX SYSTEM.TABLE1_PK ON SYSTEM.SURVEY_PAPER_MODULE_QUESTION
(ID)
LOGGING
TABLESPACE SYSTEM
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


ALTER TABLE SYSTEM.SURVEY_PAPER ADD (
  CONSTRAINT SURVEY_PAPER_PK
  PRIMARY KEY
  (ID)
  USING INDEX SYSTEM.SURVEY_PAPER_PK
  ENABLE VALIDATE);

ALTER TABLE SYSTEM.SURVEY_PAPER_MODULE_QUESTION ADD (
  CONSTRAINT TABLE1_PK
  PRIMARY KEY
  (ID)
  USING INDEX SYSTEM.TABLE1_PK
  ENABLE VALIDATE);

ALTER TABLE SYSTEM.SURVEY_QUESTION ADD (
  CONSTRAINT SURVEY_QUESTION_PK
  PRIMARY KEY
  (ID)
  USING INDEX SYSTEM.SURVEY_QUESTION_PK
  ENABLE VALIDATE);

ALTER TABLE SYSTEM.SURVEY_STATISTICS ADD (
  CONSTRAINT SURVEY_STATISTICS_PK
  PRIMARY KEY
  (ID)
  USING INDEX SYSTEM.SURVEY_STATISTICS_PK
  ENABLE VALIDATE);

ALTER TABLE SYSTEM.SYS_USER ADD (
  CONSTRAINT SYS_USER_PK
  PRIMARY KEY
  (ID)
  USING INDEX SYSTEM.SYS_USER_PK
  ENABLE VALIDATE);

  
  DROP SEQUENCE SYSTEM.SEQ_SURVEY_ID;

CREATE SEQUENCE SYSTEM.SEQ_SURVEY_ID
  START WITH 108
  MAXVALUE 999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;
