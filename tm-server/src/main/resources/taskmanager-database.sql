CREATE DATABASE IF NOT EXISTS `taskmanager` ;

CREATE TABLE IF NOT EXISTS `user` (
	`id` VARCHAR(36) NOT NULL,
	`login` VARCHAR(16) NOT NULL,
	`pwdHash` VARCHAR(32) NOT NULL,
	`role` VARCHAR(16) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `login` (`login`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `session` (
	`id` VARCHAR(36) NOT NULL,
	`userId` VARCHAR(36) NOT NULL,
	`userLogin` VARCHAR(16) NOT NULL,
	`hash` VARCHAR(32) NOT NULL,
	`creationDate` DATETIME NOT NULL,
	`sortMethod` VARCHAR(32) NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `userId` (`userId`),
	CONSTRAINT `FK_session_user_id` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE IF NOT EXISTS `project` (
	`id` VARCHAR(36) NOT NULL,
	`userId` VARCHAR(36) NOT NULL,
	`status` VARCHAR(16) NOT NULL,
	`name` VARCHAR(16) NOT NULL,
	`description` VARCHAR(64) NOT NULL,
	`creationDate` DATETIME NOT NULL,
	`startDate` DATETIME NOT NULL,
	`endDate` DATETIME NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `userId` (`userId`),
	CONSTRAINT `FK_project_user_id` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE IF NOT EXISTS `task` (
	`id` VARCHAR(36) NOT NULL,
	`userId` VARCHAR(36) NOT NULL,
	`projectId` VARCHAR(36) NOT NULL,
	`status` VARCHAR(16) NOT NULL,
	`name` VARCHAR(16) NOT NULL,
	`description` VARCHAR(64) NOT NULL,
	`creationDate` DATETIME NOT NULL,
	`startDate` DATETIME NOT NULL,
	`endDate` DATETIME NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `userId` (`userId`),
	INDEX `projectId` (`projectId`),
	CONSTRAINT `FK_task_user_id` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `FK_task_project_id` FOREIGN KEY (`projectId`) REFERENCES `project` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
