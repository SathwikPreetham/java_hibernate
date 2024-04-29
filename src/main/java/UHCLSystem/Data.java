package UHCLSystem;

import java.util.ArrayList;

public interface Data {
	
	uhcluser login(String id, String psw);
	void registerCourse(String sid, String cid);
	ArrayList<String> getMyCourses(String sid);
	ArrayList<String> getOpenCourses(String sid);
	ArrayList<String> getMyTeachingCourses(String sid);
	ArrayList<String> getEnrolledStudentNames(String cid);
	void writeNote(String writer, String content, String course, String datetime);
	ArrayList<coursenote> getCourseNotes(String course);

}
