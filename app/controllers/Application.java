package controllers;

import models.Personn;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(Form.form(Personn.class),""));
    }

    public static Result added() {
        return ok();
    }

    public static Result addPerson() {
        Form<Personn> filledForm = Form.form(Personn.class).bindFromRequest();

        if(filledForm.hasErrors()){
            return badRequest(index.render(filledForm, "Errors in form."));
        }

        Personn p = filledForm.get();
        p.save();

        return ok(index.render(Form.form(Personn.class),"Person has been added."));
    }

}
