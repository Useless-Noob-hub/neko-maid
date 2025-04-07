package nekomaid.tools;


import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import lombok.RequiredArgsConstructor;
import nekomaid.entity.po.Course;
import nekomaid.entity.po.CourseReservation;
import nekomaid.entity.po.School;
import nekomaid.entity.query.CourseQuery;
import nekomaid.service.ICourseReservationService;
import nekomaid.service.ICourseService;
import nekomaid.service.ISchoolService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;


@RequiredArgsConstructor
@Component
public class CourseTools {

    private final ICourseService courseService;
    private final ISchoolService schoolService;
    private final ICourseReservationService reservationService;

    @Tool(description = "根据条件查询课程")
    public List<Course> queryCourse(@ToolParam(description = "查询的条件") CourseQuery query) {
        if(query == null){
            return courseService.list();
        }
        QueryChainWrapper<Course> wrapper = courseService.query()
                .eq(query.getType() != null, "type", query.getType())//type = "编程"
                .le(query.getEdu() != null, "edu", query.getEdu());//edu <= 3
        if(query.getSorts() != null && !query.getSorts().isEmpty())
        {
            for (CourseQuery.Sort sort : query.getSorts()) {
                wrapper.orderBy(true, sort.getAsc(),sort.getField());
            }

        }

        return wrapper.list();
    }

    @Tool(description = "查询所有校区")
    public List<School> querySchool(){
        return schoolService.list();
    }

    @Tool(description = "生成预约单，返回预约单号")
    public Integer createCourseReservation(
            @ToolParam(description = "预约课程") String course,
            @ToolParam(description = "预约校区") String school,
            @ToolParam(description = "学生姓名") String studentName,
            @ToolParam(description = "联系方式") String contactInfo,
            @ToolParam(description = "备注", required = false) String remark){
        CourseReservation reservation = new CourseReservation();
        reservation.setCourse(course)
                .setSchool(school)
                .setStudentName(studentName)
                .setContactInfo(contactInfo)
                .setRemark(remark);
       reservationService.save(reservation);
        return reservation.getId();
    }
}