CREATE TABLE smt_user (
	user_id	varchar(100)	PRIMARY KEY,
	user_pw	varchar(200)	NOT NULL,
	user_nm	varchar(50)	NOT NULL,
	user_auth	char(1)	NOT NULL	DEFAULT 'U',
	quit_yn	char(1)	NOT NULL	DEFAULT 'N',
	reg_dt	timestamp	NULL	DEFAULT localtimestamp,
	quit_dt	timestamp	NULL,
	udt_dt	timestamp	NULL
);

COMMENT ON COLUMN smt_user.user_id is '사용자 E-Mail 주소';
COMMENT ON COLUMN smt_user.user_auth is 'A - 관리자 / U - 사용자 / O - 사업주';
COMMENT ON COLUMN smt_user.quit_yn is 'Y/N';

CREATE TABLE smt_category (
	ctg_id	varchar(10)	PRIMARY KEY,
	ctg_nm	varchar(50)	NOT NULL,
	ctg_prnt_id	varchar(10)	NULL,
	ctg_ord	smallint	NOT NULL,
	reg_dt	timestamp	NULL	DEFAULT localtimestamp,
	reg_id	varchar(100)	NULL,
	udt_dt	timestamp	NULL,
	udt_id	varchar(100)	NULL,
	del_yn	char(1)	NULL	DEFAULT 'N'
);

COMMENT ON COLUMN smt_category.ctg_id is 'A-Z + 숫자(00~99)';
COMMENT ON COLUMN smt_category.ctg_prnt_id is 'root는 NULL / 1단계 : A-Z';
COMMENT ON COLUMN smt_category.del_yn is 'Y/N';

CREATE TABLE smt_attach_main (
	atta_mkey	uuid	PRIMARY KEY,
	reg_dt	timestamp	NULL	DEFAULT localtimestamp,
	reg_id	varchar(100)	NULL,
	udt_dt	timestamp	NULL,
	del_yn	char(1)	NULL	DEFAULT 'N'
);

COMMENT ON COLUMN smt_attach_main.atta_mkey is 'UUID 생성 / JAVA에서 생성';
COMMENT ON COLUMN smt_attach_main.del_yn is 'Y/N';

CREATE TABLE smt_attach_files (
	atta_fkey	serial	PRIMARY KEY,
	atta_mkey	uuid	NOT NULL,
	attr_fnm	varchar(100)	NOT NULL,
	atta_sv_pth	varchar(100)	NOT NULL,
	att_sv_fnm	varchar(100)	NOT NULL,
	atta_fsize	bigint	NULL,
	reg_dt	timestamp	NULL	DEFAULT localtimestamp,
	reg_id	varchar(100)	NULL,
	udt_dt	timestamp	NULL,
	del_yn	char(1)	NULL	DEFAULT 'N',
	CONSTRAINT fk_attach_files_mkey
		FOREIGN KEY(atta_mkey) 
		REFERENCES smt_attach_main(atta_mkey)
		ON DELETE CASCADE
);

COMMENT ON COLUMN smt_attach_files.atta_fkey is '자동증가';
COMMENT ON COLUMN smt_attach_files.atta_mkey is 'smt_attach_main 테이블 atta_mkey';
COMMENT ON COLUMN smt_attach_files.del_yn is 'Y/N';

CREATE TABLE smt_store (
	str_id	uuid	PRIMARY KEY	DEFAULT uuid_generate_v4(),
	str_nm	varchar(200)	NOT NULL,
	str_cnt	text	NOT NULL,
	str_dist	varchar(200)	NULL,
	geom	geometry(Point)	NOT NULL,
	sls_dw	varchar(20)	NULL,
	sls_st_tm	time	NULL,
	sls_ed_tm	time	NULL,
	srch_tag	text	NULL,
	atta_mkey	uuid	NULL,
	ctg_id	varchar(10)	NOT NULL,
	user_id	varchar(100)	NOT NULL,
	reg_dt	timestamp	NULL	DEFAULT localtimestamp,
	udt_dt	timestamp	NULL,
	del_yn	char(1)	NULL	DEFAULT 'N',
	CONSTRAINT fk_store_atta_mkey
		FOREIGN KEY(atta_mkey) 
		REFERENCES smt_attach_main(atta_mkey)
		ON DELETE SET NULL,
	CONSTRAINT fk_store_ctg_id
		FOREIGN KEY(ctg_id) 
		REFERENCES smt_category(ctg_id)
		ON DELETE CASCADE,
	CONSTRAINT fk_store_user_id
		FOREIGN KEY(user_id) 
		REFERENCES smt_user(user_id)
		ON DELETE CASCADE
);

