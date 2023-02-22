CREATE TABLE ipp.member_info_tbl (
    fk_member_id    NUMBER NOT NULL,
    member_info_id  NUMBER NOT NULL,
    feeling1_code   NUMBER DEFAULT 0 NOT NULL,
    feeling2_code   NUMBER DEFAULT 0 NOT NULL,
    feeling3_code   NUMBER DEFAULT 0 NOT NULL,
    interest_code   NUMBER DEFAULT 0 NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE users LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT );

CREATE UNIQUE INDEX ipp.member_info_pk ON
    ipp.member_info_tbl (
        fk_member_id
    ASC,
        member_info_id
    ASC )
        TABLESPACE users PCTFREE 10
            STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
        LOGGING;

ALTER TABLE ipp.member_info_tbl ADD CONSTRAINT member_info_pk PRIMARY KEY ( member_info_id );

CREATE TABLE ipp.member_tbl (
    member_id           NUMBER NOT NULL,
    birthday            DATE NOT NULL,
    rfid_serial_number  VARCHAR2(16 BYTE) NOT NULL,
    barcode_number      VARCHAR2(20 BYTE) NOT NULL,
    username            VARCHAR2(20 BYTE) NOT NULL,
    gender              CHAR(1 BYTE) DEFAULT NULL NOT NULL
)
PCTFREE 10 PCTUSED 40 TABLESPACE users LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT );

COMMENT ON COLUMN ipp.member_tbl.member_id IS
    '
';

CREATE UNIQUE INDEX ipp.member_pk ON
    ipp.member_tbl (
        member_id
    ASC )
        TABLESPACE users PCTFREE 10
            STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
        LOGGING;

ALTER TABLE ipp.member_tbl
    ADD CONSTRAINT member_pk PRIMARY KEY ( member_id )
        USING INDEX ipp.member_pk;

CREATE TABLE ipp.recommendation_result (
    fk_member_info     NUMBER NOT NULL,
    recommendation_id  NUMBER NOT NULL,
    recommended_at     DATE
)
PCTFREE 10 PCTUSED 40 TABLESPACE users LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT );

CREATE UNIQUE INDEX ipp.recommendation_result_pk ON
    ipp.recommendation_result (
        fk_member_info
    ASC,
        recommendation_id
    ASC )
        TABLESPACE users PCTFREE 10
            STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
        LOGGING;

ALTER TABLE ipp.recommendation_result ADD CONSTRAINT recommendation_result_pk PRIMARY KEY ( recommendation_id );

ALTER TABLE ipp.recommendation_result ADD CONSTRAINT recommendation_result__un UNIQUE ( fk_member_info );

CREATE TABLE ipp.recommended_book (
    fk_recommendation_result  NUMBER NOT NULL,
    recommended_book_id       NUMBER NOT NULL,
    book_name                 BLOB NOT NULL,
    author                    VARCHAR2(20 BYTE),
    publisherd                VARCHAR2(30 BYTE),
    book_img_link             CLOB NOT NULL,
    book_link                 CLOB
)
PCTFREE 10 PCTUSED 40 TABLESPACE users LOGGING
    STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT )
    LOB ( book_name ) STORE AS (
        TABLESPACE users
        STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 BUFFER_POOL DEFAULT )
        CHUNK 8192
        RETENTION
        ENABLE STORAGE IN ROW
        NOCACHE LOGGING
    )
    LOB ( book_img_link ) STORE AS (
        TABLESPACE users
        STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 BUFFER_POOL DEFAULT )
        CHUNK 8192
        RETENTION
        ENABLE STORAGE IN ROW
        NOCACHE LOGGING
    )
    LOB ( book_link ) STORE AS (
        TABLESPACE users
        STORAGE ( PCTINCREASE 0 MINEXTENTS 1 MAXEXTENTS UNLIMITED FREELISTS 1 BUFFER_POOL DEFAULT )
        CHUNK 8192
        RETENTION
        ENABLE STORAGE IN ROW
        NOCACHE LOGGING
    );

ALTER TABLE ipp.member_info_tbl
    ADD CONSTRAINT member_info_tbl_member_tbl_fk FOREIGN KEY ( fk_member_id )
        REFERENCES ipp.member_tbl ( member_id )
    NOT DEFERRABLE;

--  ERROR: FK name length exceeds maximum allowed length(30)
ALTER TABLE ipp.recommendation_result
    ADD CONSTRAINT recommendation_result_member_info_tbl_fk FOREIGN KEY ( fk_member_info )
        REFERENCES ipp.member_info_tbl ( member_info_id )
    NOT DEFERRABLE;

--  ERROR: FK name length exceeds maximum allowed length(30)
ALTER TABLE ipp.recommended_book
    ADD CONSTRAINT recommended_book_recommendation_result_fk FOREIGN KEY ( fk_recommendation_result )
        REFERENCES ipp.recommendation_result ( recommendation_id )
    NOT DEFERRABLE;