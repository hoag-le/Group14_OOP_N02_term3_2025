package com.example.servingwebcontent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servingwebcontent.database.MemberDao;
import com.example.servingwebcontent.models.Member;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberDao memberDao;

    @GetMapping
    public List<Member> allMembers() {
        return memberDao.findAll();
    }

    @PostMapping
    public String addMember(@RequestBody Member member) {
        memberDao.save(member);
        return "ok";
    }
}