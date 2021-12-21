package DBManager;

import dataStructure.DataTable;
import dataStructure.Gender;
import dataStructure.StudentFromCSV;
import entities.*;
import services.*;

import javax.persistence.Table;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class DBManager {

    public DBManager() {
    }

    public void loadDataInDB(DataTable table) throws SQLException {
        loadCityInDB(table);
        loadStudentInDB(table);
        loadPeopleInDB(table);
        loadCourseInDB(table);
        loadStudentRegistrationInDB(table);
        loadTopicInDB(table);
        loadTaskInDB(table);
        loadStudentScoreInDB(table);

    }

    private void loadCityInDB(DataTable table) throws SQLException {
        var cityService = new CityService();
        for (StudentFromCSV student : table.getStudents()) {
            if (student.getCity() == null)
                continue;
            String cityName = student.getCity();
            City city = cityService.getCityByCityName(cityName);
            if (city == null) {
                city = new City();
                city.setCityName(cityName);
                cityService.addCity(city);
            }
        }
    }

    private void loadStudentInDB(DataTable table) throws SQLException {
        var studentService = new StudentService();
        for (StudentFromCSV s : table.getStudents()) {
            var student = studentService.getStudentByUsername(s.getFirstname(), s.getLastname());
            if (student == null) {
                student = new Student();
                student.setSpecialization(s.getSpecialization());
                if (s.getLastname() == null)
                    s.convertUsernameToNameLastname();
                student.setLastname(s.getLastname());
                student.setFirstname(s.getFirstname());
                student.setGroupNumber(s.getAcademicGroup());
                studentService.addStudent(student);
            }
        }
    }

    private void loadPeopleInDB(DataTable table) throws SQLException {
        var studentService = new StudentService();
        var cityService = new CityService();
        var personService = new PersonService();
        for (StudentFromCSV s : table.getStudents()) {
            if (s.getUserVkId() == 0)
                continue;
            Student student = studentService.getStudentByUsername(s.getFirstname(), s.getLastname());
            if (student.getPerson() == null) {
                var person = new Person();
                person.setBirthday(s.getBirthDay());
                person.setLinkPhoto(s.getLinkPhoto() != null ? s.getLinkPhoto().toString() : null);
                person.setGender(s.getGender() == Gender.MALE ? "Муж." : s.getGender() == Gender.FEMALE ? "Жен." : null);
                person.setVkId(s.getUserVkId());
                person.setStudent(student);

                if (s.getCity() != null) {
                    City city = cityService.getCityByCityName(s.getCity());
                    cityService.connectPersonWithWith(city.getId(), person);
                }
                studentService.updateStudent(student);
                personService.addPerson(person);

            }
        }
    }

    private void loadCourseInDB(DataTable table) throws SQLException {
        var courseService = new CourseService();
        Course course = courseService.getCourseByCourseName(table.getCourseName());
        if (course == null) {
            course = new Course();
            course.setCourseName(table.getCourseName());
            course.setMaxScore(table.getMaxScores().getScoreForCourse());
            courseService.addCourse(course);
        }
    }

    private void loadTopicInDB(DataTable table) throws SQLException {
        var topicService = new TopicService();
        var courseService = new CourseService();
        for (dataStructure.Topic t : table.getMaxScores().getTopics()) {
            Topic topic = topicService.getTopicByTopicName(t.getName());
            if (topic == null) {
                Course course = courseService.getCourseByCourseName(table.getCourseName());
                topic = new Topic();
                topic.setTopicName(t.getName());
                topic.setMaxScore(t.getTotalScore());
                topic.setCourse(course);

                topicService.addTopic(topic);

                courseService.connectTopicWithCourse(course.getId(), topic);
            }
        }
    }

    private void loadTaskInDB(DataTable table) throws SQLException {
        var taskService = new TaskService();
        var topicService = new TopicService();
        for (dataStructure.Topic topic : table.getMaxScores().getTopics()) {
            for (dataStructure.Task t : topic.getTasks()) {
                Topic topicFromDB = topicService.getTopicByTopicName(topic.getName());
                Task task = new Task();
                task.setTaskName(t.getTitle());
                task.setTopic(topicFromDB);
                task.setMaxScore(t.getScore());
                taskService.addTask(task);
                topicService.connectTaskWithTopic(topicFromDB.getId(), task);
            }
        }

    }

    private void loadStudentScoreInDB(DataTable table) throws SQLException {
        var studentService = new StudentService();
        var courseService = new CourseService();
        var studentRegistrationService = new StudentRegistrationService();
        for (StudentFromCSV s : table.getStudents()) {
            Student student = studentService.getStudentByUsername(s.getFirstname(), s.getLastname());
            Course course = courseService.getCourseByCourseName(table.getCourseName());
            System.out.println(s.getFirstname());
            System.out.println(s.getLastname());
            System.out.println(table.getCourseName());
            StudentRegistration registration = studentRegistrationService
                    .getStudentRegistrationOnCourse(student.getId(), course.getId());
            for (dataStructure.Topic topic : s.getCourse().getTopics()) {
                loadScoreInDB(topic, registration);
            }
        }
    }

    private void loadScoreInDB(dataStructure.Topic topic, StudentRegistration registration) throws SQLException {
        var taskService = new TaskService();
        var topicService = new TopicService();
        var registrationService = new StudentRegistrationService();
        var scoreService = new TaskScoreService();
        Topic topicFromDB = topicService.getTopicByTopicName(topic.getName());
        Collection<Task> tasks = topicService.getTasksFromTopic(topicFromDB.getId());
        int i = 0;
        for (Task task : tasks) {
            var taskScore = new TaskScore();
            taskScore.setScore(topic.getTasks().get(i).getScore());
            taskScore.setStudentRegistration(registration);
            taskScore.setTask(task);
            i++;
            scoreService.addTaskScore(taskScore);

            taskService.connectTaskScoreWithTask(task.getId(), taskScore);
            registrationService.connectTaskScoreWithStudentRegistration(registration.getId(), taskScore);
        }

    }

    private void loadStudentRegistrationInDB(DataTable table) throws SQLException {
        var studentService = new StudentService();
        var courseService = new CourseService();
        var studentRegistrationService = new StudentRegistrationService();

        for (StudentFromCSV s : table.getStudents()) {
            var studentRegistration = new StudentRegistration();
            Student student = studentService.getStudentByUsername(s.getFirstname(), s.getLastname());
            Course course = courseService.getCourseByCourseName(table.getCourseName());
            studentRegistration.setStudent(student);
            studentRegistration.setCourse(course);

            studentRegistrationService.addStudentStudentRegistration(studentRegistration);

            studentService.connectStudentRegistrationWithStudent(student.getId(), studentRegistration);
            courseService.connectStudentRegistrationWithCourse(course.getId(), studentRegistration);

        }
    }
}
