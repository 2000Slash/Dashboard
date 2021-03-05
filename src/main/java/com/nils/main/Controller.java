package com.nils.main;

import com.amihaiemil.docker.Container;
import com.amihaiemil.docker.Containers;
import com.amihaiemil.docker.UnixDocker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api")
// When building for prod, you should remove this line or else everyone can access this api
@CrossOrigin(origins = "*")
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final UnixDocker dockerService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/containers/json", produces = "application/json;")
    @ResponseBody
    public String getContainers() {
        JsonArrayBuilder containerList = Json.createArrayBuilder();
        dockerService.containers().all().forEachRemaining(containerList::add);
        return containerList.build().toString();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/containers/prune")
    public String pruneContainers(@RequestParam(name = "all", defaultValue = "false") String all) {
        AtomicInteger counter = new AtomicInteger();
        dockerService.containers().all().forEachRemaining(container -> {
            if( container.getString("State").equals("exited")) {
                try {
                    container.remove();
                    counter.getAndIncrement();
                } catch (IOException e) {
                    logger.error("Could not stop container : {}, e", container.getJsonArray("Names").getJsonObject(0).getString("string"), e);
                }
            }
        });
        return counter.toString();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(path = "/containers/{id}/stop")
    public String stopContainer(@PathVariable(name = "id") String id) throws IOException {
        dockerService.containers().get(id).stop();
        return "success";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(path = "/containers/{id}/start")
    public String startContainer(@PathVariable(name = "id") String id) throws IOException {
        dockerService.containers().get(id).start();
        return "success";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(path = "/containers/{id}/restart")
    public String restartContainer(@PathVariable(name = "id") String id) throws IOException {
        dockerService.containers().get(id).restart();
        return "success";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/containers/{id}")
    public String deleteContainer(@PathVariable(name = "id") String id) throws IOException {
        dockerService.containers().get(id).remove();
        return "success";
    }
}
