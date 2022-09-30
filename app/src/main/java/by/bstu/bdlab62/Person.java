package by.bstu.bdlab62;

public class Person
{
    public String Surname;
    public String Name;
    public String Phone;
    public String DateOfBirth;

    public Person(String surname, String name, String phone, String dateOfBirth){
        Surname = surname;
        Name = name;
        Phone = phone;
        DateOfBirth = dateOfBirth;
    }

    public Person(){

    }

    @Override
    public String toString() {
        return Surname + " " + Name + "\n" + Phone + "\n" + "Дата рождения: " + DateOfBirth;
    }
}
