package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

import static play.data.validation.Constraints.*;
import static play.data.validation.Constraints.Required;


/**
 * Created by szymonre on 2014-07-28.
 */

@Entity
public class Personn extends Model {

    @Id
    public long id;

    @Required
    @Column(length = 100)
    @MaxLength(100)
    public String name;

    @Required
    @Column(length = 100)
    @MaxLength(100)
    public String lastName;

    @Required
    public Date dateOfBirth;

    @Required
    @Pattern(".+\\@.+\\..+")
    public String email;

    @Required
    public int favoriteDb;

    @Required
    @Column(length = 5000)
    @MaxLength(5000)
    public String notes;

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public Date getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(Date dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public String getFavoriteDB() {
//        return favoriteDB;
//    }
//
//    public void setFavoriteDB(String favoriteDB) {
//        this.favoriteDB = favoriteDB;
//    }
}
