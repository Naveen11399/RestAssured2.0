package api.endPoints;

public class Routes {

	public static String Base_URL = "https://dev-schoolapi.izome.in/api";

	public static String Post_URL = Base_URL + "/auth/login";

	public static String GET_Std_URL = Base_URL + "/student/get-student/38";

	public static String Create_Std_URL = Base_URL + "/student/create-student";

	public static String Get_Created_Std_URL = Base_URL + "/student/get-student/{studentId}";

	public static String Delete_Std_URL = Base_URL + "/student/delete-student/{studentId}";

	public static String Std_Bulk_URL = Base_URL + "/student/upload-student-details";

	public static String View_Std_BulK_URL = Base_URL + "/student/view-file-info";

	public static String Add_Std_BulK_URL = Base_URL + "/student/add-student-excel";

	// Staff creation

	public static String uploadTimeTable_URL = Base_URL + "/media/upload-raw-file";

	public static String uploadProfile_Happening_URL = Base_URL + "/media/upload-file";

	public static String uploadProfile_teacher_URL = Base_URL + "/media/upload-file";

	public static String Create_teacherAPI_URL = Base_URL + "/teacher/create-teacher";

	public static String GetStaff_Details_URL = Base_URL + "/teacher/get-teacher/{userId}";// {userId}

	public static String Update_Staff_URL = Base_URL + "/teacher/update-teacher/{userId}";

	public static String Delete_Staff_URL = Base_URL + "/teacher/delete-teacher/{userId}";

	// Staff Bulk Import

	public static String Upload_FileTeacher_URL = Base_URL + "/teacher/upload-staff-details";// ?path=staff-management/imports/

	public static String ViewFile_Teacher_URL = Base_URL + "/teacher/view-staff-file-info";// ?fileName={{Bulk_fileName}}&filePath={{Bulk_filePath}}

	public static String ImportStaff_URL = Base_URL + "/teacher/import-teacher";

	public static String getStaffBulk_URL = Base_URL + "/teacher/get-teacher/{StaffId}";

	public static String UpdateStaffBulk_URL = Base_URL + "/teacher/update-teacher/{StaffId}";

	public static String DeleteStaffBulk_URL = Base_URL + "/teacher/delete-teacher/{StaffId}";// {userId}
	
	//Marks URL 
	
	public static String GetExam_URL = Base_URL + "/exam-report/exam-report-subject-list";// ? //gradeId=3&sectionId=1&examId=4&type=2

	public static String DownloadFile_URL = Base_URL + "/exam-report/upload-bulk";

	public static String MarkUpload_URL = Base_URL + "/exam-report/upload-exam-report";

	public static String Excelinfo_URL = Base_URL + "/exam-report/view-subjects-info" ; //?gradeId={{gradeId}}&sectionId={{sectionId}}&fileName={{fileName}}&filePath={{filePath}}";

	public static String BulkMarkImport_URL = Base_URL + "/exam-report/bulk-exam-report";

	public static String GetSubjectlist_URL = Base_URL + "/exam-report/exam-report-subject-list?gradeId={{gradeId}}&sectionId={{sectionId}}&examId={{examId}}&type={{type}}";// {userId}
   

	public static String ExamDetails_URL = Base_URL + "/exam-report/get-exam-report/{{reportId}}";

	public static String DeleteMarks_URL = Base_URL + "/exam-report/delete-student-exam-report/{{ExamMarksId}}";// {userId}
	
}
