create index IX_B02C80B7 on ToDoList_Task (groupId, userId, parentId);
create index IX_663DAA04 on ToDoList_Task (groupId, userId, status);
create unique index IX_EF766C50 on ToDoList_Task (groupId, uuid_[$COLUMN_LENGTH:75$]);
create index IX_859CB06E on ToDoList_Task (uuid_[$COLUMN_LENGTH:75$]);