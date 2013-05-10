drop schema if exists app;
create schema if not exists app;
use app;

DROP TABLE IF EXISTS Gender;
CREATE TABLE Gender (
  gender_id varchar(200) DEFAULT NULL,
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
  state_id varchar(200) DEFAULT NULL,
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
  user_id varchar(200) NOT NULL,
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
  address_id varchar(200) NOT NULL,
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
  user_id varchar(200) NOT NULL,
  address_id varchar(200) NOT NULL,
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
  FOREIGN KEY (address_id) REFERENCES Address(address_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS Phone CASCADE;
CREATE TABLE Phone (
  phone_id varchar(200) NOT NULL,
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
  phone_id VARCHAR(200) NOT NULL,
  user_id VARCHAR(200) NOT NULL,
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (phone_id) REFERENCES Phone(phone_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS Email CASCADE;
CREATE TABLE Email (
  email_id VARCHAR(200) NOT NULL,
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
  email_id VARCHAR(200) NOT NULL,
  user_id VARCHAR(200) NOT NULL,
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (email_id) REFERENCES Email(email_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS Credential CASCADE;
CREATE TABLE Credential (
  credential_id VARCHAR(200) NOT NULL,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  status varchar(10) DEFAULT 'A',
  create_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  end_date DATETIME,
  PRIMARY KEY (credential_id)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS UserCredential CASCADE;
CREATE TABLE UserCredential (
  credential_id VARCHAR(200) NOT NULL,
  user_id VARCHAR(200) NOT NULL,
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (credential_id) REFERENCES Credential(credential_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS Role CASCADE;
CREATE TABLE Role (
  role_id VARCHAR(200) NOT NULL,
  role VARCHAR(250) NOT NULL,
  status varchar(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (role_id)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS UserRole CASCADE;
CREATE TABLE UserRole (
  role_id VARCHAR(200) NOT NULL,
  user_id VARCHAR(200) NOT NULL,
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (role_id) REFERENCES Role(role_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS UserLoginLog CASCADE;
CREATE TABLE UserLoginLog (
  login_log_id varchar(200) NOT NULL,
  user_id VARCHAR(200) NOT NULL,
  browser VARCHAR(500) NOT NULL,
  ip_address VARCHAR(500) NOT NULL,
  machine_name VARCHAR(500) NOT NULL,
  time_zone VARCHAR(500) NOT NULL,
  session_id VARCHAR(500) NOT NULL,
  create_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (login_log_id),
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS FailedLoginLog CASCADE;
CREATE TABLE FailedLoginLog (
  failed_login_id VARCHAR(200) NOT NULL,
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
  click_id varchar(200) NOT NULL,
  user_id VARCHAR(200) DEFAULT NULL,
  action_name varchar(255) DEFAULT NULL,
  create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (click_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS Category CASCADE;
CREATE TABLE Category (
  category_id VARCHAR(200) NOT NULL,
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
  user_id VARCHAR(200) NOT NULL,
  category_id VARCHAR(200) NOT NULL,
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
  bookmark_id VARCHAR(200) NOT NULL,
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
  category_id VARCHAR(200) NOT NULL,
  bookmark_id VARCHAR(200) NOT NULL,
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
  bookmark_click_id VARCHAR(200) NOT NULL,
  bookmark_id VARCHAR(200) NOT NULL,
  hiper_link VARCHAR(1000) NOT NULL,
  create_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (bookmark_click_id),
  FOREIGN KEY (bookmark_id) REFERENCES Bookmark(bookmark_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS UserReferral CASCADE;
CREATE TABLE UserReferral (
  referral_id VARCHAR(200) NOT NULL,
  user_id VARCHAR(200) NOT NULL,
  referral_email VARCHAR(500) NOT NULL,
  message VARCHAR(1000),
  create_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (referral_id),
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS Preference CASCADE;
CREATE TABLE Preference (
  preference_id VARCHAR(200) NOT NULL,
  preference_code VARCHAR(50) NOT NULL,
  preference_value VARCHAR(50) NOT NULL,
  description VARCHAR(500) NOT NULL,
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (preference_id)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS UserPreference CASCADE;
CREATE TABLE UserPreference (
  preference_id VARCHAR(200) NOT NULL,
  user_id VARCHAR(200) NOT NULL,
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
  FOREIGN KEY (preference_id) REFERENCES Preference(preference_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS UserDocument CASCADE;
CREATE TABLE UserDocument (
  document_id VARCHAR(200) NOT NULL,
  user_id VARCHAR(200) NOT NULL,
  file_type VARCHAR(100) NOT NULL,
  file_name varchar(500) NOT NULL,
  content blob NOT NULL,
  content_type varchar(255) NOT NULL,
  status VARCHAR(10) DEFAULT 'A',
  create_date DATETIME,
  end_date DATETIME,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (document_id),
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;

insert into gender (gender_id, gender_code, gender_name) values ('402881e43e8ccc1b013e8ccc20c80001', 'M', 'Male');

insert into state (state_id, state_code, state_name) values ('402881e43e8ccc1b013e8ccc20c80002', 'CA', 'California');

commit;

