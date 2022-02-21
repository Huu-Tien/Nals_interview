package com.me.nalsinterview.service;

import com.me.nalsinterview.entity.Work;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


import java.util.List;

public interface WorkService {

    /**
     * Add new work.
     *
     * @param work
     * @return Work
     */
    Work save(Work work);

    /**
     * Find all work.
     *
     * @return List<Work>
     */
    List<Work> findAll();

    /**
     * Fetch list works with Pageable.
     *
     * @return Page<Work>
     */
    Page<Work> findAll(Pageable pageable);

    /**
     * Update work by id.
     *
     * @param work
     * @param id
     * @return Work
     */
    Work update(Work work, Long id);

    /**
     * Delete work by id
     *
     * @param id
     */
    void delete(Long id);
}
