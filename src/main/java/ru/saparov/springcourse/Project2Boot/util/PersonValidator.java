package ru.saparov.springcourse.Project2Boot.util;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.saparov.springcourse.Project2Boot.models.Person;
import ru.saparov.springcourse.Project2Boot.servises.PeopleService;

@Component
public class PersonValidator implements Validator {

   private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if(peopleService.getPersonByFIO(person.getFIO()).isPresent()){
            errors.rejectValue("FIO","","This FIO is already taken");
        }

    }
}
