package org.rest.alert.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.rest.alert.service.sender;
import org.rest.alert.dao.messageBean;
import org.rest.alert.service.configure;

@RestController
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class baseController {
    @Value("${mq.address}")
    private String address;

    @Value("${mq.port}")
    private int port;

    @Value("${app.id}")
    private String id;

    @Value("${app.password}")
    private String password;

    @Value("${bus.subject}")
    private String subject;

    @RequestMapping("/")
    public String base(){
        return "200";
    }

    @RequestMapping(value = "/alert", method = RequestMethod.POST)
    public int connector(@RequestBody messageBean messagebean){
        //return "hello world~";
        configure con = new configure();
        con.init(address, port, id, password, subject);
        sender message = new sender();
        int value = message.send( messagebean, con);
        if (value >= 1){
            return value;
        }
        else {
            return 0;
        }
    }
}
