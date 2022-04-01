package az.task.service;

import az.task.exception.CourseNotFoundException;
import az.task.model.Course;
import az.task.repository.CourseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Integer courseId) {


        Optional<Course> byId = courseRepository.findById(courseId);

        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new CourseNotFoundException("Course id not found - " + courseId);
        }


    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public ResponseEntity<Course> updateCourse(Integer courseId, Course newCourse) {
        Optional<Course> byId = courseRepository.findById(courseId);

        if (byId.isPresent()) {
            Course course = byId.get();
            course.setName(newCourse.getName());
            course.setDepartment(newCourse.getDepartment());

            final Course updatedCourse = courseRepository.save(course);
            return ResponseEntity.ok(updatedCourse);
        } else {
            throw new CourseNotFoundException("Course id not found - " + courseId);
        }


    }

    public void deleteCourse(Integer courseId) {
        Optional<Course> byId = courseRepository.findById(courseId);

        if (byId.isPresent()) {
            Course course = byId.get();
            courseRepository.delete(course);
        } else {
            throw new CourseNotFoundException("Course id not found - " + courseId);
        }

    }


}
