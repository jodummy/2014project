package ya.rain.bow;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

import ya.rain.bow.beans.GoogleCalendarService;
import ya.rain.bow.beans.ProjectSchduleBarChart;
import ya.rain.bow.beans.ProjectSchdulePieChart;
import ya.rain.bow.beans.TermAnalsis;
import ya.rain.bow.dtos.DepartmentDto;
import ya.rain.bow.dtos.MemberDto;
import ya.rain.bow.dtos.BookingResourceDto;
import ya.rain.bow.dtos.CalendarDto;
import ya.rain.bow.dtos.ProjectDto;
import ya.rain.bow.dtos.ProjectSchduleDto;
import ya.rain.bow.model.service.ProjectService;

@Controller
public class ProjectAjaxController {

	private Logger logger = LoggerFactory.getLogger(ProjectAjaxController.class);

	@Autowired
	private ProjectService projectService;
	
	// 프로젝트일정 상세 데이터 처리
	@RequestMapping(value = "/selectListProjectSchdule.do", method = RequestMethod.POST)
	@ResponseBody
	public List<ProjectSchduleDto> selectListProjectSchdule(String prj_no) {
		logger.info("selectListProjectSchdule : " + new Date());
		
		List<ProjectSchduleDto> prjsLists = projectService.selectListProjectSchdule(Integer.parseInt(prj_no));
		return prjsLists;
	}
	
	// 프로젝트일정 저장 처리
	@RequestMapping(value = "/insertProjectSchdule.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> insertProjectSchdule(HttpServletRequest req, ProjectDto prjDto) {
		logger.info("insertProjectSchdule : " + new Date());
		
		boolean isc = projectService.updateProject(prjDto);
		String[] prjs_termsBig = req.getParameterValues("inPrjs_term");
		if(prjs_termsBig != null) {
			String[] prjs_terms = new TermAnalsis(prjDto, prjs_termsBig).getTermsFormat();
			List<ProjectSchduleDto> lists = new ArrayList<ProjectSchduleDto>();
			for (int i = 0; i < prjs_terms.length; i++) {
				ProjectSchduleDto prjsDto = new ProjectSchduleDto(	prjDto.getPrj_no(),
																	req.getParameterValues("inPrjs_item")[i],
																	req.getParameterValues("inPrjs_name")[i],
																	Integer.parseInt(req.getParameterValues("inPrjs_refer")[i]),
																	Integer.parseInt(req.getParameterValues("inPrjs_step")[i]),
																	Integer.parseInt(req.getParameterValues("inPrjs_depth")[i]),
																	prjs_terms[i]);
				lists.add(prjsDto);
			}
			isc = projectService.insertProjectSchdule(lists);
		}else {
			isc = projectService.deleteProjectSchdule(prjDto.getPrj_no());
		}
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("isc", isc);
		return map;
	}
	
	// pie 차트 데이터 처리
	@RequestMapping(value="/projectSchdulePie.do", method=RequestMethod.POST)
	@ResponseBody
	public List<ProjectSchduleDto> selectListProjectSchdulePie(String prj_no) {
		logger.info("selectListProjectSchdulePie : " + new Date());
		
		return new ProjectSchdulePieChart(projectService.selectListProjectSchdule(Integer.parseInt(prj_no))).getPieChart();
	}
	
	// bar 차트 데이터 처리
	@RequestMapping(value="/projectSchduleBar.do", method=RequestMethod.POST)
	@ResponseBody
	public List<ProjectSchduleDto> selectListProjectSchduleBar(String prj_no) {
		logger.info("selectListProjectSchduleBar : " + new Date());
		
		return new ProjectSchduleBarChart(projectService.selectListProjectSchdule(Integer.parseInt(prj_no))).getBarChart();
	}
	
