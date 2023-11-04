CREATE TABLE Accord (ID int(5) NOT NULL AUTO_INCREMENT, Name varchar(50) NOT NULL UNIQUE, PRIMARY KEY (ID), UNIQUE INDEX (ID));
CREATE TABLE Brand (ID int(5) NOT NULL AUTO_INCREMENT, Name varchar(255) NOT NULL UNIQUE, CountryID int(5), Photo_Path varchar(255), Text_Path varchar(255), Website varchar(255), PRIMARY KEY (ID), UNIQUE INDEX (ID));
CREATE TABLE Collection_Entry (ID int(5) NOT NULL AUTO_INCREMENT, UserID int(5) NOT NULL, PerfumeID int(5) NOT NULL, Collection_TypeID int(5) NOT NULL, Quantity tinyint NOT NULL, Note blob, PRIMARY KEY (ID), UNIQUE INDEX (ID));
CREATE TABLE Collection_Type (ID int(5) NOT NULL AUTO_INCREMENT, Name varchar(50) NOT NULL UNIQUE, PRIMARY KEY (ID), UNIQUE INDEX (ID));
CREATE TABLE Concentration (ID int(5) NOT NULL AUTO_INCREMENT, Name varchar(50) NOT NULL UNIQUE, PRIMARY KEY (ID), UNIQUE INDEX (ID));
CREATE TABLE Country (ID int(5) NOT NULL AUTO_INCREMENT, Name varchar(255) NOT NULL UNIQUE, PRIMARY KEY (ID), UNIQUE INDEX (ID));
CREATE TABLE Note (ID int(5) NOT NULL AUTO_INCREMENT, Name varchar(50) NOT NULL UNIQUE, PRIMARY KEY (ID), UNIQUE INDEX (ID));
CREATE TABLE Perfume (ID int(5) NOT NULL AUTO_INCREMENT, Name varchar(100) NOT NULL, BrandID int(5) NOT NULL, ConcentrationID int(5) NOT NULL, PerfumerID int(5), Gallery_Path varchar(255), Year smallint(5), Scent float NOT NULL, Longevity float NOT NULL, Sillage float NOT NULL, Bottle float NOT NULL, Value float NOT NULL, PRIMARY KEY (ID), UNIQUE INDEX (ID));
CREATE TABLE Perfume_Accord (PerfumeID int(5) NOT NULL, AccordID int(5) NOT NULL, Yes int(5) NOT NULL, No int(5) NOT NULL, PRIMARY KEY (PerfumeID, AccordID));
CREATE TABLE Perfume_Note (PerfumeID int(5) NOT NULL, NoteID int(5) NOT NULL, Yes int(5) NOT NULL, No int(5) NOT NULL, PRIMARY KEY (PerfumeID, NoteID));
CREATE TABLE Perfume_Tag (PerfumeID int(5) NOT NULL, TagID int(5) NOT NULL, Yes int(5) NOT NULL, No int(5) NOT NULL, PRIMARY KEY (PerfumeID, TagID));
CREATE TABLE Perfumer (ID int(5) NOT NULL AUTO_INCREMENT, Name varchar(255) NOT NULL UNIQUE, CountryID int(5), Photo_Path varchar(255), Text_Path varchar(255), Website varchar(255), PRIMARY KEY (ID), UNIQUE INDEX (ID));
CREATE TABLE Review (ID int(5) NOT NULL AUTO_INCREMENT, UserID int(5) NOT NULL, PerfumeID int(5) NOT NULL, Scent tinyint, Longevity tinyint, Sillage tinyint, Bottle tinyint, Value tinyint, Text_Path varchar(255), PRIMARY KEY (ID), UNIQUE INDEX (ID));
CREATE TABLE Similar (ID int(5) NOT NULL AUTO_INCREMENT, PerfumeID_1 int(5) NOT NULL, PerfumeID_2 int(5) NOT NULL, Yes int(5) NOT NULL, No int(5) NOT NULL, PRIMARY KEY (ID), UNIQUE INDEX (ID));
CREATE TABLE `Tag` (ID int(5) NOT NULL AUTO_INCREMENT, Name varchar(50) NOT NULL UNIQUE, PRIMARY KEY (ID), UNIQUE INDEX (ID));
CREATE TABLE `User` (ID int(5) NOT NULL AUTO_INCREMENT, Login varchar(30) NOT NULL UNIQUE, Email varchar(255) NOT NULL UNIQUE, Password varchar(255) NOT NULL, Avatar_Path varchar(255) UNIQUE, PRIMARY KEY (ID), UNIQUE INDEX (ID));
ALTER TABLE Perfume ADD CONSTRAINT FKPerfume309434 FOREIGN KEY (BrandID) REFERENCES Brand (ID);
ALTER TABLE Perfume ADD CONSTRAINT FKPerfume702103 FOREIGN KEY (PerfumerID) REFERENCES Perfumer (ID);
ALTER TABLE Review ADD CONSTRAINT FKReview970746 FOREIGN KEY (UserID) REFERENCES `User` (ID);
ALTER TABLE Review ADD CONSTRAINT FKReview845071 FOREIGN KEY (PerfumeID) REFERENCES Perfume (ID);
ALTER TABLE Collection_Entry ADD CONSTRAINT FKCollection916491 FOREIGN KEY (UserID) REFERENCES `User` (ID);
ALTER TABLE Collection_Entry ADD CONSTRAINT FKCollection277151 FOREIGN KEY (Collection_TypeID) REFERENCES Collection_Type (ID);
ALTER TABLE Similar ADD CONSTRAINT FKSimilar433766 FOREIGN KEY (PerfumeID_1) REFERENCES Perfume (ID);
ALTER TABLE Collection_Entry ADD CONSTRAINT FKCollection790816 FOREIGN KEY (PerfumeID) REFERENCES Perfume (ID);
ALTER TABLE Perfumer ADD CONSTRAINT FKPerfumer850002 FOREIGN KEY (CountryID) REFERENCES Country (ID);
ALTER TABLE Brand ADD CONSTRAINT FKBrand585949 FOREIGN KEY (CountryID) REFERENCES Country (ID);
ALTER TABLE Perfume ADD CONSTRAINT FKPerfume619132 FOREIGN KEY (ConcentrationID) REFERENCES Concentration (ID);
ALTER TABLE Similar ADD CONSTRAINT FKSimilar433767 FOREIGN KEY (PerfumeID_2) REFERENCES Perfume (ID);
ALTER TABLE Perfume_Note ADD CONSTRAINT FKPerfume_No141421 FOREIGN KEY (PerfumeID) REFERENCES Perfume (ID);
ALTER TABLE Perfume_Note ADD CONSTRAINT FKPerfume_No79943 FOREIGN KEY (NoteID) REFERENCES Note (ID);
ALTER TABLE Perfume_Tag ADD CONSTRAINT FKPerfume_Ta274517 FOREIGN KEY (PerfumeID) REFERENCES Perfume (ID);
ALTER TABLE Perfume_Tag ADD CONSTRAINT FKPerfume_Ta570436 FOREIGN KEY (TagID) REFERENCES `Tag` (ID);
ALTER TABLE Perfume_Accord ADD CONSTRAINT FKPerfume_Ac311578 FOREIGN KEY (PerfumeID) REFERENCES Perfume (ID);
ALTER TABLE Perfume_Accord ADD CONSTRAINT FKPerfume_Ac737039 FOREIGN KEY (AccordID) REFERENCES Accord (ID);

