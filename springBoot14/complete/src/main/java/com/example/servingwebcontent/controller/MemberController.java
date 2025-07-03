package com.example.servingwebcontent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(@PathVariable int id, @RequestBody Member member) {
        member.setId(id);
        boolean updated = memberDao.update(member);
        if (updated) {
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable int id) {
        boolean deleted = memberDao.delete(id);
        if (deleted) {
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
    }
}