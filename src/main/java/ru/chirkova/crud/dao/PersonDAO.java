package ru.chirkova.crud.dao;

import org.springframework.stereotype.Component;
import ru.chirkova.crud.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people=new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT,"Anna"));
        people.add(new Person(++PEOPLE_COUNT,"Misha"));
        people.add(new Person(++PEOPLE_COUNT,"Sofya"));
        people.add(new Person(++PEOPLE_COUNT,"Dasha"));

    }

    public List<Person> index(){
        return people;
    }
    public Person show(int id){
        Person result=null;
        for (Person x : people){
            if (x.getId()==id) {result= x; break;}
        }
        return result;
    }
    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
    public void updatePerson(int id,Person person){
        Person updatingPerson=show(id);
        updatingPerson.setName(person.getName());
    }
    public void deletePerson(int id) {
        Person deletePerson=show(id);
        people.remove(deletePerson);
    }
}
