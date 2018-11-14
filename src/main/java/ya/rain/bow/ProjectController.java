package ya.rain.bow;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;

import ya.rain.bow.beans.GoogleCalendarService;
import ya.rain.bow.beans.PercentCalculation;
import ya.rain.bow.dtos.DepartmentDto;
import ya.rain.bow.dtos.MemberDto;
import ya.rain.bow.dtos.BookingResourceDto;
import ya.rain.bow.dtos.CalendarDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.dtos.ProjectDto;
import ya.rain.bow.model.service.ProjectService;

@Controller
public class ProjectController {
	
	private Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	private ProjectService projectService;
	
	// 프로젝트 메인 이동
	@RequestMapping(value="/projectMain.do", method={RequestMethod.GET, RequestMethod.POST})
	public String projectMain(Model model, HttpSession session, PagingDto p) {
		logger.info("projectMain : " + new Date());
		
		String dept_no = ((MemberDto) session.getAttribute("memDto")).getDept_no();
		DepartmentDto deparDto = new DepartmentDto();
		p.setDept_no(dept_no);
		deparDto.setDept_no(dept_no);
		List<ProjectDto> prjLists = null;
		if (p.getDept_no().charAt(0) == 'C') {
			prjLists = projectService.selectListPrjClass(p);
		} else {
			prjLists = projectService.selectListPrjTeam(p);
		}
		for (int i = 0; i < prjLists.size(); i++) {
			prjLists.get(i).setPrjs_term(new PercentCalculation(projectService.selectListProjectSchdule(prjLists.get(i).getPrj_no())).getTermLine());
		}
		deparDto = projectService.selectOneSchdulecode(deparDto);
		List<BookingResourceDto> bkrLists = projectService.selectListBookingresource();
		model.addAttribute("prjLists", prjLists);
		model.addAttribute("deparDto", deparDto);
		model.addAttribute("bkrLists", bkrLists);
		return "project/projectMain";
	}
	
	// 프로젝트 목록 이동
	@RequestMapping(value="/projectList.do", method={RequestMethod.GET, RequestMethod.POST})
	public String projectList(Model model, HttpSession session, PagingDto p) {
		logger.info("projectList : " + new Date());

		String dept_no = ((MemberDto) session.getAttribute("memDto")).getDept_no();
		p.setDept_no(dept_no);
		List<ProjectDto> prjLists = null;
		if(p.getDept_no().charAt(0) == 'C') {
			p.setTotal(projectService.selectOnePrjCntClass(p));
			prjLists = projectService.selectListPrjClass(p);
		}else {
			p.setTotal(projectService.selectOnePrjCntTeam(p));
			prjLists = projectService.selectListPrjTeam(p);
		}
		for(int i=0; i<prjLists.size(); i++) {
			prjLists.get(i).setPrjs_term(new PercentCalculation(projectService.selectListProjectSchdule(prjLists.get(i).getPrj_no())).getTermLine());
		}
		model.addAttribute("p", p);
		model.addAttribute("prjLists", prjLists);
		return "project/projectList";
	}
	
	// 프로젝트 생성 처리
	@RequestMapping(value="/insertProject.do", method=RequestMethod.POST)
	public String insertProject(ProjectDto prjDto) {
		logger.info("insertProject : " + new Date());
		
		projectService.insertProject(prjDto);
		return "redirect:/projectList.do";
	}
	
	// 프로젝트 삭제 처리
	@RequestMapping(value="/updateDelProject.do", method=RequestMethod.POST)
	public String updateDelProject(HttpServletRequest request) {
		logger.info("updateDelProject : " + new Date());
		
		String[] chkVal = request.getParameterValues("chkVal");
		projectService.updateDelProject(chkVal);
		return "redirect:/projectList.do";
	}
	
	// 프로젝트일정 상세 이동
	@RequestMapping(value="/projectSchdule.do", method=RequestMethod.POST)
	public String projectSchdule(Model model, String prj_no, HttpSession session) {
		logger.info("projectSchdule : " + new Date());
		
		ProjectDto prjDto = projectService.selectOneProject(Integer.parseInt(prj_no));
		model.addAttribute("prjDto", prjDto);
		return "project/projectSchdule";
	}
	
	// 일정 이동
	@RequestMapping(value="/calendarSchdule.do")
	public String calendarSchdule(Model model, HttpSession session) {
		logger.info("calendarSchdule : " + new Date());
		
		String dept_no = ((MemberDto) session.getAttribute("memDto")).getDept_no();
		DepartmentDto deparDto = new DepartmentDto();
		deparDto.setDept_no(dept_no);
		deparDto = projectService.selectOneSchdulecode(deparDto);
		model.addAttribute("deparDto", deparDto);
		return "project/calendarSchdule";
	}
	
	// 회의실 예약 이동
	@RequestMapping(value="/meetingRoom.do", method=RequestMethod.POST)
	public String meetingRoom(Model model, BookingResourceDto bkrDto) {
		logger.info("meetingRoom : " + new Date());
		
		model.addAttribute("bkrDto", projectService.selectOneBookingresource(bkrDto.getBr_no()));
		return "project/meetingRoom";
	}
	
