BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Exam" (
	"examNum"	INTEGER,
	"examName"	text,
	"subjectName"	TEXT,
	PRIMARY KEY("examNum"),
	FOREIGN KEY("subjectName") REFERENCES "subject"("subjectName")
);
CREATE TABLE IF NOT EXISTS "Subject" (
	"subjectName"	TEXT,
	"coordinater"	TEXT,
	PRIMARY KEY("subjectName"),
	FOREIGN KEY("coordinater") REFERENCES "users"("username")
);
CREATE TABLE IF NOT EXISTS "orders" (
	"id"	INTEGER,
	"user_id"	INTEGER NOT NULL,
	"product_id"	INTEGER NOT NULL,
	"quantity"	INTEGER NOT NULL,
	"total_price"	DECIMAL(10, 2) NOT NULL,
	"order_date"	DATETIME DEFAULT CURRENT_TIMESTAMP,
	"status"	TEXT DEFAULT 'pending',
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("product_id") REFERENCES "products"("id"),
	FOREIGN KEY("user_id") REFERENCES "users"("id")
);
CREATE TABLE IF NOT EXISTS "products" (
	"id"	INTEGER,
	"name"	TEXT NOT NULL,
	"description"	TEXT,
	"price"	DECIMAL(10, 2) NOT NULL,
	"stock"	INTEGER DEFAULT 0,
	"created_date"	DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "users" (
	"id"	INTEGER,
	"username"	TEXT NOT NULL,
	"email"	TEXT NOT NULL,
	"password"	VARCHAR(255),
	"role"	VARCHAR(255),
	"name"	VARCHAR(50) NOT NULL DEFAULT 'some_value',
	PRIMARY KEY("name","id")
);


INSERT INTO "users" VALUES (12,'a','aaa@a','rr','admin','aab');
INSERT INTO "users" VALUES (NULL,'a','aaa@a','44','admin','aac');
INSERT INTO "users" VALUES (NULL,'a','aaa@a','ffd','admin','aa');

COMMIT;