COMMENT ON COLUMN smt_store.str_id is 'UUID 자동 생성';
COMMENT ON COLUMN smt_store.str_dist is '위/경도 기준 법정동(리) 주소';
COMMENT ON COLUMN smt_store.sls_st_tm is '24시간 기준';
COMMENT ON COLUMN smt_store.sls_ed_tm is '24시간 기준';
COMMENT ON COLUMN smt_store.del_yn is 'Y/N';

CREATE TABLE smt_store_menu (
	menu_id	serial	PRIMARY KEY,
	str_id	uuid	NOT NULL,
	menu_nm	varchar(100)	NOT NULL,
	menu_prc	int	NOT NULL,
	menu_dscnt	smallint	NULL,
	menu_cnt	text	NULL,
	atta_mkey	uuid	NULL,
	menu_ord	smallint	NOT NULL,
	mn_menu_yn	char(1)	NULL	DEFAULT 'N',
	reg_dt	timestamp	NULL	DEFAULT localtimestamp,
	udt_dt	timestamp	NULL,
	del_yn	char(1)	NULL	DEFAULT 'N',
	CONSTRAINT fk_store_menu_str_id
		FOREIGN KEY(str_id) 
		REFERENCES smt_store(str_id)
		ON DELETE CASCADE,
	CONSTRAINT fk_store_menu_atta_mkey
		FOREIGN KEY(atta_mkey) 
		REFERENCES smt_attach_main(atta_mkey)
		ON DELETE SET NULL
);

COMMENT ON COLUMN smt_store_menu.mn_menu_yn is 'Y/N';
COMMENT ON COLUMN smt_store_menu.del_yn is 'Y/N';

CREATE TABLE smt_favorites (
	str_id	uuid	PRIMARY KEY,
	user_id	varchar(100)	PRIMARY KEY,
	reg_dt	timestamp	NULL	DEFAULT localtimestamp,
	del_dt	timestamp	NULL,
	del_yn	char(1)	NULL	DEFAULT 'N',
	CONSTRAINT fk_favorites_str_id
		FOREIGN KEY(str_id) 
		REFERENCES smt_store(str_id)
		ON DELETE CASCADE,
	CONSTRAINT fk_favorites_user_id
		FOREIGN KEY(user_id) 
		REFERENCES smt_user(user_id)
		ON DELETE CASCADE
);

COMMENT ON COLUMN smt_favorites.del_yn is 'Y/N';

CREATE TABLE smt_appr (
	appr_no	serial	PRIMARY KEY,
	user_id	varchar(100)	NOT NULL,
	str_id	uuid	NOT NULL,
	appr_mrk	smallint	NOT NULL,
	appr_cnt	text	NULL,
	atta_mkey	uuid	NULL,
	reg_dt	timestamp	NULL	DEFAULT localtimestamp,
	udt_dt	timestamp	NULL,
	del_yn	char(1)	NULL	DEFAULT 'N',
	CONSTRAINT fk_appr_str_id
		FOREIGN KEY(str_id) 
		REFERENCES smt_store(str_id)
		ON DELETE SET NULL,
	CONSTRAINT fk_appr_user_id
		FOREIGN KEY(user_id) 
		REFERENCES smt_user(user_id)
		ON DELETE SET NULL,
	CONSTRAINT fk_appr_atta_mkey
		FOREIGN KEY(atta_mkey) 
		REFERENCES smt_attach_main(atta_mkey)
		ON DELETE SET NULL
);

COMMENT ON COLUMN smt_appr.del_yn is 'Y/N';
