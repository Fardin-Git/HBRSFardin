package org.hbrs.se1.ws24.exercises.uebung2;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private Member member;
    public void setMember(Member m){
        member = m;
    }
    public Member getMember(){
        return member;
    }

    public void memberViewConnect(List<Member> memberList){
        MemberView.dump(memberList);
    }
}
