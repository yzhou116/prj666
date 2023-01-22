package com.exam.serviceimpl;

import com.exam.entity.PaperManage;
import com.exam.mapper.PaperMapper;
import com.exam.service.PaperService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl implements PaperService {

  @Autowired private PaperMapper paperMapper;

  @Override
  public List<PaperManage> findAll() {
    return paperMapper.findAll();
  }

  @Override
  public List<PaperManage> findById(Integer paperId) {
    return paperMapper.findById(paperId);
  }

  @Override
  public int add(PaperManage paperManage) {
    return paperMapper.add(paperManage);
  }
}
