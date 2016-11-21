package com.shams.spring.rest;

import com.shams.spring.poller.Poller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


/**
 * Created by shams on 11/21/16.
 */
@RestController
@RequestMapping("/poller")
public class PollerRestService {

    @Autowired
    private Poller poller;

    @RequestMapping(value = "/start/{reccur}", method = RequestMethod.PUT)
    public boolean start(@PathVariable("reccur") Long reccur) {
        return poller.poll(reccur);
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public boolean stop() {
        return poller.stop();
    }

    @RequestMapping(value = "/listeners", method = RequestMethod.GET)
    public Set<String> listeners() {
        return poller.getListeners();
    }

    @RequestMapping(value = "/listeners/{id}", method = RequestMethod.DELETE)
    public boolean removeListener(@PathVariable("id") String id) {
        poller.removePollerListener(id);
        return true;
    }
}