	// 캘린더 리스트 이동
	@RequestMapping(value="/calendarList.do")
	public String calendarList(Model model) {
		logger.info("calendarList : " + new Date());
		
		List<DepartmentDto> deparLists = projectService.selectListDepartmentClass();
		try {
			Calendar service = GoogleCalendarService.getCalendarService();
			String pageToken = null;
			CalendarList calendarList = service.calendarList().list().setPageToken(pageToken).execute();
			List<CalendarListEntry> items = calendarList.getItems();
			model.addAttribute("items", items);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("deparLists", deparLists);
		return "project/calendarList";
	}
	
	// 캘린더 생성 처리
	@RequestMapping(value="/calendarAdd.do", method=RequestMethod.POST)
	public String calendarAdd(CalendarDto gcDto, String listCtgr) {
		logger.info("calendarAdd : " + new Date());
		
		try {
			Calendar service = GoogleCalendarService.getCalendarService();
			com.google.api.services.calendar.model.Calendar calendar = new com.google.api.services.calendar.model.Calendar();
			calendar.setSummary(gcDto.getSummary());
			calendar.setTimeZone("America/Los_Angeles");
			service.calendars().insert(calendar).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(listCtgr.equals("0")) {
			return "redirect:/calendarList.do";
		}else {
			return "redirect:/bookingResourceList.do";
		}
	}
	
	// 캘린더 삭제 처리
	@RequestMapping(value="/calendarRemove.do", method=RequestMethod.POST)
	public String calendarRemove(HttpServletRequest req, String listCtgr) {
		logger.info("calendarRemove : " + new Date());
		
		String[] chkVal = req.getParameterValues("chkVal");
		try {
			Calendar service = GoogleCalendarService.getCalendarService();
			for (String calendarId : chkVal) {
				service.calendars().delete(calendarId).execute();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(listCtgr.equals("0")) {
			return "redirect:/calendarList.do";
		}else {
			return "redirect:/bookingResourceList.do";
		}
	}
	
	// 캘린더 수정 처리
	@RequestMapping(value="/calendarModify.do", method=RequestMethod.POST)
	public String calendarModify(CalendarDto gcDto, String listCtgr) {
		logger.info("calendarModify : " + new Date());
		
		try {
			Calendar service = GoogleCalendarService.getCalendarService();
			com.google.api.services.calendar.model.Calendar calendar = service.calendars().get(gcDto.getSchc_code()).execute();
			calendar.setSummary(gcDto.getSummary());
		    service.calendars().update(calendar.getId(), calendar).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(listCtgr.equals("0")) {
			return "redirect:/calendarList.do";
		}else {
			return "redirect:/bookingResourceList.do";
		}
	}
	
	// 캘린더 코드 등록 처리
	@RequestMapping(value="/updateSchdulecode.do", method=RequestMethod.POST)
	public String updateSchdulecode(DepartmentDto deparDto) {
		logger.info("updateSchdulecode : " + new Date());
		
		projectService.updateSchdulecode(deparDto);
		return "redirect:/calendarList.do";
	}
	
	
	// 예약목록 리스트 이동
	@RequestMapping(value="/bookingResourceList.do")
	public String bookingResourceList(Model model) {
		logger.info("bookingResourceList : " + new Date());
		
		List<BookingResourceDto> bkrLists = projectService.selectListBookingresource();
		try {
			Calendar service = GoogleCalendarService.getCalendarService();
			String pageToken = null;
			CalendarList calendarList = service.calendarList().list().setPageToken(pageToken).execute();
			List<CalendarListEntry> items = calendarList.getItems();
			model.addAttribute("items", items);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("bkrLists", bkrLists);
		return "project/bookingResourceList";
	}
	
	// 예약목록 생성 처리
	@RequestMapping(value="/insertBookingResource.do", method=RequestMethod.POST)
	public String insertBookingResource(BookingResourceDto bkrDto) {
		logger.info("insertBookingResource : " + new Date());
		
		projectService.insertBookingresource(bkrDto);
		return "redirect:/bookingResourceList.do";
	}
	
	// 예약목록 삭제 처리
	@RequestMapping(value="/deleteBookingResource.do", method=RequestMethod.POST)
	public String deleteBookingResource(HttpServletRequest req) {
		logger.info("deleteBookingResource : " + new Date());
		
		String[] br_nos = req.getParameterValues("chkValBooking");
		projectService.deleteBookingresource(br_nos);
		return "redirect:/bookingResourceList.do";
	}
	
	// 예약목록 수정 처리
	@RequestMapping(value="/updateBookingResource.do", method=RequestMethod.POST)
	public String updateBookingResource(BookingResourceDto bkrDto) {
		logger.info("updateBookingResource : " + new Date());
		
		projectService.updateBookingresource(bkrDto);
		return "redirect:/bookingResourceList.do";
	}
	
	// 예약목록 캘린더 코드 등록 처리
	@RequestMapping(value="/updateBookingResouceCode.do", method=RequestMethod.POST)
	public String updateBookingResouceCode(BookingResourceDto bkrDto) {
		logger.info("updateBookingResouceCode : " + new Date());
		
		projectService.updateBookingresourceCode(bkrDto);
		return "redirect:/bookingResourceList.do";
	}
	
	
	// 예약목록 리스트 이동
	@RequestMapping(value="/meetingRoomList.do")
	public String meetingRoomList(Model model) {
		logger.info("meetingRoomList : " + new Date());
		
		List<BookingResourceDto> bkrLists = projectService.selectListBookingresource();
		model.addAttribute("bkrLists", bkrLists);
		return "project/meetingRoomList";
	}
	
}
