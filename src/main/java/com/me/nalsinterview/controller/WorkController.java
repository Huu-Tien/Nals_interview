package com.me.nalsinterview.controller;

import com.me.nalsinterview.entity.Work;
import com.me.nalsinterview.handler.ResponseHandler;
import com.me.nalsinterview.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class WorkController {

    @Autowired
    private WorkService service;

    /**
     * Add new work.
     *
     * @param work
     * @return ResponseEntity<Object>
     */
    @RequestMapping(value = "/work", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestBody Work work) {
        try {
            Work result = service.save(work);
            return ResponseHandler.generateResponse(result, HttpStatus.OK, "Save Work Successfully!");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Fetch list works.
     * API: localhost:8080/api/work/
     *
     * @return ResponseEntity<Object>
     */
    @RequestMapping(value = "/work/", method = RequestMethod.GET)
    public ResponseEntity<Object> findAll() {
        try {
            List<Work> result = service.findAll();
            return ResponseHandler.generateResponse(result, HttpStatus.OK, "Find all work!");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Fetch list works with sorting and pagination.
     * API: localhost:8080/api/work?page=0&size=5
     *
     * @param page
     * @param size
     * @return ResponseEntity<Object>
     */
    @RequestMapping(value = "/work", method = RequestMethod.GET)
    public ResponseEntity<Object> findAll(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "5") int size,
                                          @RequestParam(defaultValue = "ASC") String sort) {
        try {
            Sort sortable = Sort.by("id").ascending();
            if (sort.equals("DESC")) {
                sortable = Sort.by("id").descending();
            }
            Pageable pageable = PageRequest.of(page, size, sortable);
            Page<Work> result = service.findAll(pageable);
            return ResponseHandler.generateResponse(result, HttpStatus.OK, "Fetch list works with sorting and pagination!");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Update work.
     *
     * @param work
     * @param id
     * @return ResponseEntity<Object>
     */
    @RequestMapping(value = "/work/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody Work work, @PathVariable("id") Long id) {
        try {
            Work result = service.update(work, id);
            return ResponseHandler.generateResponse(result, HttpStatus.OK, "Update Work Successfully!");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Delete work.
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/work/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseHandler.generateResponse(null, HttpStatus.OK, "Deleted Successfully!");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
