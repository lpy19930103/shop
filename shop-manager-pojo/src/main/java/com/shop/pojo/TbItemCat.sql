-- auto Generated on 2018-12-21
-- DROP TABLE IF EXISTS tb_item_cat;
CREATE TABLE tb_item_cat(
	id BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT 'id',
	parent_id BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'parentId',
	name VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'name',
	status INT (11) NOT NULL DEFAULT -1 COMMENT 'status',
	sort_order INT (11) NOT NULL DEFAULT -1 COMMENT 'sortOrder',
	is_parent TINYINT (3) NOT NULL DEFAULT 0 COMMENT 'isParent',
	created DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'created',
	updated DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'updated',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'tb_item_cat';
