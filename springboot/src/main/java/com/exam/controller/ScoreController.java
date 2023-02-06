package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ApiResult;
import com.exam.entity.Message;
import com.exam.entity.Score;
import com.exam.service.SurveyRsService;
import com.exam.serviceimpl.ScoreServiceImpl;
import com.exam.util.ApiResultHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class ScoreController {
    @Autowired
    private ScoreServiceImpl scoreService;

    @Autowired
    private SurveyRsService surveyRsService;

    @GetMapping("/scores")
    public ApiResult findAll() {
        List<Score> res = scoreService.findAll();
        return ApiResultHandler.buildApiResult(200,"查询所有学生成绩",res);
    }
//    分页
    @GetMapping("/score/{page}/{size}/{studentId}")
    public ApiResult findById(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("studentId") String studentId) {
        int stuId = scoreService.findStudentIdbyUsername(studentId);
        Page<Score> scorePage = new Page<>(page, size);
        IPage<Score> res = scoreService.findById(scorePage, stuId);
        return ApiResultHandler.buildApiResult(200, "根据ID查询成绩", res);
    }

//    不分页
    @GetMapping("/score/{studentId}")
        public ApiResult findById(@PathVariable("studentId") String studentId) {
        int stuId = scoreService.findStudentIdbyUsername(studentId);
        List<Score> res = scoreService.findById(stuId);
        if (!res.isEmpty()) {
            return ApiResultHandler.buildApiResult(200, "根据ID查询成绩", res);
        } else {
            return ApiResultHandler.buildApiResult(400, "ID不存在", res);
        }
    }

    @PostMapping("/score")
    public ApiResult add(@RequestBody Score score) {
        int stuId = scoreService.findStudentIdbyUsername(score.getStudentId());
        int res = scoreService.add(score.getExamCode(), stuId,score.getSubject(), score.getPtScore(), score.getEtScore(), score.getScore(), score.getAnswerDate(),score.getScoreId());
        if (res == 0) {
            return ApiResultHandler.buildApiResult(400,"成绩添加失败",res);
        }else {
            return ApiResultHandler.buildApiResult(200,"成绩添加成功",res);
        }
    }

    @GetMapping("/scores/{examCode}")
    public ApiResult findByExamCode(@PathVariable("examCode") Integer examCode) {
        List<Score> scores = scoreService.findByExamCode(examCode);
        return ApiResultHandler.buildApiResult(200,"查询成功",scores);
    }
    @GetMapping("/surveyRes/{examCode}")
    public ApiResult getSurveyRes(@PathVariable("examCode") Integer examCode) throws IOException {
        List<HashMap<String, String>> res = surveyRsService.findSurveydataByExamCode(examCode);


      //  String ress = surveyRsMapper.findResByExamCode(20190021);
      //  TypeReference<HashMap<Integer, String>> typeRef
      //          = new TypeReference<HashMap<Integer, String>>() {};
      //  HashMap<Integer, String> dotdotdto = mapper.readValue(ress, typeRef);
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }


}
