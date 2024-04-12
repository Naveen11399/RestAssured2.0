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

	public static String Upload_FileTeacher_URL = Base_URL + "/teacher/upload-staff-details";//?path=staff-management/imports/

	public static String ViewFile_Teacher_URL = Base_URL + "/teacher/view-staff-file-info";//?fileName={{Bulk_fileName}}&filePath={{Bulk_filePath}}

	public static String ImportStaff_URL = Base_URL + "/teacher/import-teacher";
	
	public static String getStaffBulk_URL = Base_URL + "/teacher/get-teacher/{StaffId}";

	public static String UpdateStaffBulk_URL = Base_URL + "/teacher/update-teacher/{StaffId}";

	public static String DeleteStaffBulk_URL = Base_URL + "/teacher/delete-teacher/{StaffId}";// {userId}
	
	
	

}
