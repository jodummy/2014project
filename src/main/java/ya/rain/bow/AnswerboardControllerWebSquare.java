package ya.rain.bow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ya.rain.bow.dtos.AnswerboardDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.model.service.AnswerboardService;

@Controller
public class AnswerboardControllerWebSquare {
	
	private Logger logger = LoggerFactory.getLogger(AnswerboardControllerWebSquare.class);
	
	@Autowired
	private AnswerboardService abService;
	
	// 글쓰기 처리
	@RequestMapping(value="/info/insertAnswerboard.do", method=RequestMethod.POST)
	public void insertAnswerboard(@RequestBody Map<String, Object> map){
		logger.info("insertAnswerboard : " + map.toString());
		abService.insertAnswerboard(map);
	}
	
	// 리스트처리
	@RequestMapping(value="/info/selectListAnswerboard.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectListAnswerboard(@RequestBody Map<String, Object> map){
		logger.info("selectListAnswerboard : " + map.toString());
		Map<String, Object> reMap = new HashMap<String, Object>();
		PagingDto paging = new PagingDto();
		Map<String, Object> parMap = (Map<String, Object>) map.get("paging");
		paging.setIndex(Integer.parseInt(parMap.get("index").toString()));
		List<AnswerboardDto> lists = abService.selectListAnswerboard(paging);
		for (AnswerboardDto abDto : lists) {
			if(abDto.getAb_delflag().equalsIgnoreCase("Y")) {
				abDto.setAb_title("-");
				abDto.setAb_name("-");
				abDto.setAb_readcount(0);
				abDto.setAb_regdate("-");
			}else {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < abDto.getAb_depth(); i++) {
					if(i==0) {
						sb.append("└");
					}else {
						sb.append("─");
					}
				}
				sb.append(abDto.getAb_title());
				abDto.setAb_title(sb.toString());
			}
		}
		reMap.put("abLists", lists);
		paging.setTotal(abService.selectOneCntAnswerboard());
		map = new HashMap<String, Object>();
		map.put("index", (paging.getIndex()+1));
		map.put("pageTotal", Math.ceil((double)paging.getTotal()/paging.getListCnt()));
		reMap.put("paging", map);
		return reMap;
	}
	
	// 상세보기
	@RequestMapping(value="/info/selectOneAnswerboard.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectOneAnswerboard(@RequestBody Map<String, Object> map){
		logger.info("selectOneAnswerboard : " + map.toString());
		Map<String, Object> reMap = new HashMap<String, Object>();
		AnswerboardDto abDto = abService.selectOneAnswerboard(map);
		if(abDto.getAb_delflag().equalsIgnoreCase("Y")) {
			abDto.setAb_regdate("-");
			abDto.setAb_readcount(0);
			abDto.setAb_name("-");
			abDto.setAb_title("-");
			abDto.setAb_content("-");
		}
		reMap.put("detail", abDto);
		return reMap;
	}
	
	// 비밀번호확인
	@RequestMapping(value="/info/selectOneChkAnswerboard.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectOneChkAnswerboard(@RequestBody Map<String, Object> map) {
		logger.info("selectOneChkAnswerboard : " + map.toString());
		Map<String, Object> reMap = new HashMap<String, Object>();
		reMap.put("chk", abService.selectOneChkAnswerboard(map));
		map = new HashMap<String, Object>();
		map.put("reChk", reMap);
		return map;
	}
	
	// 글수정처리
	@RequestMapping(value="/info/updateAnswerboardModify.do", method=RequestMethod.POST)
	public void updateAnswerboardModify(@RequestBody Map<String, Object> map) {
		logger.info("updateAnswerboardModify : " + map.toString());
		abService.updateAnswerboardModify(map);
	}	
	
	// 삭제delflag처리
	@RequestMapping(value="/info/updateDelAnswerboard.do", method=RequestMethod.POST)
	public void updateDelAnswerboard(@RequestBody Map<String, Object> map) {
		logger.info("updateDelAnswerboard : " + map.toString());
		abService.updateDelAnswerboard(map);
	}
	
	// 답글쓰기
	@RequestMapping(value="/info/insertReplyboard.do", method=RequestMethod.POST)
	public void insertReplyboard(@RequestBody Map<String, Object> map) {
		logger.info("insertReplyboard : " + map.toString());
		abService.insertReplyboard(map);
	}	
	
}
