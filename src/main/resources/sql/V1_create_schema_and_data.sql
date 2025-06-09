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
INSERT INTO "orders" VALUES (1,1,1,1,2500,'2025-06-05 10:14:15','completed');
INSERT INTO "orders" VALUES (2,1,2,2,179.8,'2025-06-05 10:14:15','pending');
INSERT INTO "orders" VALUES (3,2,3,1,299,'2025-06-05 10:14:15','shipped');
INSERT INTO "orders" VALUES (4,2,4,1,799,'2025-06-05 10:14:15','completed');
INSERT INTO "products" VALUES (1,'לפטופ Dell','מחשב נייד עם מסך 15 אינץ',2500,10,'2025-06-05 10:14:15');
INSERT INTO "products" VALUES (2,'עכבר אלחוטי','עכבר ארגונומי עם חיבור Bluetooth',89.9,25,'2025-06-05 10:14:15');
INSERT INTO "products" VALUES (3,'מקלדת מכנית','מקלדת גיימינג עם תאורה',299,15,'2025-06-05 10:14:15');
INSERT INTO "products" VALUES (4,'מסך 24 אינץ','מסך Full HD עם חיבור HDMI',799,8,'2025-06-05 10:14:15');
INSERT INTO "products" VALUES (5,'אוזניות גיימינג','אוזניות עם מיקרופון מובנה',199.9,20,'2025-06-05 10:14:15');
INSERT INTO "users" VALUES (1,'yonat','yonat@example.com','uyu',NULL,'some_value');
INSERT INTO "users" VALUES (2,'Yonatan','yonatan@example.com',NULL,NULL,'some_value');
INSERT INTO "users" VALUES (3,'admin','admin@example.com','e10adc3949ba59abbe56e057f20f883e','ADMIN','some_value');
INSERT INTO "users" VALUES (4,'user1','user1@example.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','USER','some_value');
INSERT INTO "users" VALUES (5,'manager','manager@example.com','ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f','MANAGER','some_value');
INSERT INTO "users" VALUES (6,'admin','admin@example.com','aaaaa','ADMIN','some_value');
INSERT INTO "users" VALUES (7,'user1','user1@example.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','USER','some_value');
INSERT INTO "users" VALUES (8,'manager','manager@example.com','ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f','MANAGER','some_value');
INSERT INTO "users" VALUES (9,'aaaaaa','aaa@a','aa','admin','some_value');
INSERT INTO "users" VALUES (10,'admin','aaa@a','aaa','student','some_value');
INSERT INTO "users" VALUES (11,'a','aaa@a','a','admin','aa');
INSERT INTO "users" VALUES (12,'a','aaa@a','rr','admin','aa');
INSERT INTO "users" VALUES (NULL,'a','aaa@a','44','admin','aa');
INSERT INTO "users" VALUES (NULL,'a','aaa@a','ffd','admin','aa');
COMMIT;
