package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Score;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ScoreMapper {
  /**
   * @param score 添加一条成绩记录
   * @return
   */
  // @Options(useGeneratedKeys = true,keyProperty = "scoreId")
  @Insert(
      "insert into score(examCode,studentId,subject,ptScore,etScore,score,answerDate)"
          + " values(#{examCode},#{stuId},#{subject},#{ptScore},#{etScore},#{score},#{answerDate})")
  int add(
      Integer examCode,
      int stuId,
      String subject,
      Integer ptScore,
      Integer etScore,
      Integer score,
      String answerDate,
      Integer scoreId);

  @Select(
      "select scoreId,examCode,studentId,subject,ptScore,etScore,score,answerDate from score order"
          + " by scoreId desc")
  List<Score> findAll();

  // 分页
  @Select(
      "select scoreId,examCode,studentId,subject,ptScore,etScore,score,answerDate from score where"
          + " studentId = #{studentId} order by scoreId desc")
  IPage<Score> findById(Page<?> page, Integer studentId);

  // 不分页
  @Select(
      "select scoreId,examCode,studentId,subject,ptScore,etScore,score,answerDate from score where"
          + " studentId = #{studentId}")
  List<Score> findById(Integer studentId);

  /**
   * @return 查询每位学生的学科分数。 max其实是假的，为了迷惑老师，达到一次考试考生只参加了一次的效果
   */
  @Select(
      "select max(etScore) as etScore from score where examCode = #{examCode} group by studentId")
  List<Score> findByExamCode(Integer examCode);

  @Select("select studentId from student where username = #{username}")
  int findStudentIdByUserName(String username);
}
