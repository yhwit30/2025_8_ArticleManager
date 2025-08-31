package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Article> articleList = new ArrayList<>();
    static List<Member> memberList = new ArrayList<>();

    public static void main(String[] args) {

        makeTestData();

        Scanner sc = new Scanner(System.in);

        System.out.println("== 프로그램 시작 ==");

        int lastId = 3;
        int lastMemberId = 0;


        while (true) {

            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력하세요");
                continue;
            }
            if (cmd.equals("exit")) {
                break;
            }
            if (cmd.equals("member list")) {
                for (Member member : memberList) {
                    System.out.println("member" + member.toString());
                }
            } else if (cmd.equals("member join")) {
                System.out.println("==회원가입==");
                int id = lastMemberId + 1;
                System.out.print("아이디 : ");
                String loginId = sc.nextLine().trim();
                System.out.print("비밀번호 : ");
                String loginPw = sc.nextLine().trim();
                System.out.print("이름 : ");
                String name = sc.nextLine().trim();
                String regDate = Util.getNowStr();
                String updateDate = Util.getNowStr();

                Member member = new Member(id, loginId, loginPw, name, regDate, updateDate);

                memberList.add(member);

                System.out.println(id + "번 회원이 등록되었습니다.(개발자용)");
                System.out.println(name + "회원님 등록을 환영합니다.");

                lastMemberId++;
            } else if (cmd.equals("article write")) {
                System.out.println("==게시글 작성==");
                int id = lastId + 1;
                System.out.print("제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("내용 : ");
                String body = sc.nextLine().trim();
                String regDate = Util.getNowStr();
                String updateDate = Util.getNowStr();

                articleList.add(new Article(id, title, body, regDate, updateDate));
                System.out.println(id + "번 게시글이 작성되었습니다.");
                lastId++;

            } else if (cmd.startsWith("article list")) {
                System.out.println("==게시글 목록==");
                System.out.println("번호   /   제목   /   내용   /   등록일자");
                if (articleList.size() == 0) {
                    System.out.println("게시글 없습니다.");
                    continue;
                }

                String searchKeyword = cmd.substring("article list".length()).trim();

                List<Article> forPrintArticles = articleList;
                if (searchKeyword.length() > 0) {
                    forPrintArticles = new ArrayList<>();
                    System.out.println("검색어 : " + searchKeyword);
                    for (Article article : articleList) {
                        if (article.getTitle().contains(searchKeyword)) {
                            forPrintArticles.add(article);
                        }
                    }
                    if (forPrintArticles.size() == 0) {
                        System.out.println("검색결과 없음");
                        continue;
                    }
                }

                for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
                    System.out.printf("%d   /   %s    /   %s   /   %s\n", forPrintArticles.get(i).getId(), forPrintArticles.get(i).getTitle(), forPrintArticles.get(i).getBody(), forPrintArticles.get(i).getRegDate());
                }

            } else if (cmd.startsWith("article detail")) {
                System.out.println("==게시글 상세보기==");
                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticleById(id);
                if (foundArticle == null) {
                    System.out.println(id + "번 게시글은 없습니다.");
                }

                System.out.println("번호 : " + foundArticle.getId());
                System.out.println("등록일 : " + foundArticle.getRegDate());
                System.out.println("수정일 : " + foundArticle.getUpdateDate());
                System.out.println("제목 : " + foundArticle.getTitle());
                System.out.println("내용 : " + foundArticle.getBody());

            } else if (cmd.startsWith("article delete")) {
                System.out.println("==게시글 삭제==");
                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticleById(id);
                if (foundArticle == null) {
                    System.out.println(id + "번 게시글은 없습니다.");
                }

                articleList.remove(foundArticle);
                System.out.println(id + "번 게시글이 삭제되었습니다.");
            } else if (cmd.startsWith("article modify")) {
                System.out.println("==게시글 수정==");
                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticleById(id);
                if (foundArticle == null) {
                    System.out.println(id + "번 게시글은 없습니다.");
                }

                System.out.println("기존 제목 : " + foundArticle.getTitle());
                System.out.println("기존 내용 : " + foundArticle.getBody());
                System.out.print("새 제목 : ");
                String newTitle = sc.nextLine().trim();
                System.out.print("새 내용 : ");
                String newBody = sc.nextLine().trim();

                foundArticle.setTitle(newTitle);
                foundArticle.setBody(newBody);
                foundArticle.setUpdateDate(Util.getNowStr());
                System.out.println(id + "번 게시글이 수정되었습니다.");

            } else {
                System.out.println("사용할 수 없는 명령어입니다.");
            }
        }
        System.out.println("프로그램 종료");
        sc.close();
    }

    private static Article getArticleById(int id) {
        for (Article article : articleList) {
            if (article.getId() == id) {
                return article;
            }
        }
        System.out.println("해당 게시글은 없습니다.");
        return null;

    }

    private static void makeTestData() {
        System.out.println("테스트 데이터 생성됨");
        articleList.add(new Article(1, "제목1", "내용1", "2025-8-20 12:12:12", "2025-9-1 12:30:30"));
        articleList.add(new Article(2, "제목2", "내용2", "2025-8-26 12:23:12", Util.getNowStr()));
        articleList.add(new Article(3, "제목3", "내용3", "2025-8-27 12:12:44", Util.getNowStr()));
    }
}

class Member {
    private int id;
    private String loginId;
    private String loginPw;
    private String name;
    private String regDate;
    private String updateDate;

    public Member(int id, String loginId, String loginPw, String name, String regDate, String updateDate) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.regDate = regDate;
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", loginPw='" + loginPw + '\'' +
                ", name='" + name + '\'' +
                ", regDate='" + regDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Article {
    private int id;
    private String title;
    private String body;
    private String regDate;
    private String updateDate;

    public Article(int id, String title, String body, String regDate, String updateDate) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.regDate = regDate;
        this.updateDate = updateDate;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}