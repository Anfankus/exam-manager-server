package anfankus.dao;

import anfankus.dao.inner.Answer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class Solution {

  private static ObjectMapper mapper;

  static {
    mapper = new ObjectMapper();
  }


  private String solutionid;
  private String examid;
  private String userid;
  private Integer submittime;
  private Answer answer;
  private Integer totalscore;

  public void setAnswerString(String json) throws Exception {
    answer = mapper.readValue(json, Answer.class);
  }

  @JsonIgnore
  public String getAnswerString() throws Exception {
    return mapper.writeValueAsString(answer);
  }

}
