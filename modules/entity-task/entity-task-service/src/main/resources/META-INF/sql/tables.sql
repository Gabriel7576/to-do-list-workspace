create table ToDoList_Task (
	uuid_ VARCHAR(75) null,
	taskId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title VARCHAR(75) null,
	description TEXT null,
	status INTEGER,
	priority INTEGER,
	pathImage TEXT null,
	parentId LONG,
	fileEntryId LONG
);