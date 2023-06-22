package com.example.contentcalender.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.contentcalender.model.Content;
import com.example.contentcalender.repository.ContentCollectionRepository;
@CrossOrigin///will  hellp our  front end tofetch our application
@RestController//This is bascially defining the type of COMPONENT(INITIALIZATION OF  A CLASS)
@RequestMapping("/api/content")//MAPPING(PATH) TO THIS CONTROLLER
//BASCIALLY THE  CLASS THAT HANNDLES CRUD OPERATIONS
public class ContentController {
    
    private final ContentCollectionRepository repository;
    @Autowired //WE  ADD THIS BEFORE THHE CONSTRUCTOR(TO SPECIFY INJECTION)
    
    //no need for the autowierd annnotation if only one public constructor
   
    public ContentController(ContentCollectionRepository repository){
//this  is  our constructor
///we will inject our  dependency(REPOSITORY) here
this.repository=repository;


    }
    //*** CRUD 1)READ ***/
    //MAKE A  REQUEST ANND FIND  ALL  THE PIECES  OF CONTENT IN THE SYSTEM
    @CrossOrigin
    @GetMapping("")//HANDLES GET REQUESTS(<WE LEAVE THE PATH EMPTY CAUSE WE ALREADY DEFINED A  PATH FOR THIS CLASS IN RESTMAPPING>)
    public List<Content> findAll(){
return repository.findAll();//HEERW WE ARE BASCIALLY USING THE METHOD  OF THE  DEPENDENCY(CLASS)WE HAVE INJECTED
    }
    @CrossOrigin
    @GetMapping("/{id}")//Here we have provided a dynamic url,,which will hellp  us identify the id requested by the user,and use it to retrive it from the system 
public Content  findbyID(@PathVariable Integer id){
    //Herre again we are using the methodof the injected dependency,
    // Note the @PathVariable parameter,this taes the input provided  in the dynamixc url by the user and use it for our code.
    
    return repository.findbyID(id)
    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"CONTENT NOT FOUND"));//Basically takes care of if the id dosent exixts scenario
    
}

//***crud 2)CREATE ***//
@CrossOrigin
@ResponseStatus(HttpStatus.CREATED)//THIS WILL NOTIFY US WHEN A NEW  RESOURCE IS CREATED
@PostMapping("")//IN REST FRAMEWORKK<WHENEVER WE CREATE SOMETHING ITS A POST REQUEST
public void create(@RequestBody Content content){
    repository.save(content);
}

//***crud 3)UPDATE ***//
@CrossOrigin
@ResponseStatus(HttpStatus.NO_CONTENT)
@PutMapping("/{id}")// When we update  a existing resource
public void update(@RequestBody Content content,Integer id){
if(!repository.exixtsbyId(id)){
    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"CONTENT NOT FOUND");//Basically takes care of if the id dosent exixts scenario
// YOU CAN MAKE A CUSTO EXCEPTION FOR THISTOO
}
repository.save(content);

}
//***crud 3)DELETE ***//
@CrossOrigin
@ResponseStatus(HttpStatus.NO_CONTENT)
@DeleteMapping("/{id}")// When we delete a resource
public void delete(Integer id){
    repository.delete(id);
}
}
