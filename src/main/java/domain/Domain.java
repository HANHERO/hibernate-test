package domain;

import bl.HibernateUtil;
import entity.Address;
import entity.Employee;
import entity.Project;
import org.hibernate.Session;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Domain {public static void main(String[] args){

    Session session = HibernateUtil.getSessionFactory().openSession();

    session.beginTransaction();


    Address address = new Address();
    address.setId(1L);
    address.setCountry("DC");
    address.setCity("Gotham City");
    address.setStreet("Arkham street 1");
    address.setPostCode("12345");

    Project project = new Project();
    project.setTitle("GC:PD");

    Employee employee = new Employee();
    employee.setFirstName("James");
    employee.setLastName("Gordon");

    Calendar calendar = Calendar.getInstance();
    calendar.set(1939, Calendar.MAY, 1);

    employee.setBirthday(new java.sql.Date(calendar.getTime().getTime()));
    employee.setAddress(address);

    Set<Employee> employees = new HashSet<Employee>();
    employees.add(employee);
    project.setEmployees(employees);

    Set<Project> projects = new HashSet<Project>();
    projects.add(project);
    employee.setProjects(projects);

    session.save(address);
    session.save(project);
    session.save(employee);

    session.getTransaction().commit();
    HibernateUtil.shutdown();


}
}
