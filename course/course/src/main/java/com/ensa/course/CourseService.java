package com.ensa.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository repository;
    private final StudentClient client;

    public void saveCourse(Course course) {
        repository.save(course);
    }

    public List<Course> findAllCourses() {
        return repository.findAll();
    }

    public CourseResponse findAllStudentsByCourse(Integer courseId) {
        var course = repository.findById(courseId).orElseThrow(() -> new RuntimeException("course not found"));
        var students = client.findAllStudentsByCourse(courseId);
        return CourseResponse.builder()
                .name(course.getName())
                .students(students)
                .build();
    }


}
