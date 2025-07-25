package com.andreisidoro.mcp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import com.andreisidoro.mcp.model.Course;

import jakarta.annotation.PostConstruct;

@Service
public class CourseService {

    private static final Logger log = LoggerFactory.getLogger(CourseService.class);
    private List<Course> courses = new ArrayList<>();

    @Tool(name = "get_courses", description = "Get a list of courses from Dan Vega")
    public List<Course> getCourses() {
        return courses;
    }

    @Tool(name = "get_course", description = "Get a single course from Dan Vega by title")
    public Course getCourse(String title) {
        return courses.stream()
            .filter(course -> course.title().equals(title))
            .findFirst()
            .orElse(null);
    }

    @Tool(name = "search_courses", description = "Search courses containing a keyword")
    public List<Course> searchCourses(String keyword) {
        return courses.stream()
            .filter(course -> course.title().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }

    @PostConstruct
    public void init() {
        courses.addAll(List.of(
            new Course("Building Web Applications with Spring Boot (FreeCodeCamp)", 
                      "https://youtu.be/31KTdfRH6nY"),
            new Course("Spring Boot Tutorial for Beginners - 2023 Crash Course using Spring Boot 3",
                      "https://youtu.be/UgX5lgv4uVM")
        ));
    }
}
