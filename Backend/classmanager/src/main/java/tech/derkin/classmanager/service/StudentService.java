package tech.derkin.classmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.derkin.classmanager.exception.UserNotFoundException;
import tech.derkin.classmanager.model.Student;
import tech.derkin.classmanager.repo.StudentRepo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StudentService {
    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Student addStudent(Student student){
        student.setStudentCode(UUID.randomUUID().toString());
        return studentRepo.save(student);
    }

    public List<Student> findAllStudents(){
        return studentRepo.findAll();
    }

    public Student updateStudent(Student student){
        return studentRepo.save(student);
    }

    public Student findStudentById(Long id){
        return studentRepo.findStudentById(id)
                .orElseThrow(() -> new UserNotFoundException("User by Id " + id + " was not found"));
    }

    public void deleteStudent(Long id){
        studentRepo.deleteStudentById(id);
    }

}
