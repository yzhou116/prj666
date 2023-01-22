package com.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

@Data
public class Replay {
  private Integer messageId;

  private Integer replayId;

  private String replay;

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date replayTime;
}
