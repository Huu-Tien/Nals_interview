package com.me.nalsinterview.service.impls;

import com.me.nalsinterview.entity.Work;
import com.me.nalsinterview.repository.WorkRepository;
import com.me.nalsinterview.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkRepository repository;

    @Override
    public Work save(Work work) {
        return repository.save(work);
    }

    @Override
    public List<Work> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Work> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Work update(Work work, Long id) {
        Work workDb = repository.findById(id).orElse(null);
        if (Objects.nonNull(workDb)) {
            if (Objects.nonNull(work.getWorkName())
                    && !"".equalsIgnoreCase(work.getWorkName())) {
                workDb.setWorkName(work.getWorkName());
            }
            if (Objects.nonNull(work.getStartingDate())) {
                workDb.setStartingDate(work.getStartingDate());
            }
            if (Objects.nonNull(work.getEndingDate())) {
                workDb.setEndingDate(work.getEndingDate());
            }
            if (Objects.nonNull(work.getStatus())) {
                workDb.setStatus(work.getStatus());
            }
        }

        return repository.save(workDb);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
