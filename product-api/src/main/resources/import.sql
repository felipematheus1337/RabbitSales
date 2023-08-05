INSERT INTO CATEGORY (ID, DESCRIPTION) VALUES (1, "Comic Books");
INSERT INTO CATEGORY (ID, DESCRIPTION) VALUES (2, "Movies");
INSERT INTO CATEGORY (ID, DESCRIPTION) VALUES (3, "Books");

INSERT INTO SUPPLIER (ID, NAME) VALUES (1, "Panini Comics");
INSERT INTO SUPPLIER (ID, DESCRIPTION) VALUES (2, "AMAZON");


INSERT INTO PRODUCT (ID, NAME, FK_SUPPLIER, FK_CATEGORY, QUANTITY_AVAILABLE, CREATED_AT)
 VALUES (1, "Crise nas infinitas terras", 1, 1, 10, CURRENT_TIMESTAMP);

 INSERT INTO PRODUCT (ID, NAME, FK_SUPPLIER, FK_CATEGORY, QUANTITY_AVAILABLE, CREATED_AT)
  VALUES (1, "Interestelar", 2, 2, 5, CURRENT_TIMESTAMP);

  INSERT INTO PRODUCT (ID, NAME, FK_SUPPLIER, FK_CATEGORY, QUANTITY_AVAILABLE, CREATED_AT)
   VALUES (1, "Harry Potter E a Pedra filosofal", 3, 2, 3, CURRENT_TIMESTAMP);