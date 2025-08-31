package org.example.controller;

import org.example.dto.Member;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {

    private List<Member> memberList = new ArrayList<>();
    private int lastMemberId = 3;
    private Scanner sc;
    private String cmd;

    public MemberController(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "join":
                doJoin();
                break;
            case "list":
                showList();
                break;
        }

    }


    public void showList() {
        for (Member member : memberList) {
            System.out.println(member.toString());
        }
    }

    public void doJoin() {
        System.out.println("==회원가입==");
        int id = lastMemberId + 1;

        String loginId = null;
        while (true) {
            System.out.print("아이디 : ");
            loginId = sc.nextLine().trim();

            if (!isJoinableLoginId(loginId)) {
                System.out.println(loginId + "는 사용할 수 없는 아이디입니다.");
                continue;
            }
            break;
        }

        String loginPw = null;
        while (true) {
            System.out.print("비밀번호 : ");
            loginPw = sc.nextLine().trim();
            System.out.print("비밀번호 : ");
            String loginPwCheck = sc.nextLine().trim();

            if (!loginPw.equals(loginPwCheck)) {
                System.out.println("비밀번호를 확인하세요.");
                continue;
            }
            break;
        }
        System.out.print("이름 : ");
        String name = sc.nextLine().trim();
        String regDate = Util.getNowStr();
        String updateDate = Util.getNowStr();

        Member member = new Member(id, loginId, loginPw, name, regDate, updateDate);

        memberList.add(member);

        System.out.println(id + "번 회원이 등록되었습니다.(개발자용)");
        System.out.println(name + "회원님 등록을 환영합니다.");

        lastMemberId++;
    }

    private boolean isJoinableLoginId(String loginId) {
        for (Member member : memberList) {
            if (member.getLoginId().equals(loginId)) {
                return false;
            }
        }
        return true;
    }

    public void makeMemberTestData() {
        System.out.println("회원 테스트 데이터 생성됨");
        memberList.add(new Member(1, "test1", "test1", "testName1", "2025-7-5", Util.getNowStr()));
        memberList.add(new Member(2, "test2", "test2", "testName2", Util.getNowStr(), Util.getNowStr()));
        memberList.add(new Member(3, "test3", "test3", "testName3", Util.getNowStr(), Util.getNowStr()));
    }

}
