package uo.ri.cws.application.repository;

import java.util.List;

import uo.ri.cws.domain.Course;

public interface CourseRepository extends Repository<Course> {

    /**
     * @return a list with all mechanics (might be empty)
     */
    List<Course> findAll();

}
