package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("== 프로그램 시작 ==");

        int lastId = 0;
        List<Article> articleList = new ArrayList<>();

        while(true){

            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if(cmd.length()== 0){
                System.out.println("명령어를 입력하세요");
                continue;
            }
            if(cmd.equals("exit")){
                break;
            }
            if(cmd.equals("article write")){

            } else if (cmd.equals("article list")) {

            } else if (cmd.equals("article detail")){

            } else if (cmd.startsWith("article delete")){

            } else if(cmd.startsWith("aritlce modify")){

            } else {
                System.out.println("사용할 수 없는 명령어입니다.");
            }
        }
        System.out.println("프로그램 종료");
        sc.close();

    }
}
class Article{
    private int id;
    private String title;
    private String body;

    public Article(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
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