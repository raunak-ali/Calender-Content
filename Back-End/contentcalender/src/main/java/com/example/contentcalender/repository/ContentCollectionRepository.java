package com.example.contentcalender.repository;
import java.util.Optional
;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.example.contentcalender.model.Status;
import org.springframework.stereotype.Repository;

import com.example.contentcalender.model.Content;
import com.example.contentcalender.model.Type;
import jakarta.annotation.PostConstruct;

@Repository
public class ContentCollectionRepository {
    //KEEP A CLLECTION OF PIECES OF  CONTENT IN THE  MEMORY
private final List<Content>contentList=new ArrayList<>();
public ContentCollectionRepository(){

}
public List<Content> findAll(){
    return contentList;

}
public Optional<Content> findbyID(Integer id){
    return contentList.stream().filter(c->c.id().equals(id)).findFirst();
//HELPS US SEARCH AND FIND THE REQUESTED ID AND HANDLE NULL VALUES AS WELL
}
@PostConstruct
private void init(){
    Content c=  new Content(
    1,
    "FIRST CONTENT", 
    "MEOW MOEWOO MEOW", 
    Status.IDEA, 
    Type.ARTICLE, 
    LocalDateTime.now(), 
    null, 
    "");
    contentList.add(c);
}
public void save(Content content) {
    contentList.removeIf(c->c.id().equals(content.id()));//heck if the record already exist,if it does remove the existing record and create a  new one with pdatted info
    
    contentList.add(content);
}
public boolean exixtsbyId(Integer id) {
    return contentList.stream().filter(c->c.id().equals(id)).count()==1;
}
public void delete(Integer id) {
    contentList.removeIf(c->c.id().equals(id));


}

}
