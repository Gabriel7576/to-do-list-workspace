create index IX_45F07458 on ToDoList_Task (userId, priority);
create index IX_7D7F27A6 on ToDoList_Task (userId, status);
create index IX_166E1DB4 on ToDoList_Task (userId, taskId);
create unique index IX_21BD5FFC on ToDoList_Task (uuid_[$COLUMN_LENGTH:75$], groupId);