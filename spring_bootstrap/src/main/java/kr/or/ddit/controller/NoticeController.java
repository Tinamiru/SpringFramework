package kr.or.ddit.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.command.Criteria;
import com.jsp.command.MemberRegistCommand;
import com.jsp.command.NoticeModifyCommand;
import com.jsp.dto.MemberVO;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

import kr.or.ddit.command.MemberModifyCommand;
import kr.or.ddit.command.NoticeRegistCommand;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@GetMapping("list")
	public String list(Criteria cri, HttpServletRequest request) throws Exception {
		String url = "notice/list";

		Map<String, Object> dataMap = noticeService.getNoticeList(cri);

		request.setAttribute("dataMap", dataMap);

		return url;
	}
	
	@GetMapping(value = "/detail")
	public String detail(int nno, Model model) throws Exception {
		String url = "notice/detail";

		NoticeVO notice = noticeService.getNotice(nno);
		model.addAttribute("notice", notice);

		return url;
	}
	
	@GetMapping("/registForm")
	public String registForm() {
		String url = "notice/regist";
		return url;
	}

	@PostMapping(value = "/regist")
	public String regist(NoticeVO notice,RedirectAttributes rttr) throws Exception {
		String url = "redirect:/notice/list";

		noticeService.regist(notice);
		
		rttr.addFlashAttribute("from", "regist");
		
		return url;
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(NoticeModifyCommand NoticeReq, HttpSession session, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/notice/detail.do";
		
		NoticeVO notice = NoticeReq.toNoticeVO();
		
		noticeService.modify(notice);
		
		
		return url;
	}
	
	
	

}

