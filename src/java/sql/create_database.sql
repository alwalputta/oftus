drop schema if exists app;
create schema if not exists app;
use app;
/*
delete from message;
delete from UserClick;
delete from Bookmark;
delete from Category;
delete from UserLogin;
delete from UserLoginAddress;
delete from UserEmail;
delete from Users;

commit;

DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS UserEmail CASCADE;
DROP TABLE IF EXISTS UserLoginAddress CASCADE;
DROP TABLE IF EXISTS UserLogin CASCADE;
DROP TABLE IF EXISTS Category CASCADE;
DROP TABLE IF EXISTS Bookmark CASCADE;
DROP TABLE IF EXISTS BookmarkClick CASCADE;
DROP TABLE IF EXISTS UserClick CASCADE;
DROP TABLE IF EXISTS message CASCADE;
*/

DROP TABLE IF EXISTS Gender;
CREATE TABLE Gender (
  gender_id int(20) DEFAULT NULL,
  gender_code varchar(10) NOT NULL,
  gender_name varchar(45) NOT NULL,
  status varchar(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (gender_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS State;
CREATE TABLE State (
  state_id int(20) DEFAULT NULL,
  state_code varchar(10) NOT NULL,
  state_name varchar(45) NOT NULL,
  status varchar(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (state_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS User CASCADE;
CREATE TABLE User (
  user_id int(20) NOT NULL,
  first_name varchar(250),
  middle_name varchar(250),
  last_name varchar(250),
  gender varchar(100),
  DOB date,
  status varchar(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS Address CASCADE;
CREATE TABLE Address (
  address_id int(20) NOT NULL,
  address1 VARCHAR(1000),
  address2 VARCHAR(1000),
  city VARCHAR(250),
  state_code VARCHAR(100),
  zip VARCHAR(100),
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (address_id)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS UserAddress CASCADE;
CREATE TABLE UserAddress (
  user_id int(20) NOT NULL,
  address_id int(20) NOT NULL,
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
  FOREIGN KEY (address_id) REFERENCES Address(address_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS Phone CASCADE;
CREATE TABLE Phone (
  phone_id int(20) NOT NULL,
  phone_number VARCHAR(50),
  phone_type VARCHAR(50),
  status VARCHAR(10) DEFAULT 'A',
  validate_flag VARCHAR(10),
  validate_date DATETIME,
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (phone_id)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS UserPhone CASCADE;
CREATE TABLE UserPhone (
  phone_id int(20) NOT NULL,
  user_id int(20) NOT NULL,
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (phone_id) REFERENCES User(user_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS Email CASCADE;
CREATE TABLE Email (
  email_id int(20) NOT NULL,
  email_address VARCHAR(500) NOT NULL,
  primary_mail VARCHAR(10),
  validate_flag VARCHAR(10),
  validate_date DATETIME,
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (email_id)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS UserEmail CASCADE;
CREATE TABLE UserEmail (
  email_id int(20) NOT NULL,
  user_id int(20) NOT NULL,
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (email_id) REFERENCES Email(email_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS Credential CASCADE;
CREATE TABLE Credential (
  credential_id int(20) NOT NULL,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  status varchar(10) DEFAULT 'A',
  create_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (credential_id)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS UserCredential CASCADE;
CREATE TABLE UserCredential (
  credential_id int(20) NOT NULL,
  user_id int(20) NOT NULL,
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (credential_id) REFERENCES Credential(credential_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS UserLoginLog CASCADE;
CREATE TABLE UserLoginLog (
  user_login_id int(20) NOT NULL,
  user_id int(20) NOT NULL,
  browser VARCHAR(500) NOT NULL,
  ip_address VARCHAR(500) NOT NULL,
  machine_name VARCHAR(500) NOT NULL,
  time_zone VARCHAR(500) NOT NULL,
  session_id VARCHAR(500) NOT NULL,
  create_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (user_login_id),
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS FailedLoginLog CASCADE;
CREATE TABLE FailedLoginLog (
  failed_login_id int(20) NOT NULL,
  username VARCHAR(500),
  password VARCHAR(500),
  browser VARCHAR(500) NOT NULL,
  ip_address VARCHAR(500) NOT NULL,
  machine_name VARCHAR(500) NOT NULL,
  time_zone VARCHAR(500) NOT NULL,
  session_id VARCHAR(500) NOT NULL,
  create_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (failed_login_id)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS UserClickLog CASCADE;
CREATE TABLE UserClickLog (
  user_click_id int(20) NOT NULL,
  user_login_id int(20) NOT NULL,
  form_name VARCHAR(500) NOT NULL,
  create_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (user_click_id),
  FOREIGN KEY (user_login_id) REFERENCES UserLoginLog(user_login_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS Category CASCADE;
CREATE TABLE Category (
  category_id int(20) NOT NULL,
  category_name varchar(250) NOT NULL,
  description VARCHAR(1000),
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (CATEGORY_ID)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS UserCategory CASCADE;
CREATE TABLE UserCategory (
  user_id int(20) NOT NULL,
  category_id int(20) NOT NULL,
  status VARCHAR(10) DEFAULT 'A',
  category_order int(20) DEFAULT 10000,
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
  FOREIGN KEY (category_id) REFERENCES Category(category_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS Bookmark CASCADE;
CREATE TABLE Bookmark (
  bookmark_id int(20) NOT NULL,
  bookmark_name VARCHAR(500) NOT NULL,
  hiper_link VARCHAR(1000) NOT NULL,
  description VARCHAR(1000),
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (bookmark_id)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS CategoryBookmark CASCADE;
CREATE TABLE CategoryBookmark (
  category_id int(20) NOT NULL,
  bookmark_id int(20) NOT NULL,
  status VARCHAR(10) DEFAULT 'A',
  bookmark_order int(20) DEFAULT 10000,
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (category_id) REFERENCES Category(category_id) ON DELETE CASCADE,
  FOREIGN KEY (bookmark_id) REFERENCES Bookmark(bookmark_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS BookmarkClick CASCADE;
CREATE TABLE BookmarkClick (
  bookmark_click_id int(20) NOT NULL,
  bookmark_id int(20) NOT NULL,
  hiper_link VARCHAR(1000) NOT NULL,
  create_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (bookmark_click_id),
  FOREIGN KEY (bookmark_id) REFERENCES Bookmark(bookmark_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS UserReferral CASCADE;
CREATE TABLE UserReferral (
  referral_id int(20) NOT NULL,
  user_id int(20) NOT NULL,
  referral_email VARCHAR(500) NOT NULL,
  message VARCHAR(1000),
  create_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (referral_id),
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS UserPreference CASCADE;
CREATE TABLE UserReferral (
  preference_id int(20) NOT NULL,
  user_id int(20) NOT NULL,
  preference_code VARCHAR(50) NOT NULL,
  preference_value VARCHAR(50) NOT NULL,
  description VARCHAR(500) NOT NULL,
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (preference_id),
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;