	// 일정 데이터 처리
	@RequestMapping(value="/calendarEventList.do")
	@ResponseBody
	public List<Event> calendarEventList(HttpSession session) {
		logger.info("calendarEventList : " + new Date());
		
		String dept_no = ((MemberDto) session.getAttribute("memDto")).getDept_no();
		DepartmentDto deparDto = new DepartmentDto();
		deparDto.setDept_no(dept_no);
		deparDto = projectService.selectOneSchdulecode(deparDto);
		List<Event> items = new ArrayList<Event>();
		try {
			Calendar service = GoogleCalendarService.getCalendarService();
			Events events = service.events().list(deparDto.getSchc_code()).setOrderBy("startTime").setSingleEvents(true).execute();
			items = events.getItems();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	// 일정 저장 처리
	@RequestMapping(value="/calendarEventAdd.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> calendarEventAdd(CalendarDto gcDto, HttpSession session) {
		logger.info("calendarEventAdd : " + new Date());
		
		String dept_no = ((MemberDto) session.getAttribute("memDto")).getDept_no();
		DepartmentDto deparDto = new DepartmentDto();
		deparDto.setDept_no(dept_no);
		deparDto = projectService.selectOneSchdulecode(deparDto);
		boolean isc = false;
		try {
			Calendar service = GoogleCalendarService.getCalendarService();
			Event event = new Event().setSummary(gcDto.getSummary()).setDescription(gcDto.getDescription());
			//시작일
			DateTime startDateTime = new DateTime(gcDto.getStartDateTime());
			EventDateTime start = new EventDateTime().setDateTime(startDateTime).setTimeZone("America/Los_Angeles");
			event.setStart(start);
			//종료일
			DateTime endDateTime = new DateTime(gcDto.getEndDateTime());
			EventDateTime end = new EventDateTime().setDateTime(endDateTime).setTimeZone("America/Los_Angeles");
			event.setEnd(end);
			event = service.events().insert(deparDto.getSchc_code(), event).execute();
			isc = true;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("isc", isc);
		return map;
	}
	
	// 일정 삭제
	@RequestMapping(value="/calendarEventRemoveOne.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> calendarEventRemoveOne(CalendarDto gcDto, HttpSession session) {
		logger.info("calendarEventRemoveOne : " + new Date());
		
		String dept_no = ((MemberDto) session.getAttribute("memDto")).getDept_no();
		DepartmentDto deparDto = new DepartmentDto();
		deparDto.setDept_no(dept_no);
		deparDto = projectService.selectOneSchdulecode(deparDto);
		boolean isc = false;
		try {
			Calendar service = GoogleCalendarService.getCalendarService();
			service.events().delete(deparDto.getSchc_code(), gcDto.getEventId()).execute();
			isc = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("isc", isc);
		return map;
	}
	
	// 일정 수정
	@RequestMapping(value="/calendarEventModify.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> calendarEventModify(CalendarDto gcDto, HttpSession session) {
		logger.info("calendarEventModify : " + new Date());
		
		String dept_no = ((MemberDto) session.getAttribute("memDto")).getDept_no();
		DepartmentDto deparDto = new DepartmentDto();
		deparDto.setDept_no(dept_no);
		deparDto = projectService.selectOneSchdulecode(deparDto);
		boolean isc = false;
		try {
			Calendar service = GoogleCalendarService.getCalendarService();
			Event event = service.events().get(deparDto.getSchc_code(), gcDto.getEventId()).execute();
			event.setSummary(gcDto.getSummary()).setDescription(gcDto.getDescription());
			service.events().update(deparDto.getSchc_code(), event.getId(), event).execute();
			isc = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("isc", isc);
		return map;
	}
	
	
	// 회의실 예약 데이터 처리
	@RequestMapping(value="/meetingRoomEventList.do", method=RequestMethod.POST)
	@ResponseBody
	public List<Event> meetingRoomEventList(String br_no) {
		logger.info("meetingRoomEventList : " + new Date());
		
		BookingResourceDto bkr = projectService.selectOneBookingresource(Integer.parseInt(br_no));
		List<Event> items = new ArrayList<Event>();
		try {
			Calendar service = GoogleCalendarService.getCalendarService();
			Events events = service.events().list(bkr.getSchc_code()).setOrderBy("startTime").setSingleEvents(true).execute();
			items = events.getItems();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	// 회의실 예약 저장 처리
	@RequestMapping(value="/meetingRoomEventAdd.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> meetingRoomEventAdd(CalendarDto gcDto, String br_no) {
		logger.info("meetingRoomEventAdd : " + new Date());
		
		BookingResourceDto bkr = projectService.selectOneBookingresource(Integer.parseInt(br_no));
		boolean isc = false;
		try {
			Calendar service = GoogleCalendarService.getCalendarService();
			Event event = new Event().setSummary(gcDto.getSummary()).setDescription(gcDto.getDescription());
			//시작일
			DateTime startDateTime = new DateTime(gcDto.getStartDateTime());
			EventDateTime start = new EventDateTime().setDateTime(startDateTime).setTimeZone("America/Los_Angeles");
			event.setStart(start);
			//종료일
			DateTime endDateTime = new DateTime(gcDto.getEndDateTime());
			EventDateTime end = new EventDateTime().setDateTime(endDateTime).setTimeZone("America/Los_Angeles");
			event.setEnd(end);
			event = service.events().insert(bkr.getSchc_code(), event).execute();
			isc = true;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("isc", isc);
		return map;
	}
	
	// 회의실 예약 삭제
	@RequestMapping(value="/meetingRoomEventRemoveOne.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> meetingRoomEventRemoveOne(CalendarDto gcDto, String br_no) {
		logger.info("meetingRoomEventRemoveOne : " + new Date());
		
		BookingResourceDto bkr = projectService.selectOneBookingresource(Integer.parseInt(br_no));
		boolean isc = false;
		try {
			Calendar service = GoogleCalendarService.getCalendarService();
			service.events().delete(bkr.getSchc_code(), gcDto.getEventId()).execute();
			isc = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("isc", isc);
		return map;
	}
	
	// 회의실 예약 수정
	@RequestMapping(value="/meetingRoomEventModify.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> meetingRoomEventModify(CalendarDto gcDto, String br_no) {
		logger.info("meetingRoomEventModify : " + new Date());
		
		BookingResourceDto bkr = projectService.selectOneBookingresource(Integer.parseInt(br_no));
		boolean isc = false;
		try {
			Calendar service = GoogleCalendarService.getCalendarService();
			Event event = service.events().get(bkr.getSchc_code(), gcDto.getEventId()).execute();
			event.setSummary(gcDto.getSummary()).setDescription(gcDto.getDescription());
			service.events().update(bkr.getSchc_code(), event.getId(), event).execute();
			isc = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("isc", isc);
		return map;
	}
	
}
