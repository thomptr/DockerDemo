package com.example.controller;

import com.example.model.Host;
import com.example.services.HostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by trevor on 9/11/16.
 */
@Controller
public class HostInfoController {

    private final AtomicLong counter = new AtomicLong();
    private static final Logger LOGGER = LoggerFactory.getLogger(HostInfoController.class);

    @Autowired
    public HostService hostService;

    @RequestMapping("/host")
    public Host getHost(Model model) {

        try {
            String hostname = hostService.getHostname();
            model.addAttribute("hostname", hostname);
            return new Host(hostService.getHostIpAddress(), hostname);
        } catch (UnknownHostException e) {
            LOGGER.error("Unable to obtain host information", e);
            return null;
        }
    }

}