package com.cafe24.springmvcstudy.regist;

import com.cafe24.springmvcstudy.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/regist")
@Controller
public class RegistController {

    private final MemberRepository memberRepository;
    private final RegistService registService;
    private final ModelMapper modelMapper;

    @GetMapping("/step1")
    public String handleStep1() {
        return ("register/step1");
    }

    @GetMapping("/step2")
    public String handleStep2Get(MemberDto memberDto) {
        return "register/step2";
    }

    @PostMapping("/step2")
    public String handleStep2(@Valid MemberDto memberDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!memberDto.getPassword().equals(memberDto.getConfirmPassword())) {
            FieldError fieldError = new FieldError("memberDto", "confirmPassword", "암호가 일치하지 않습니다");
            result.addError(fieldError);
        }

        if (result.hasErrors()) {
            return "register/step2";
        }

        memberRepository.save(memberDto.toEntity());

        return "redirect:/regist/step3";
    }

    @GetMapping("/step3")
    public String handleStep3() {
        return "register/step3";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<MemberDto> memberList = memberRepository.findAll().stream().map(member -> modelMapper.map(member, MemberDto.class)).collect(Collectors.toList());
        model.addAttribute("memberList", memberList);
        return "register/list";
    }
}
