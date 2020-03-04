package uo.ri.cws.application.service.training.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.dto.CourseDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;

public class FindAllActiveCourses implements Command<List<CourseDto>> {

    private CourseRepository repo = Factory.repository.forCourse();

    @Override
    public List<CourseDto> execute() throws BusinessException {
	List<Course> courses = repo.findAll();
	return DtoAssembler.toCourseDtoList(courses);
    }

}
