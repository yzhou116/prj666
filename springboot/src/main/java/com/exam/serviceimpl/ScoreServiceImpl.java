package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Score;
import com.exam.mapper.ScoreMapper;
import com.exam.service.ScoreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {

  @Autowired private ScoreMapper scoreMapper;

  @Override
  public int add(
      Integer examCode,
      int stuId,
      String subject,
      Integer ptScore,
      Integer etScore,
      Integer score,
      String AnswerDate,
      Integer scoreId) {
    return scoreMapper.add(examCode, stuId, subject, ptScore, etScore, score, AnswerDate, scoreId);
  }

  @Override
  public List<Score> findAll() {
    return scoreMapper.findAll();
  }

  @Override
  public IPage<Score> findById(Page page, Integer studentId) {
    return scoreMapper.findById(page, studentId);
  }

  @Override
  public List<Score> findById(Integer studentId) {
    return scoreMapper.findById(studentId);
  }

  @Override
  public List<Score> findByExamCode(Integer examCode) {
    return scoreMapper.findByExamCode(examCode);
  }

  @Override
  public int findStudentIdbyUsername(String username) {
    return scoreMapper.findStudentIdByUserName(username);
  }
}
