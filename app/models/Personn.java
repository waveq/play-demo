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

//    @Required
    @Column(length = 5000)
    @MaxLength(5000)
    public String notes;
}
