package az.task.controller;


import az.task.exception.CourseNotFoundException;
import az.task.model.Course;
import az.task.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    final private CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{id}")
        public Course getCourse(@PathVariable(value = "id") Integer courseId){

        return courseService.getCourseById(courseId);
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable(value ="id") Integer courseId,
                                               @RequestBody Course newCourse){
        return courseService.updateCourse(courseId, newCourse);
    }

    @DeleteMapping("/courses/{id}")
    public String deleteCourse(@PathVariable(value = "id") Integer courseId){
        Course course = courseService.getCourseById(courseId);
        if (course == null){
            throw new CourseNotFoundException("Course id not found - " + courseId);
        }
        courseService.deleteCourse(courseId);
        return "Deleted course id: " + courseId;
    }
}
